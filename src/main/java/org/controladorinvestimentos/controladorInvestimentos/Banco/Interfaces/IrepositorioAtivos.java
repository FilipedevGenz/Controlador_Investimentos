package org.controladorinvestimentos.controladorInvestimentos.Banco.Interfaces;

import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Ativo;

import java.io.IOException;

public interface IrepositorioAtivos {
    Ativo buscarAtivo(String nome);
    void adicionarAtivo(String nome) throws IOException;

    void adicionarAtivo(String code, double preco, int periodoAssociado) throws IOException;

    void removerAtivo(Ativo ativoEncontrado);
    void adicionarAtivo(String code, double preco) throws IOException;
}
