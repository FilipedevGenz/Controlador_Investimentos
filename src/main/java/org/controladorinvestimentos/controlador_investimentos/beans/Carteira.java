package org.controladorinvestimentos.controlador_investimentos.beans;

import org.controladorinvestimentos.controlador_investimentos.Banco.IrepositorioRelatorio;
import org.controladorinvestimentos.controlador_investimentos.Banco.RepositorioMovimentacoes;
import org.controladorinvestimentos.controlador_investimentos.Banco.RepositorioRelatorio;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;

public class Carteira {

    public final String ID;
    private double ValorCarteira;

    public RepositorioMovimentacoes repositorioMovimentacoes;
    public IrepositorioRelatorio repositorioRelatorio;


    public Carteira(String ID) {
        this.ID = ID;
        this.ValorCarteira = 0.0;
        this.repositorioMovimentacoes = new RepositorioMovimentacoes();
        this.repositorioRelatorio = new RepositorioRelatorio();
    }

    public double getValorCarteira() {
        return ValorCarteira;
    }

    public void adicionarAtivoNaCarteira(Ativo ativo, double quantidade) throws Exist {
        repositorioMovimentacoes.addToAtivosCarteira(ativo, quantidade);
        atualizarValorCarteira();
    }

    public void removerAtivo(Ativo ativo, double quantidade) throws RuntimeException {
        if (repositorioMovimentacoes.getListaAtivos().contains(ativo)) {
            Ativo ativoToRemove = repositorioMovimentacoes.removeFromAtivosCarteira(ativo, quantidade);
            String nome = ativoToRemove.getNome();
            double qtdAtual = repositorioRelatorio.getQuantidadeAtivo(nome);

            if (qtdAtual <= quantidade) {
                throw new RuntimeException("Quantidade insuficiente para remover.");
            } else {
                try {
                    repositorioMovimentacoes.removeFromAtivosCarteira(ativo, quantidade);
                } catch (Exception e) {
                    throw new RuntimeException("Erro ao remover ativo.", e);
                }
            }
            atualizarValorCarteira();
        }
    }

    public IrepositorioRelatorio getRepositorioRelatorio() {
        return repositorioRelatorio;
    }

    public void atualizarValorCarteira() {
        ValorCarteira = repositorioRelatorio.calcularValorAtual();
    }



    public String getID() {
        return ID;
    }
}
