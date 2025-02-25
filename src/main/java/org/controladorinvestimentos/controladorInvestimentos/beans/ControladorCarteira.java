package org.controladorinvestimentos.controladorInvestimentos.beans;

import org.controladorinvestimentos.controladorInvestimentos.Banco.IrepositorioCarteira;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioCarteira;
import org.controladorinvestimentos.controladorInvestimentos.Exceptions.Exist;

import java.util.List;

public class ControladorCarteira {

    private final IrepositorioCarteira repositorioCarteira;

    public ControladorCarteira() {
        this.repositorioCarteira = new RepositorioCarteira();
    }

    public void novaCarteira(String nomeCarteira) {
        Carteira novaCarteira = new Carteira(nomeCarteira);
        repositorioCarteira.adicionarCarteira(novaCarteira);
    }

    public void removerCarteira(int carteiraID) throws Exist {
        repositorioCarteira.removerCarteira(carteiraID);
    }

    public Carteira getCarteira(int carteiraID) throws Exist {
        return repositorioCarteira.buscarCarteira(carteiraID);
    }

    public List<Carteira> getCarteiras() {
        return repositorioCarteira.listarCarteiras();
    }
}
