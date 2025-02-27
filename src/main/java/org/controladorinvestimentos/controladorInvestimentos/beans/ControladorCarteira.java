package org.controladorinvestimentos.controladorInvestimentos.beans;

import org.controladorinvestimentos.controladorInvestimentos.Banco.Interfaces.IrepositorioCarteira;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioCarteira;
import org.controladorinvestimentos.controladorInvestimentos.Exceptions.Exist;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Carteira;

import java.util.List;

public class ControladorCarteira {

    private static ControladorCarteira instance;
    private final IrepositorioCarteira repositorioCarteira;

    private ControladorCarteira() {
        // Usa a instância global do repositório
        this.repositorioCarteira = RepositorioCarteira.getInstance();
    }

    public static synchronized ControladorCarteira getInstance() {
        if (instance == null) {
            instance = new ControladorCarteira();
        }
        return instance;
    }

    public void novaCarteira(String carteiraID, String nomeCarteira, int periodoControlador) {
        Carteira novaCarteira = new Carteira(carteiraID, nomeCarteira, periodoControlador);
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
