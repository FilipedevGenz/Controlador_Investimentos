package org.controladorinvestimentos.controlador_investimentos.beans.Strategy;





import org.controladorinvestimentos.controlador_investimentos.Banco.iRepositorioUsers;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.beans.Usuario;

import java.util.Scanner;

public class senhaStrategy implements updateStrategy{

    private static iRepositorioUsers repositorioUsuario;

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

