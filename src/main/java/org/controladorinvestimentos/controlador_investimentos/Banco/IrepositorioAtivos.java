package org.controladorinvestimentos.controlador_investimentos.Banco;

import org.controladorinvestimentos.controlador_investimentos.beans.Ativo;

public interface IrepositorioAtivos {
    public Ativo buscarAtivo(String nome);
    public void adicionarAtivo(String nome);
    public void removerAtivo(Ativo ativoEncontrado);
}
