package org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras;
import lombok.Getter;
import lombok.Setter;
import org.controladorinvestimentos.controladorInvestimentos.beans.APIfuncionalidades.APIrequest;

import java.io.IOException;
import java.time.LocalDate;
@Getter
public class Relatorio {

    private static int contadorId = 0;

            final String nomeAtivo;
            @Getter
            final String codigo;
    @Setter double valorCompra;
            final LocalDate data = LocalDate.now();
            @Getter
            final double quantidade;
            final int id;

        public Relatorio(String codigo, double quantidade) throws IOException {
        this.id = ++contadorId;
        this.nomeAtivo = APIrequest.buscarNomeAtivo(codigo);
        this.valorCompra = APIrequest.buscarPrecoAtivoEmTempoReal(codigo);
        this.quantidade = quantidade;
        this.codigo = codigo;
    }

        public double getValorTotal() {
        return valorCompra * quantidade;
    }


}

