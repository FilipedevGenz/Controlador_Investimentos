package org.controladorinvestimentos.controlador_investimentos.Banco;


import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.beans.carteira;
import org.controladorinvestimentos.controlador_investimentos.beans.conta;

import java.util.ArrayList;

// A classe repositorioCarteira tem forte relação com conta.
public class repositorioCarteira implements IrepositorioCarteira {
//remover o singleton
     conta dono;
     private final ArrayList<carteira> carteiras = new ArrayList<>();

        private repositorioCarteira instance;

        private repositorioCarteira(conta dono) {
            this.dono = dono;
        }

    public void adicionarCarteira(carteira carteira){
        //na fase de gui, receber o nome como parametro e alterar
        // para atribuiur o nome da carteira recebida com parametro

        carteira newCarteira = new carteira(carteira.Ncarteiras);
        carteiras.add(newCarteira);
    }

    public void removerCarteira(carteira carteira){
        carteiras.remove(carteira);
    }

    public carteira buscarCarteira(carteira carteira) throws Exist {
        for(carteira u : carteiras){
            if(u.ID == carteira.ID){
                return u;
            }
        }
        throw new Exist("carteira nao encontrada");
    }

    public ArrayList<carteira> getCarteiras(){
            return carteiras;
    }

   
}