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
}
