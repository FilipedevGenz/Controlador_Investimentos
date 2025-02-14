package org.controladorinvestimentos.controlador_investimentos.beans;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDate;
@Value

public class relatorio {
    String nomeAtivo;
    double valorCompra;
    LocalDate data;
    double quantidade;
    double valorTotal;

    // Construtor da classe Registro
    public relatorio(String nomeAtivo, double valorCompra, LocalDate data, double quantidade) {
        this.nomeAtivo = nomeAtivo;
        this.valorCompra = valorCompra;
        this.data = data;
        this.quantidade = quantidade;
        valorTotal = quantidade * valorCompra;
    }

}

