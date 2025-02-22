package org.controladorinvestimentos.controlador_investimentos.Banco;

import org.controladorinvestimentos.controlador_investimentos.beans.Carteira;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import java.util.List;

public interface IrepositorioCarteira {
    void adicionarCarteira(Carteira carteira);
    void removerCarteira(int carteiraID) throws Exist;
    Carteira buscarCarteira(int carteiraID) throws Exist;
    List<Carteira> listarCarteiras();
}
