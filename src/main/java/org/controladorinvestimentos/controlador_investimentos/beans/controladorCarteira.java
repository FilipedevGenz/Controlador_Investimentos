package org.controladorinvestimentos.controlador_investimentos.beans;

import org.controladorinvestimentos.controlador_investimentos.Banco.IrepositorioCarteira;
import org.controladorinvestimentos.controlador_investimentos.Banco.repositorioCarteira;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;

import java.util.List;

public class controladorCarteira {

    private final IrepositorioCarteira repositorioCarteira;

    public controladorCarteira() {
        this.repositorioCarteira = new repositorioCarteira();
    }

    public void NovaCarteira(String nomeCarteira) {
        Carteira novaCarteira = new Carteira(nomeCarteira);
        repositorioCarteira.adicionarCarteira(novaCarteira);
    }

    public void RemoverCarteira(int carteiraID) throws Exist {
        repositorioCarteira.removerCarteira(carteiraID);
    }

    public Carteira GetCarteira(int carteiraID) throws Exist {
        return repositorioCarteira.buscarCarteira(carteiraID);
    }

    public List<Carteira> GetCarteiras() {
        return repositorioCarteira.listarCarteiras();
    }
}
