package org.controladorinvestimentos.controlador_investimentos.beans.Strategy;


import org.controladorinvestimentos.controlador_investimentos.Banco.IrepositorioUsers;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.beans.Usuario;

import java.util.Scanner;

public class emailStrategy implements updateStrategy {

    private static IrepositorioUsers repositorioUsuario;

    @Override
    public void updateInfo(Usuario usuario) {

            try {
                System.out.println("Digite o novo email:");
                Scanner ler = new Scanner(System.in);
                String NewInfo = ler.nextLine();
                repositorioUsuario.AlterarEmail(NewInfo,usuario);

            }catch (Exist e){throw new Exist("Erro ao alterar Email, tente novamente.");}

        }
    }
