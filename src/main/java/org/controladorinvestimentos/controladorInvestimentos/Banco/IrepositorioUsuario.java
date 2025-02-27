package org.controladorinvestimentos.controladorInvestimentos.Banco;

import org.controladorinvestimentos.controladorInvestimentos.Exceptions.Exist;
import org.controladorinvestimentos.controladorInvestimentos.beans.Usuario;

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