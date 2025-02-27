/*

package org.controladorinvestimentos.controladorInvestimentos.beans.Strategy;


import org.controladorinvestimentos.controladorInvestimentos.Banco.IrepositorioUsuario;
import org.controladorinvestimentos.controladorInvestimentos.Exceptions.Exist;
import org.controladorinvestimentos.controladorInvestimentos.beans.Usuario;

import java.util.Scanner;

public class emailStrategy implements updateStrategy {

    private static IrepositorioUsuario repositorioUsuario;

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
*/