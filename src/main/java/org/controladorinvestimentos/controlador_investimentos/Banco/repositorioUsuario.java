package org.controladorinvestimentos.controlador_investimentos.Banco;

import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.beans.conta;
import org.controladorinvestimentos.controlador_investimentos.beans.usuario;

import java.util.ArrayList;

public class repositorioUsuario implements IrepositorioUsuario {

    private static final ArrayList<usuario> USUARIOS = new ArrayList<>();
    private static repositorioUsuario instance;

    private repositorioUsuario() {}

    public static synchronized repositorioUsuario getInstance() {
        if (instance == null) {
            synchronized (repositorioUsuario.class) {
                if (instance == null) {
                    instance = new repositorioUsuario();
                }
            }
        }
        return instance;
    }

    @Override
    public void construtorUsuario(int cpf, String nome, String email, String senha) {
        usuario user = new usuario(cpf, nome, senha, email);
        adicionarUsuario(user);
    }

    @Override
    public void adicionarUsuario(usuario usuario) {
        USUARIOS.add(usuario);
    }

    @Override
    public void removerUsuario(usuario usuario) throws Exist {
        usuario userToRemove = null;
        for (usuario u : USUARIOS) {
            if (u.getCpf() == usuario.getCpf()) {
                userToRemove = u;
                break;
            }
        }
        if (userToRemove != null) {
            USUARIOS.remove(userToRemove);
        } else {
            throw new Exist("Usuário não encontrado");
        }
    }

    @Override
    public usuario buscarUsuario(usuario usuario) throws Exist {
        for (usuario u : USUARIOS) {
            if (u.getCpf() == usuario.getCpf()) {
                return u;
            }
        }
        throw new Exist("Usuário não encontrado");
    }

    @Override
    public void alterarEmail(String email, usuario usuario) throws Exist {
        usuario user = buscarUsuario(usuario);
        user.setEmail(email);
    }

    @Override
    public void alterarSenha(String senha, usuario usuario) throws Exist {
        usuario user = buscarUsuario(usuario);
        user.setSenha(senha);
    }

    @Override
    public void alterarNome(String nome, usuario usuario) throws Exist {
        usuario user = buscarUsuario(usuario);
        user.setNome(nome);
    }

    @Override
    public boolean buscarCPF(int cpf) {
        for (usuario u : USUARIOS) {
            if (u.getCpf() == cpf) {
                return true;
            }
        }
        return false;
    }

    @Override
    public usuario buscarCPFreturnUser(int cpf) {
        for (usuario u : USUARIOS) {
            if (u.getCpf() == cpf) {
                return u;
            }
        }
        return null;
    }

    @Override
    public conta buscarCPFreturnConta(int cpf) {
        for (usuario u : USUARIOS) {
            if (u.getCpf() == cpf) {
                if (u instanceof conta) {
                    return (conta) u;
                } else {
                    System.out.println("Usuário encontrado, mas não é uma conta.");
                    return null;
                }
            }
        }
        return null;
    }
}