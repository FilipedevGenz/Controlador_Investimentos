package org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class Ativo {

    @NonNull public String nome;
    @NonNull public double preco;


}

