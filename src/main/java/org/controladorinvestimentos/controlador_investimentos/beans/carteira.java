package org.controladorinvestimentos.controlador_investimentos.beans;

import lombok.Getter;
import lombok.Setter;
import org.controladorinvestimentos.controlador_investimentos.Banco.IrepositorioRelatorio;
import org.controladorinvestimentos.controlador_investimentos.Banco.RepositorioMovimentacoes;
import org.controladorinvestimentos.controlador_investimentos.Banco.RepositorioRelatorio;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;

import java.io.IOException;

import static org.controladorinvestimentos.controlador_investimentos.beans.Simulador.atualizarValorCarteira;

public class Carteira {

    public final String ID;
    @Getter
    @Setter
    private double ValorCarteira;
    public RepositorioMovimentacoes repositorioMovimentacoes;
    public IrepositorioRelatorio repositorioRelatorio;
    //repositorioRelatorio é responsavel pelas relatorios da carteira,
    // RepositorioMovimentacoes é responsável pelas movimentações globais de todas as carteiras


    public Carteira(String ID) {
        this.ID = ID;
        this.ValorCarteira = 0.0;
        this.repositorioRelatorio = new RepositorioRelatorio();
    }


    public void adicionarAtivoNaCarteira(String codeAtv, double quantidade) throws IOException {
        Relatorio relatorio = new Relatorio(codeAtv,quantidade);
        repositorioRelatorio.addRelatorio(relatorio);
        repositorioMovimentacoes.addRelatorio(relatorio, this);
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

    public void atualizarValorCarteira() {
        ValorCarteira = repositorioRelatorio.calcularValorAtual();
    }

}
