package org.controladorinvestimentos.controladorInvestimentos.beans;

import java.io.IOException;

public class ControladorRelatorio {

    public Relatorio criarRelatorio(String codigoAtivo,  double quantidade) throws IOException {
        return new Relatorio(codigoAtivo, quantidade);
    }

}