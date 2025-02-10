package org.controladorinvestimentos.controlador_investimentos.Banco;

import org.controladorinvestimentos.controlador_investimentos.beans.Ativo;

import java.util.ArrayList;

public interface iRepositorioAtivos {

    public void adicionarAtivo(String nome,double preco);
    public void removerAtivo(Ativo ativo);
    public Ativo buscarAtivo(String nome) throws Exception;
    public void AlterarPreco(double preco,Ativo ativo);

}
