package org.controladorinvestimentos.controlador_investimentos.beans;

import org.controladorinvestimentos.controlador_investimentos.Banco.iRepositorioUsers;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.beans.Strategy.emailStrategy;
import org.controladorinvestimentos.controlador_investimentos.beans.Strategy.nomeStrategy;
import org.controladorinvestimentos.controlador_investimentos.beans.Strategy.senhaStrategy;
import org.controladorinvestimentos.controlador_investimentos.beans.Strategy.updateStrategy;

import java.util.Map;
import java.util.Scanner;

import static org.controladorinvestimentos.controlador_investimentos.beans.UpdateOptions.Email;
import static org.controladorinvestimentos.controlador_investimentos.beans.UpdateOptions.Nome;


public class ControladorUsers {

    private static iRepositorioUsers repositorioUsuario;
    //verificações por email e prints no console serão corrigidas na fase de produção da gui

    public void CadastrarUsuario(Usuario usuario) {
        try {
            Usuario usuarioEncontrado = repositorioUsuario.buscarUsuario(usuario);
            if (usuarioEncontrado != null) {
                throw new Exist("Usuário já existe no sistema.");
            }
        } catch (Exception e) {
            repositorioUsuario.adicionarUsuario(usuario);
        }
    }
    public void CadastrarAdm(Usuario usuario) {
        try {
            Usuario usuarioEncontrado = repositorioUsuario.buscarUsuario(usuario);
            if (usuarioEncontrado != null) {
                throw new Exist("Usuário já existe no sistema.");
            }
        } catch (Exception e) {
            repositorioUsuario.adicionarADM(usuario);
        }

    }



    public void RemoverUsuario(Usuario usuario) {
        try {
            Usuario usuarioEncontrado = repositorioUsuario.buscarUsuario(usuario);
            if (usuarioEncontrado != null) {
                repositorioUsuario.removerUsuario(usuario);
            }
        }catch (Exception e) {
            throw new Exist("Usuario não existe no sistema.");
        }
    }

    public void AlterarUsuario(Usuario usuario,UpdateOptions option) throws Exist{

        //strategy
        UpdateOptions Senha;
        final Map<UpdateOptions, updateStrategy> mapStrategy = Map.of(
                Nome, new nomeStrategy(),
                Senha, new senhaStrategy(),
                Email, new emailStrategy()
        );

        try {
            Usuario usuarioAtual = repositorioUsuario.buscarUsuario(usuario);

            mapStrategy.get(option).updateInfo(usuarioAtual);

        } catch (Exception e) {throw new Exist("Usuario não existe no sistema");}
    }


}
