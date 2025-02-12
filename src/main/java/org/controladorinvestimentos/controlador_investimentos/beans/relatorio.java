package org.controladorinvestimentos.controlador_investimentos.beans;

import java.time.LocalDate;

public class relatorio {
    private final String nomeAtivo;
    private final double valorCompra;
    private final LocalDate data;
    private final double quantidade;
    private final double valorTotal;

    // Construtor da classe Registro
    public relatorio(String nomeAtivo, double valorCompra, LocalDate data, double quantidade) {
        this.nomeAtivo = nomeAtivo;
        this.valorCompra = valorCompra;
        this.data = data;
        this.quantidade = quantidade;
        valorTotal = quantidade * valorCompra;
    }

    // Getters para acessar os dados do registro
    public String getNomeAtivo() {
        return nomeAtivo;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public LocalDate getData() {
        return data;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public double getvalorTotal(){
        return valorTotal;
    }

    @Override
    public String toString() {
        return "Ativo: " + nomeAtivo + "\n" +
               "Quantidade: " + quantidade + "\n" +
               "Valor de Compra: " + valorCompra + "\n" +
               "Data: " + data;
    }
}

