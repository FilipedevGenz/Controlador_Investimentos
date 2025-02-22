package org.controladorinvestimentos.controlador_investimentos.beans;

import org.controladorinvestimentos.controlador_investimentos.Banco.RepositorioMovimentacoes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        carteira.setValorCarteira(variacaoPreco);
        Double impactoFinanceiro = quantidade * variacaoPreco;

        // Obtém o valor atual da carteira, considerando os preços em tempo real de cada ativo
        Double valorAtualCarteira = atualizarValorCarteira(carteira);


        if (valorAtualCarteira == 0) {
            return 0.0;
        }

        Double impactoPercentual = (impactoFinanceiro / valorAtualCarteira) * 100;
        return impactoPercentual;
    }


    public static Double atualizarValorCarteira(Carteira carteira) throws IOException {
        Double valorAtualizado = 0.0;

        List<String> ativos = carteira.repositorioRelatorio.getAtivos();

        for (String ativo : ativos) {
            // Obtém a quantidade do ativo na carteira
            Double quantidade = carteira.repositorioRelatorio.getQuantidadeAtivo(ativo);

            // Busca o preço atual do ativo em tempo real
            Double precoAtual = APIrequest.buscarPrecoAtivoEmTempoReal(ativo);

            // Atualiza o valor acumulado da carteira
            valorAtualizado += quantidade * precoAtual;
        }

        return valorAtualizado;
    }




}
