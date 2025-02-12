package org.controladorinvestimentos.controlador_investimentos.beans;

import java.util.List;
import java.time.LocalDate;

public class ControladorRelatorio {

    public static relatorio criarRelatorio(String nomeAtivo,double valorCompra, LocalDate data, double quantidade) {
        LocalDate dataAtual = LocalDate.now();
        return new relatorio(nomeAtivo, valorCompra, dataAtual, quantidade);
    }

}