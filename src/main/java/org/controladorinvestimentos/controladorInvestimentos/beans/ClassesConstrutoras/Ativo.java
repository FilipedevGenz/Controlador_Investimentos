package org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Ativo {

    @NonNull public String nome;
    @NonNull public double preco;

    public Ativo(String nome, double preco) {
        this.nome = nome.trim().toUpperCase();
        this.preco = preco;
    }
}

