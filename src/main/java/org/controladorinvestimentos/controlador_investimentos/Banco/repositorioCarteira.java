package org.controladorinvestimentos.controlador_investimentos.Banco;


import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.beans.Carteira;
import org.controladorinvestimentos.controlador_investimentos.beans.conta;

import java.util.ArrayList;

// A classe repositorioCarteira tem forte relação com conta.
public class repositorioCarteira implements IrepositorioCarteira {
//remover o singleton
     conta dono;
     private final ArrayList<Carteira> Carteiras = new ArrayList<>();

        private repositorioCarteira instance;

        public repositorioCarteira(conta dono) {
            this.dono = dono;
        }

    public void adicionarCarteira(Carteira carteira){
        //na fase de gui, receber o nome como parametro e alterar
        // para atribuiur o nome da carteira recebida com parametro

        Carteira newCarteira = new Carteira(carteira.Ncarteiras);
        Carteiras.add(newCarteira);
        newCarteira.repositorioAtvCarteira = new RepositorioMovimetacoes();
        RepositorioRelatorio carteiraRelatorio = new RepositorioRelatorio();
    }

    public void removerCarteira(Carteira carteira){
        Carteiras.remove(carteira);
    }

    public Carteira buscarCarteira(Carteira carteira) throws Exist {
        for(Carteira u : Carteiras){
            if(u.ID == carteira.ID){
                return u;
            }
        }
        throw new Exist("carteira nao encontrada");
    }

    public ArrayList<Carteira> getCarteiras(){
            return Carteiras;
    }

   
}