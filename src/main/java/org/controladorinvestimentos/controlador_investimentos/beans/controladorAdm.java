package org.controladorinvestimentos.controlador_investimentos.beans;

import org.controladorinvestimentos.controlador_investimentos.Banco.repositorioUsers;
import org.controladorinvestimentos.controlador_investimentos.Banco.IrepositorioUsers;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;

public class controladorAdm {

    private static IrepositorioUsers repositorioUsuario;

    public controladorAdm() {
        repositorioUsuario = repositorioUsers.getInstance();
    }

    public void CadastrarAdm(usuario usuario) {
        try {
            usuario usuarioEncontrado = repositorioUsuario.buscarUsuario(usuario);
            if (usuarioEncontrado != null) {
                throw new Exist("Usuário já existe no sistema.");
            }
        } catch (Exception e) {
            repositorioUsuario.adicionarADM(usuario);
        }
    }
}