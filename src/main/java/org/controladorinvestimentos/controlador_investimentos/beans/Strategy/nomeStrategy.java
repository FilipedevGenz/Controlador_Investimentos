package org.controladorinvestimentos.controlador_investimentos.beans.Strategy;



import org.controladorinvestimentos.controlador_investimentos.Banco.iRepositorioUsers;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.beans.Usuario;


import java.util.Scanner;

public class nomeStrategy implements updateStrategy{

    private static iRepositorioUsers repositorioUsuario;

    @Override
    public void updateInfo(Usuario usuario) {
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
