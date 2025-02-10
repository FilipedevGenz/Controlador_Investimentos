package org.controladorinvestimentos.controlador_investimentos.Banco;


import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.beans.Carteira;

import java.util.ArrayList;
import java.util.List;

// A classe repositorioCarteira tem forte relação com conta.
public class RepositorioCarteira implements iRepositorioCarteira {

    static final ArrayList<Carteira> carteiras = new ArrayList<>();

    public void adicionarCarteira(Carteira carteira){
        //na fase de gui, receber o nome como parametro e alterar
        // para atribuiur o nome da carteira recebida com parametro

        Carteira _NewCarteira = new Carteira(carteira.ID);
        carteiras.add(_NewCarteira);
    }

    public void removerCarteira(Carteira _carteira){
        carteiras.remove(_carteira);
    }

    public Carteira buscarCarteira(Carteira carteira) throws Exist {
        for(Carteira u : carteiras){
            if(u.ID == carteira.ID){
                return u;
            }
        }
        throw new Exist("carteira nao encontrada");
    }

    public List<Carteira> buscarCarteirasComAtivo(String nomeAtivo, List<Carteira> todasCarteiras) {
        List<Carteira> carteirasComAtivo = new ArrayList<>();
        for (Carteira carteira : todasCarteiras) {
            if (carteira.getAtivos().keySet().stream().anyMatch(a -> a.getNome().equals(nomeAtivo))) {
                carteirasComAtivo.add(carteira);
            }
        }
        return carteirasComAtivo;
    }
}
