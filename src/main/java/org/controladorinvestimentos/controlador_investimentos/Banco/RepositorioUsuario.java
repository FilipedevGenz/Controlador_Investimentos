package org.controladorinvestimentos.controlador_investimentos.Banco;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.beans.Usuario;

import java.util.ArrayList;

public class RepositorioUsuario implements IrepositorioUsuario {

    private static final ArrayList<Usuario> USUARIOS = new ArrayList<>();
    private static RepositorioUsuario instance;

    private RepositorioUsuario() {}

    public static synchronized RepositorioUsuario getInstance() {
        if (instance == null) {
            synchronized (RepositorioUsuario.class) {
                if (instance == null) {
                    instance = new RepositorioUsuario();
                }
            }
        }
        return instance;
    }

    @Override
    public void construtorUsuario(int cpf, String nome, String email, String senha) {
        Usuario user = new Usuario(cpf, nome, senha, email);
        adicionarUsuario(user);
    }

    @Override
    public void adicionarUsuario(Usuario usuario) {
        USUARIOS.add(usuario);
    }

    @Override
    public void removerUsuario(Usuario usuario) throws Exist {
        Usuario userToRemove = null;
        for (Usuario u : USUARIOS) {
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
    public Usuario buscarUsuario(Usuario usuario) throws Exist {
        for (Usuario u : USUARIOS) {
            if (u.getCpf() == usuario.getCpf()) {
                return u;
            }
        }
        throw new Exist("Usuário não encontrado");
    }

    @Override
    public void alterarEmail(String email, Usuario usuario) throws Exist {
        Usuario user = buscarUsuario(usuario);
        user.setEmail(email);
    }

    @Override
    public void alterarSenha(String senha, Usuario usuario) throws Exist {
        Usuario user = buscarUsuario(usuario);
        user.setSenha(senha);
    }

    @Override
    public void alterarNome(String nome, Usuario usuario) throws Exist {
        Usuario user = buscarUsuario(usuario);
        user.setNome(nome);
    }

    @Override
    public boolean buscarCPF(int cpf) {
        for (Usuario u : USUARIOS) {
            if (u.getCpf() == cpf) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Usuario buscarCPFreturnUser(int cpf) {
        RepositorioUsuario.getInstance();
        for (Usuario u : USUARIOS) {
            if (u.getCpf() == cpf) {
                return u;
            }
        }
        return null;
    }
}