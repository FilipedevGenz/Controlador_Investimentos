package org.controladorinvestimentos.controlador_investimentos.beans;

import org.controladorinvestimentos.controlador_investimentos.Banco.repositorioAtivosCarteira;
import org.controladorinvestimentos.controlador_investimentos.Banco.repositorioRelatorio;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;

public class carteira {

    public static int Ncarteiras = 0;
    public int ID;
    private double ValorCarteira;
    public repositorioAtivosCarteira repositorioAtvCarteira;
    private org.controladorinvestimentos.controlador_investimentos.Banco.repositorioRelatorio repositorioRelatorio;

    public carteira(int ID) {
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

    public org.controladorinvestimentos.controlador_investimentos.Banco.repositorioRelatorio getRepositorioRelatorio() {
        return repositorioRelatorio;
    }

    public void atualizarValorCarteira() {
        ValorCarteira = repositorioRelatorio.calcularValorAtual();
    }
}
