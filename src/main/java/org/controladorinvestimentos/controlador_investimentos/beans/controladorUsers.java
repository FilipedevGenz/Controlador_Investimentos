package org.controladorinvestimentos.controlador_investimentos.beans;

import org.controladorinvestimentos.controlador_investimentos.Banco.repositorioUsers;
import org.controladorinvestimentos.controlador_investimentos.Banco.IrepositorioUsers;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.beans.Strategy.emailStrategy;
import org.controladorinvestimentos.controlador_investimentos.beans.Strategy.nomeStrategy;
import org.controladorinvestimentos.controlador_investimentos.beans.Strategy.senhaStrategy;
import org.controladorinvestimentos.controlador_investimentos.beans.Strategy.updateStrategy;

import java.util.Map;

import static org.controladorinvestimentos.controlador_investimentos.beans.updateOptions.Email;
import static org.controladorinvestimentos.controlador_investimentos.beans.updateOptions.Nome;


public class controladorUsers {

    private static IrepositorioUsers repositorioUsuario;
    //verificações por email e prints no console serão corrigidas na fase de produção da gui

    public controladorUsers(){
        repositorioUsuario = repositorioUsers.getInstance();
    }
    public void cadastrarNovoUsuario(int cpf, String nome, String email, String senha) throws Exist {
        if (repositorioUsuario.buscarCPF(cpf)) {
            throw new Exist("Usuário já existe no sistema.");
        }

        usuario novoUsuario = new usuario(cpf, nome, senha, email);
        repositorioUsuario.adicionarUsuario(novoUsuario);
    }

//não sei se esse codigo pode ser necessario mais pra frente, por isso esta em comentado
    //public void enviarParaBD(Usuario usuario) {
      //  try {
       //     Usuario usuarioEncontrado = repositorioUsuario.buscarUsuario(usuario);
       //     if (usuarioEncontrado != null) {
        //        throw new Exist("Usuário já existe no sistema.");
//          }
       // } catch (Exception e) {
        //    repositorioUsuario.adicionarUsuario(usuario);
      //  }
   //}

    public void CadastrarAdm(usuario usuario) {
        try {
            org.controladorinvestimentos.controlador_investimentos.beans.usuario usuarioEncontrado = repositorioUsuario.buscarUsuario(usuario);
            if (usuarioEncontrado != null) {
                throw new Exist("Usuário já existe no sistema.");
            }
        } catch (Exception e) {
            repositorioUsuario.adicionarADM(usuario);
        }

    }



    public void RemoverUsuario(usuario usuario) {
        try {
            org.controladorinvestimentos.controlador_investimentos.beans.usuario usuarioEncontrado = repositorioUsuario.buscarUsuario(usuario);
            if (usuarioEncontrado != null) {
                repositorioUsuario.removerUsuario(usuario);
            }
        }catch (Exception e) {
            throw new Exist("Usuario não existe no sistema.");
        }
    }

    public void AlterarUsuario(usuario usuario, updateOptions option) throws Exist{

        //strategy
        updateOptions Senha;
        final Map<updateOptions, updateStrategy> mapStrategy = Map.of(
                Nome, new nomeStrategy(),
                updateOptions.Senha, new senhaStrategy(),
                Email, new emailStrategy()
        );

        try {
            org.controladorinvestimentos.controlador_investimentos.beans.usuario usuarioAtual = repositorioUsuario.buscarUsuario(usuario);

            mapStrategy.get(option).updateInfo(usuarioAtual);

        } catch (Exception e) {throw new Exist("Usuario não existe no sistema");}
    }

    public conta buscarUsuario(int cpf) {
        return repositorioUsuario.buscarCPFreturnConta(cpf);
    }

}
