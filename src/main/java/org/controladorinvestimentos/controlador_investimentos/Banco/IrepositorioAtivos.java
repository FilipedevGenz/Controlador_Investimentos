package org.controladorinvestimentos.controlador_investimentos.Banco;

import org.controladorinvestimentos.controlador_investimentos.beans.Ativo;

import java.io.IOException;

public interface IrepositorioAtivos {
    public Ativo buscarAtivo(String nome);
    public void adicionarAtivo(String nome) throws IOException;
    public void removerAtivo(Ativo ativoEncontrado);
}
