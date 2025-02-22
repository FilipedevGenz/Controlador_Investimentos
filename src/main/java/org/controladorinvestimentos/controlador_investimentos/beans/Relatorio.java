package org.controladorinvestimentos.controlador_investimentos.beans;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.time.LocalDate;
@Getter

public class Relatorio {

    private static int contadorId = 0;

            final String nomeAtivo;
            final String codigo;
    @Setter double valorCompra;
            final LocalDate data = LocalDate.now();
            final double quantidade;
            final int id;

        public Relatorio(String codigo, double valorCompra, double quantidade) throws IOException {
        this.id = ++contadorId;
        this.nomeAtivo = APIrequest.buscarNomeAtivo(codigo);
        this.valorCompra = valorCompra;
        this.quantidade = quantidade;
        this.codigo = codigo;
    }

        public double getValorTotal() {
        return valorCompra * quantidade;
    }
}

