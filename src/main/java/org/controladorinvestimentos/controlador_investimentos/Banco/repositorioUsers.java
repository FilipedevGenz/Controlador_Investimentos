package org.controladorinvestimentos.controlador_investimentos.Banco;


import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.beans.adm;
import org.controladorinvestimentos.controlador_investimentos.beans.usuario;

import java.util.ArrayList;

public class repositorioUsers implements IrepositorioUsers {

    private static final ArrayList<usuario> USUARIOS = new ArrayList<>();

    private static repositorioUsers instance;


    private repositorioUsers(){}


    public static synchronized repositorioUsers getInstance(){

        if(instance == null){
            synchronized (repositorioUsers.class){
                if(instance == null){
                    instance = new repositorioUsers();
                }
            }
        }
        return instance;
    }


    public void adicionarADM(usuario usuario){
        adm NewAdm = new adm();
        USUARIOS.add(NewAdm);
    }

    public void construtorUsuario(int cpf, String nome, String email, String senha) {
            usuario user = new usuario(cpf, nome, senha, email);
            adicionarUsuario(user);
    }

    public void adicionarUsuario(usuario usuario){
         USUARIOS.add(usuario);
    }

    public void removerUsuario(usuario usuario){
        USUARIOS.remove(usuario);
    }

    public usuario buscarUsuario(usuario usuario) throws Exist {
        for(org.controladorinvestimentos.controlador_investimentos.beans.usuario u : USUARIOS){
            if(u.getCpf() == usuario.getCpf()){
                return u;
            }
        }
        throw new Exist("usuario nao encontrado");
    }

    public void AlterarEmail(String email, usuario usuario){
        usuario.setEmail(email);
    }
    public void AlterarSenha(String senha, usuario usuario){
        usuario.setSenha(senha);
    }
    public void AlterarNome(String nome, usuario usuario){usuario.setNome(nome);}

    @Override
    public boolean buscarCPF(Integer cpf) {
        for(usuario u : USUARIOS){
            if(u.getCpf() == cpf){
                return true;
            }
        }
        return false;
    }
    public usuario buscarCPFreturnUser(Integer cpf) {
        for(usuario u : USUARIOS){
            if(u.getCpf() == cpf){
                return u;
            }
        }
        return null;
    }


}
