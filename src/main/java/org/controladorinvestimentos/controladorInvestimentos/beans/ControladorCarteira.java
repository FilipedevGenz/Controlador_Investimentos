package org.controladorinvestimentos.controladorInvestimentos.beans;

import org.controladorinvestimentos.controladorInvestimentos.Banco.Interfaces.IrepositorioCarteira;
import org.controladorinvestimentos.controladorInvestimentos.Exceptions.Exist;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Carteira;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Usuario;
import java.util.List;

public class ControladorCarteira {

    public void novaCarteira(Usuario usuario, String carteiraID, String nomeCarteira, int periodoControlador) {
        Carteira novaCarteira = new Carteira(carteiraID, nomeCarteira, periodoControlador);
        usuario.getRepositorioCarteira().adicionarCarteira(novaCarteira);
    }

    public void removerCarteira(Usuario usuario, int carteiraID) throws Exist {
        usuario.getRepositorioCarteira().removerCarteira(carteiraID);
    }

    public Carteira getCarteira(Usuario usuario, int carteiraID) throws Exist {
        return usuario.getRepositorioCarteira().buscarCarteira(carteiraID);
    }

    public List<Carteira> getCarteiras(Usuario usuario) {
        return usuario.getRepositorioCarteira().listarCarteiras();
    }
}
