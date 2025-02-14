package org.controladorinvestimentos.controlador_investimentos.beans;

import lombok.*;
import org.controladorinvestimentos.controlador_investimentos.Banco.IrepositorioCarteira;
import org.controladorinvestimentos.controlador_investimentos.Banco.repositorioCarteira;

@Setter
@Getter
@RequiredArgsConstructor
public class conta {

    @NonNull
    private final String nome;
    @NonNull
    private final String senha;
    @NonNull
    private final String email;
    @NonNull
    private final String cpf;
    public IrepositorioCarteira repositorioCarteira;
}