package Banco;

import Exceptions.Exist;
import beans.UpdateOptions;
import beans.Usuario;
import java.util.ArrayList;

public class RepositorioUsers implements iRepositorioUsers {

    private static final ArrayList<Usuario> Usuarios = new ArrayList<>();


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

    public void AlterarEmail(String email){

    }
    public void AlterarSenha(String senha){

    }
    public void AlterarNome(String nome){

    }
}