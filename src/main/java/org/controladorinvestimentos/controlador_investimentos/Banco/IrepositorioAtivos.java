package org.controladorinvestimentos.controlador_investimentos.Banco;

import org.controladorinvestimentos.controlador_investimentos.beans.ativo;

public interface IrepositorioAtivos {

    public void adicionarAtivo(String nome,double preco);
    public void removerAtivo(ativo ativo);
    public ativo buscarAtivo(String nome) throws Exception;
    public void AlterarPreco(double preco, ativo ativo);

}
