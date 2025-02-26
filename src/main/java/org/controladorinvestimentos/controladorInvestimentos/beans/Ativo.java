package org.controladorinvestimentos.controladorInvestimentos.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class Ativo {

    @NonNull public String nome;
    @NonNull public double preco;
    @NonNull public double dataAtivo;


}

