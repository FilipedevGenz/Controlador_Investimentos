package org.controladorinvestimentos.controlador_investimentos.beans;

import lombok.Getter;
import lombok.Setter;
import org.controladorinvestimentos.controlador_investimentos.Banco.IrepositorioRelatorio;
import org.controladorinvestimentos.controlador_investimentos.Banco.RepositorioMovimentacoes;
import org.controladorinvestimentos.controlador_investimentos.Banco.RepositorioRelatorio;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;

import java.io.IOException;
import java.util.ArrayList;

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
        RepositorioMovimentacoes.getInstance().addRelatorio(relatorio, this);
        atualizarValorCarteira();
    }

    public void removerAtivo(String codeAtv, double quantidade) throws RuntimeException {
            RepositorioMovimentacoes.getInstance();

            try {
                Relatorio toRemove = RepositorioMovimentacoes.searchRelatorio(codeAtv, quantidade, this);

                if (toRemove != null) {
                    if(RepositorioMovimentacoes.removeRelatorio(toRemove, this)){
                        repositorioRelatorio.removerRelatorio(toRemove);
                        atualizarValorCarteira();
                    }
                }
            }catch (RuntimeException e) {
                throw new RuntimeException();
            }

        }

    public void atualizarValorCarteira() {
        ValorCarteira = repositorioRelatorio.calcularValorAtual();
    }
}



