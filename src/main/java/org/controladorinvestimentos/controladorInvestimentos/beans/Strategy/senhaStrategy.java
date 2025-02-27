
package org.controladorinvestimentos.controladorInvestimentos.beans.Strategy;
import org.controladorinvestimentos.controladorInvestimentos.Banco.Interfaces.IrepositorioUsuario;
import org.controladorinvestimentos.controladorInvestimentos.Exceptions.Exist;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Usuario;

import java.util.Scanner;

public class senhaStrategy implements updateStrategy{

    private static IrepositorioUsuario repositorioUsuario;

    @Override
    public void updateInfo(Usuario usuario) {

        Scanner ler = new Scanner(System.in);
        System.out.println("Digite a nova senha:");
        String NewInfo = ler.nextLine();
        System.out.println("Digite novamente a nova senha:");
        String NewInfo2 = ler.nextLine();

        try {
            repositorioUsuario.AlterarSenha(NewInfo,usuario);
        } catch (Exist e){
            throw new Exist("Erro ao alterar senha, tente novamente.");
        }
    }
}

