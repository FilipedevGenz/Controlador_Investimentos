package org.controladorinvestimentos.controladorInvestimentos.Banco;

import org.controladorinvestimentos.controladorInvestimentos.beans.Ativo;

import java.io.IOException;

public interface IrepositorioAtivos {
    Ativo buscarAtivo(String nome);
    void adicionarAtivo(String nome) throws IOException;
    void removerAtivo(Ativo ativoEncontrado);
    void adicionarAtivo(String code, double preco) throws IOException;
}
