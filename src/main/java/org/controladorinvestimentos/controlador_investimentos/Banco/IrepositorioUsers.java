package org.controladorinvestimentos.controlador_investimentos.Banco;

import org.controladorinvestimentos.controlador_investimentos.beans.usuario;

public interface IrepositorioUsers {

    public void adicionarUsuario(usuario usuario);
    public void adicionarADM(usuario usuario);
    public void removerUsuario(usuario usuario);
    public usuario buscarUsuario(usuario usuario) throws Exception;
    public void AlterarEmail(String email, usuario usuario);
    public void AlterarSenha(String senha, usuario usuario);
    public void AlterarNome(String nome, usuario usuario);
    public boolean buscarCPF(Integer cpf);
    public void construtorUsuario(int cpf,String nome, String email, String senha);
}
