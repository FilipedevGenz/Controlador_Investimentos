package org.controladorinvestimentos.controladorInvestimentos.Banco;

import org.controladorinvestimentos.controladorInvestimentos.beans.Carteira;
import org.controladorinvestimentos.controladorInvestimentos.Exceptions.Exist;
import java.util.List;

public interface IrepositorioCarteira {
    void adicionarCarteira(Carteira carteira);
    void removerCarteira(int carteiraID) throws Exist;
    Carteira buscarCarteira(int carteiraID) throws Exist;
    List<Carteira> listarCarteiras();
}
