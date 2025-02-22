package org.controladorinvestimentos.controlador_investimentos.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class Ativo {

    @NonNull public String nome;
    @NonNull public String code;

    public Ativo(String nome, double preco) {
    }

    public Ativo(String nome) {
    }
}

