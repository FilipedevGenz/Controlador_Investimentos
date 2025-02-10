package org.controladorinvestimentos.controlador_investimentos.beans;

import java.util.Scanner;


import Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.Banco.iRepositorioUsers;

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

        if (option == UpdateOptions.Email){
            try {
                System.out.println("Digite o novo email:");
                Scanner ler = new Scanner(System.in);
                String NewInfo = ler.nextLine();
                repositorioUsuario.AlterarEmail(NewInfo);
            }catch (Exist e){
                throw new Exist("Erro ao alterar Email, tente novamente.");
            }
        }
        else if (option == UpdateOptions.Nome){
            try {
                System.out.println("Digite o novo nome:");
                Scanner ler = new Scanner(System.in);
                String NewInfo = ler.nextLine();
                repositorioUsuario.AlterarNome(NewInfo);
            }catch (Exist e){
                throw new Exist("Erro ao alterar Nome, tente novamente.");
            }
        }
        else if (option == UpdateOptions.Senha){
            Scanner ler = new Scanner(System.in);
            System.out.println("Digite a nova senha:");
            String NewInfo = ler.nextLine();
            System.out.println("Digite novamente a nova senha:");
            String NewInfo2 = ler.nextLine();

            try {
                    repositorioUsuario.AlterarSenha(NewInfo);
            } catch (Exist e){
                throw new Exist("Erro ao alterar senha, tente novamente.");
            }
        }
    }


}
