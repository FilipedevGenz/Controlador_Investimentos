package org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras;

import lombok.Getter;
import lombok.Setter;
import org.controladorinvestimentos.controladorInvestimentos.Banco.Interfaces.IrepositorioRelatorio;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioAtivos;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioAtivosCarteira;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioMovimentacoes;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioRelatorio;
import org.controladorinvestimentos.controladorInvestimentos.beans.ControladorRelatorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Carteira {

    @Getter @Setter
    private final String IDcarteira;
    @Getter @Setter
    private final String nomeCarteira;
    private final int periodoAssociadoIns;
    @Getter @Setter
    private double valorCarteira;

    private RepositorioMovimentacoes repositorioMovimentacoes;
    @Getter
    private IrepositorioRelatorio repositorioRelatorio;

    private RepositorioAtivosCarteira ativos;

    public Carteira(String IDcarteira, String nomeCarteira, int periodoAssociadoIns) {
        this.valorCarteira = 0.0;
        this.repositorioRelatorio = new RepositorioRelatorio();
        this.nomeCarteira = nomeCarteira;
        this.IDcarteira = IDcarteira;
        this.periodoAssociadoIns = periodoAssociadoIns;
        this.ativos = new RepositorioAtivosCarteira();
    }

    public void adicionarAtivoNaCarteira(String nomeAtivo, Double quantidade, double preco) {

        try {
                ativos.adicionarAtivo(new Ativo(nomeAtivo, preco), quantidade);
                Relatorio relatorio = ControladorRelatorio.criarRelatorio(nomeAtivo,quantidade);
                repositorioRelatorio.addRelatorio(relatorio);

        } catch (Exception e) {
            System.out.println("Erro ao adicionar ativo: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void removerAtivo(String codeAtv, double quantidade) {
        try {
            Relatorio relatorio = repositorioRelatorio.buscarRelatorio(codeAtv);

            if (relatorio == null) {
                System.out.println("Erro: Ativo " + codeAtv + " não encontrado na carteira.");
                return;
            }

            repositorioRelatorio.removerRelatorio(codeAtv, quantidade);
            atualizarValorCarteira();
            System.out.println("Ativo " + codeAtv + " atualizado/removido com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao remover ativo: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void atualizarValorCarteira() {
        valorCarteira = repositorioRelatorio.calcularValorAtual();
    }
}
