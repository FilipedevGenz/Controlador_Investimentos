package org.controladorinvestimentos.controlador_investimentos.beans;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter

public class Relatorio {

    private static int contadorId = 0;

            final String nomeAtivo;
    @Setter double valorCompra;
            final LocalDate data = LocalDate.now();
            final double quantidade;
            final int id;

        public Relatorio(String nomeAtivo, double valorCompra, double quantidade) {
        this.id = ++contadorId;
        this.nomeAtivo = nomeAtivo;
        this.valorCompra = valorCompra;
        this.quantidade = quantidade;
    }

        public double getValorTotal() {
        return valorCompra * quantidade;
    }
}

