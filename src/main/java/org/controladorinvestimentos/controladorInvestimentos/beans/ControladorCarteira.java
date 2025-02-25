package org.controladorinvestimentos.controladorInvestimentos.beans;

import org.controladorinvestimentos.controladorInvestimentos.Banco.IrepositorioCarteira;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioCarteira;
import org.controladorinvestimentos.controladorInvestimentos.Exceptions.Exist;

import java.util.List;

public class ControladorCarteira {

    private final IrepositorioCarteira repositorioCarteiraAcessoGlobal; // Cria um singleton

    public ControladorCarteira() {
        this.repositorioCarteiraAcessoGlobal = new RepositorioCarteira();
    }

    public void novaCarteira(String nomeCarteira) {
        Carteira novaCarteira = new Carteira(nomeCarteira);
        repositorioCarteiraAcessoGlobal.adicionarCarteira(novaCarteira);
    }

    public void removerCarteira(int carteiraID) throws Exist {
        repositorioCarteiraAcessoGlobal.removerCarteira(carteiraID);
    }

    public Carteira getCarteira(int carteiraID) throws Exist {
        return repositorioCarteiraAcessoGlobal.buscarCarteira(carteiraID);
    }

    public List<Carteira> getCarteiras() {
        return repositorioCarteiraAcessoGlobal.listarCarteiras();
    }
}
