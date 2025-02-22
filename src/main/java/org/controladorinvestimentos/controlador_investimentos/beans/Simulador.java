package org.controladorinvestimentos.controlador_investimentos.beans;

import org.controladorinvestimentos.controlador_investimentos.Banco.RepositorioMovimentacoes;

import java.io.IOException;
import java.util.ArrayList;

public class Simulador {

    //passar o codigo do ativo como parametro
    public static Double simularVariacao(Carteira carteira,String ativo) throws IOException {
        Double valorMedioCompra = carteira.repositorioRelatorio.valorMedioDeCompra(ativo);
        Double valorAtual = APIrequest.buscarPrecoAtivoEmTempoReal(ativo);
        return valorMedioCompra - valorAtual;
    }

    public static Double influenciaNaCarteira(Carteira carteira, String ativo) throws IOException {
        
        Double quantidade = carteira.repositorioRelatorio.getQuantidadeAtivo(ativo);

        if (quantidade == 0) {
            return 0.0;
        }

        Double variacaoPreco = simularVariacao(carteira, ativo);

        Double impactoFinanceiro = quantidade * variacaoPreco;

        Double valorTotalCarteira = carteira.repositorioRelatorio.valorDeCompraCarteira();

        if (valorTotalCarteira == 0) {
            return 0.0;
        }

        Double impactoPercentual = (impactoFinanceiro / valorTotalCarteira) * 100;

        return impactoPercentual;
    }



}
