package org.controladorinvestimentos.controlador_investimentos.beans.Strategy;



import org.controladorinvestimentos.controlador_investimentos.Banco.IrepositorioUsers;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.beans.usuario;


import java.util.Scanner;

public class nomeStrategy implements updateStrategy{

    private static IrepositorioUsers repositorioUsuario;

    @Override
    public void updateInfo(usuario usuario) {
        try {
            System.out.println("Digite o novo nome:");
            Scanner ler = new Scanner(System.in);
            String NewInfo = ler.nextLine();
            repositorioUsuario.AlterarNome(NewInfo,usuario);
        }catch (Exist e){
            throw new Exist("Erro ao alterar Nome, tente novamente.");
        }
    }
}
