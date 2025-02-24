package org.controladorinvestimentos.controlador_investimentos.beans;

import org.controladorinvestimentos.controlador_investimentos.Banco.IrepositorioCarteira;
import org.controladorinvestimentos.controlador_investimentos.Banco.RepositorioCarteira;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;

import java.util.List;

public class ControladorCarteira {

    private final IrepositorioCarteira repositorioCarteira;

    public ControladorCarteira() {
        this.repositorioCarteira = new RepositorioCarteira();
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
