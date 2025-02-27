
/*
package org.controladorinvestimentos.controladorInvestimentos.beans.Strategy;



import org.controladorinvestimentos.controladorInvestimentos.Banco.Interfaces.IrepositorioUsuario;
import org.controladorinvestimentos.controladorInvestimentos.Exceptions.Exist;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Usuario;


import java.util.Scanner;

public class nomeStrategy implements updateStrategy{

    private static IrepositorioUsuario repositorioUsuario;

    @Override
    public void updateInfo(Usuario usuario) {
        try {
            System.out.println("Digite o novo nomeCarteira:");
            Scanner ler = new Scanner(System.in);
            String NewInfo = ler.nextLine();
            repositorioUsuario.AlterarNome(NewInfo,usuario);
        }catch (Exist e){
            throw new Exist("Erro ao alterar Nome, tente novamente.");
        }
    }
}

 */
