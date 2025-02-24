package org.controladorinvestimentos.controlador_investimentos.Banco;

import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.beans.usuario;

public interface IrepositorioUsuario {
    void construtorUsuario(int cpf, String nome, String email, String senha);
    void adicionarUsuario(usuario usuario);
    void removerUsuario(usuario usuario) throws Exist;
    usuario buscarUsuario(usuario usuario) throws Exist;
    void alterarEmail(String email, usuario usuario) throws Exist;
    void alterarSenha(String senha, usuario usuario) throws Exist;
    void alterarNome(String nome, usuario usuario) throws Exist;
    boolean buscarCPF(int cpf);
    usuario buscarCPFreturnUser(int cpf);
    usuario buscarCPFreturnConta(int cpf);
}