package org.controladorinvestimentos.controlador_investimentos.Banco;


import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.beans.Adm;
import org.controladorinvestimentos.controlador_investimentos.beans.Usuario;

import java.util.ArrayList;

public class RepositorioUsers implements iRepositorioUsers {

    private static final ArrayList<Usuario> Usuarios = new ArrayList<>();

    private static RepositorioUsers instance;


    private RepositorioUsers(){}


    public static synchronized RepositorioUsers getInstance(){

        if(instance == null){
            synchronized (RepositorioUsers.class){
                if(instance == null){
                    instance = new RepositorioUsers();
                }
            }
        }
        return instance;
    }


    public void adicionarADM(Usuario usuario){
        Adm NewAdm = new Adm();
        Usuarios.add(NewAdm);
    }

    public void construtorUsuario(int cpf, String nome, String email, String senha) {
            Usuario user = new Usuario(cpf, nome, senha, email);
            adicionarUsuario(user);
    }

    public void adicionarUsuario(Usuario usuario){
         Usuarios.add(usuario);
    }

    public void removerUsuario(Usuario usuario){
        Usuarios.remove(usuario);
    }

    public Usuario buscarUsuario(Usuario usuario) throws Exist {
        for(Usuario u : Usuarios){
            if(u.getCpf() == usuario.getCpf()){
                return u;
            }
        }
        throw new Exist("usuario nao encontrado");
    }

    public void AlterarEmail(String email,Usuario usuario){
        usuario.setEmail(email);
    }
    public void AlterarSenha(String senha,Usuario usuario){
        usuario.setSenha(senha);
    }
    public void AlterarNome(String nome,Usuario usuario){usuario.setNome(nome);}

    @Override
    public boolean buscarCPF(Integer cpf) {
        for(Usuario u : Usuarios){
            if(u.getCpf() == cpf){
                return true;
            }
        }
        return false;
    }

}
