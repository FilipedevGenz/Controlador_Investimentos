package org.controladorinvestimentos.controladorInvestimentos.beans;

import lombok.Getter;
import lombok.Setter;
import org.controladorinvestimentos.controladorInvestimentos.Banco.IrepositorioRelatorio;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioMovimentacoes;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioRelatorio;

import java.io.IOException;

public class Carteira {

    @Getter @Setter
    public final String IDcarteira;
    @Getter @Setter
    public final String nomeCarteira;
    private double valorCarteira;
    public RepositorioMovimentacoes repositorioMovimentacoes;
    public IrepositorioRelatorio repositorioRelatorio;
    //repositorioRelatorio é responsavel pelas relatorios da carteira,
    // RepositorioMovimentacoes é responsável pelas movimentações globais de todas as carteiras


    public Carteira(String IDcarteira, String nomeCarteira) {
        this.valorCarteira = 0.0;
        this.repositorioRelatorio = new RepositorioRelatorio();
        this.nomeCarteira = nomeCarteira;
        this.IDcarteira = IDcarteira;
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
        valorCarteira = repositorioRelatorio.calcularValorAtual();
    }
}




