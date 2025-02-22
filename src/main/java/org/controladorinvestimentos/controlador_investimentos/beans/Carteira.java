package org.controladorinvestimentos.controlador_investimentos.beans;

import org.controladorinvestimentos.controlador_investimentos.Banco.IrepositorioRelatorio;
import org.controladorinvestimentos.controlador_investimentos.Banco.RepositorioMovimentacoes;
import org.controladorinvestimentos.controlador_investimentos.Banco.RepositorioRelatorio;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;

public class Carteira {

    private final int ID;
    private double ValorCarteira;
    public RepositorioMovimentacoes repositorioMovimentacoes;
    public IrepositorioRelatorio repositorioRelatorio;

    public Carteira(int ID) {
        Ncarteiras++;
        this.ID = ID;
        this.ValorCarteira = 0.0;
    }


    public double getValorCarteira() {
        return ValorCarteira;
    }

    public void adicionarAtivoNaCarteira(ativo ativo, double quantidade) throws Exist {
        repositorioAtvCarteira.addToAtivosCarteira(ativo, quantidade);
        atualizarValorCarteira();
    }

    public void removerAtivo(ativo ativo, double quantidade) throws RuntimeException {
        if (repositorioAtvCarteira.getListaAtivos().contains(ativo)) {

            org.controladorinvestimentos.controlador_investimentos.beans.ativo ativoToremove = repositorioAtvCarteira.buscarAtivo(ativo);

            String nome = ativoToremove.getNome();

            double qtdAtual = repositorioRelatorio.getQuantidadeAtivo(nome);
            if (qtdAtual <= quantidade) {
                throw new RuntimeException();
            }
            else {
                try {
                    repositorioAtvCarteira.removeFromAtivosCarteira(ativo,quantidade);
                } catch (Exception e) {
                    //popUp de não existe esse ativo. NUNCA VAI CHEGAR AQUI
                    throw new RuntimeException(e);
                }
            }

            atualizarValorCarteira();
        }
    }

    public RepositorioRelatorio getRepositorioRelatorio() {
        return repositorioRelatorio;
    }

    public void atualizarValorCarteira() {
        ValorCarteira = repositorioRelatorio.calcularValorAtual();
    }
}
