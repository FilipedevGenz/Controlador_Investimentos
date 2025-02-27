package org.controladorinvestimentos.controladorInvestimentos.Banco.Interfaces;

import org.controladorinvestimentos.controladorInvestimentos.Exceptions.Exist;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Usuario;

public interface IrepositorioUsuario {
    void construtorUsuario(int cpf, String nome, String email, String senha);
    void adicionarUsuario(Usuario usuario);
    void removerUsuario(Usuario usuario) throws Exist;
    Usuario buscarUsuario(Usuario usuario) throws Exist;
    void AlterarEmail(String email, Usuario usuario) throws Exist;
    void AlterarSenha(String senha, Usuario usuario) throws Exist;
    void AlterarNome(String nome, Usuario usuario) throws Exist;
    boolean buscarCPF(int cpf);
    Usuario buscarCPFreturnUser(int cpf);
}