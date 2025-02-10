package org.controladorinvestimentos.controlador_investimentos.Banco;

import org.controladorinvestimentos.controlador_investimentos.beans.Carteira;

public interface iRepositorioCarteira {

     void adicionarCarteira(Carteira carteira);
     void removerCarteira(Carteira carteira);
     Carteira buscarCarteira(Carteira carteira) throws Exception;

}
