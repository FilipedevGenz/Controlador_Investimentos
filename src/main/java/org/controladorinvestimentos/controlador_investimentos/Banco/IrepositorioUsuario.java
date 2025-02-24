package org.controladorinvestimentos.controlador_investimentos.Banco;

import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.beans.Usuario;

public interface IrepositorioUsuario {
    void construtorUsuario(int cpf, String nome, String email, String senha);
    void adicionarUsuario(Usuario usuario);
    void removerUsuario(Usuario usuario) throws Exist;
    Usuario buscarUsuario(Usuario usuario) throws Exist;
    void alterarEmail(String email, Usuario usuario) throws Exist;
    void alterarSenha(String senha, Usuario usuario) throws Exist;
    void alterarNome(String nome, Usuario usuario) throws Exist;
    boolean buscarCPF(int cpf);
    Usuario buscarCPFreturnUser(int cpf);
}