package org.controladorinvestimentos.controlador_investimentos.beans;

public class Ativo {

    public String nome;
    private double preco;
    private double quantidade;

   public Ativo(String nome, double preco){
        this.nome = nome;
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getQuantidade() {return quantidade;}

    public void setQuantidade(double quantidade) {this.quantidade += quantidade;}
}