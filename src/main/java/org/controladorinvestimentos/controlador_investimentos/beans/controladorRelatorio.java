package org.controladorinvestimentos.controlador_investimentos.beans;

import java.time.LocalDate;

public class controladorRelatorio {

    public static relatorio criarRelatorio(String nomeAtivo,double valorCompra, LocalDate data, double quantidade) {
        LocalDate dataAtual = LocalDate.now();
        return new relatorio(nomeAtivo, valorCompra, dataAtual, quantidade);
    }

}