package org.controladorinvestimentos.controlador_investimentos.beans;

import java.io.IOException;
import java.time.LocalDate;

public class controladorRelatorio {

    public Relatorio criarRelatorio(String codigoAtivo,  double quantidade) throws IOException {
        return new Relatorio(codigoAtivo, quantidade);
    }

}