package org.controladorinvestimentos.controlador_investimentos.Banco;

import org.controladorinvestimentos.controlador_investimentos.beans.carteira;

public interface IrepositorioCarteira {

     void adicionarCarteira(carteira carteira);
     void removerCarteira(carteira carteira);
     carteira buscarCarteira(carteira carteira) throws Exception;

}
