package org.controladorinvestimentos.controlador_investimentos.Banco;


import org.controladorinvestimentos.controlador_investimentos.beans.Usuario;

public interface iRepositorioUsers {

    public void adicionarUsuario(Usuario usuario);
    public void removerUsuario(Usuario usuario);
    public Usuario buscarUsuario(Usuario usuario) throws Exception;
    public void AlterarEmail(String email);
    public void AlterarSenha(String senha);
    public void AlterarNome(String nome);

}
