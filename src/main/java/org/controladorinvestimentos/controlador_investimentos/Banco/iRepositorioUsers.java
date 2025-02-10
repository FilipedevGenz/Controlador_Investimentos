package org.controladorinvestimentos.controlador_investimentos.Banco;

import org.controladorinvestimentos.controlador_investimentos.beans.Usuario;

public interface iRepositorioUsers {

    public void adicionarUsuario(Usuario usuario);
    public void adicionarADM(Usuario usuario);
    public void removerUsuario(Usuario usuario);
    public Usuario buscarUsuario(Usuario usuario) throws Exception;
    public void AlterarEmail(String email,Usuario usuario);
    public void AlterarSenha(String senha,Usuario usuario);
    public void AlterarNome(String nome,Usuario usuario);
    public boolean buscarCPF(Integer cpf);
    public void construtorUsuario(int cpf,String nome, String email, String senha);
}
