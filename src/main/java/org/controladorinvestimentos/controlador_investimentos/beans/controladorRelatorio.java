package org.controladorinvestimentos.controlador_investimentos.beans;

import java.time.LocalDate;

public class controladorRelatorio {

    public Relatorio criarRelatorio(String nomeAtivo, double valorCompra, LocalDate data, double quantidade) {
        return new Relatorio(nomeAtivo, valorCompra, quantidade);
    }

}