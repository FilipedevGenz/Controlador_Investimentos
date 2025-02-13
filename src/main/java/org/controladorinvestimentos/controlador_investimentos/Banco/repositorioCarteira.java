package org.controladorinvestimentos.controlador_investimentos.Banco;


import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.beans.ativo;
import org.controladorinvestimentos.controlador_investimentos.beans.carteira;

import java.util.ArrayList;

// A classe repositorioCarteira tem forte relação com conta.
public class repositorioCarteira implements IrepositorioCarteira {
//remover o singleton
    static final ArrayList<org.controladorinvestimentos.controlador_investimentos.beans.carteira> CARTEIRAS = new ArrayList<>();

     private static final ArrayList<ativo> carteira = new ArrayList<>();

        private static repositorioCarteira instance;

        private repositorioCarteira() {}

    public static synchronized repositorioCarteira getInstance(){

        if(instance == null){
            synchronized (repositorioUsers.class){
                if(instance == null){
                    instance = new repositorioCarteira();
                }
            }
        }
        return instance;
    }

    public void adicionarCarteira(org.controladorinvestimentos.controlador_investimentos.beans.carteira carteira){
        //na fase de gui, receber o nome como parametro e alterar
        // para atribuiur o nome da carteira recebida com parametro

        org.controladorinvestimentos.controlador_investimentos.beans.carteira _NewCarteira = new carteira(carteira.ID);
        CARTEIRAS.add(_NewCarteira);
    }

    public void removerCarteira(org.controladorinvestimentos.controlador_investimentos.beans.carteira _carteira){
        CARTEIRAS.remove(_carteira);
    }

    public org.controladorinvestimentos.controlador_investimentos.beans.carteira buscarCarteira(org.controladorinvestimentos.controlador_investimentos.beans.carteira carteira) throws Exist {
        for(org.controladorinvestimentos.controlador_investimentos.beans.carteira u : CARTEIRAS){
            if(u.ID == carteira.ID){
                return u;
            }
        }
        throw new Exist("carteira nao encontrada");
    }

   
}