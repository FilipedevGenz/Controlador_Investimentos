package org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras;

import lombok.Getter;
import lombok.Setter;
import org.controladorinvestimentos.controladorInvestimentos.Banco.Interfaces.IrepositorioRelatorio;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioAtivos;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioMovimentacoes;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioRelatorio;

import java.util.ArrayList;
import java.util.List;

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

    private List<Ativo> ativos;

    public Carteira(String IDcarteira, String nomeCarteira, int periodoAssociadoIns) {
        this.valorCarteira = 0.0;
        this.repositorioRelatorio = new RepositorioRelatorio();
        this.nomeCarteira = nomeCarteira;
        this.IDcarteira = IDcarteira;
        this.periodoAssociadoIns = periodoAssociadoIns;
        this.ativos = new ArrayList<>();
    }

    public void adicionarAtivoNaCarteira(String nomeAtivo, int quantidade, int preco) {
        System.out.println("Tentando adicionar ativo na carteira: " + nomeAtivo);

        try {

            Ativo ativo = RepositorioAtivos.getInstance().buscarAtivo(nomeAtivo);

            if (ativo == null) {
                System.out.println("Erro: Ativo " + nomeAtivo + " não encontrado no repositório.");
                return;
            }

            System.out.println("Ativo encontrado no repositório! Adicionando à carteira...");

            // 🔹 Verifica se o ativo já existe na carteira para evitar duplicação
            boolean ativoExiste = ativos.stream().anyMatch(a -> a.getNome().equals(nomeAtivo));
            if (!ativoExiste) {
                this.ativos.add(new Ativo(nomeAtivo, preco));
                System.out.println("Ativo adicionado com sucesso!");
            } else {
                System.out.println("Ativo já existe na carteira. Nenhuma alteração feita.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao adicionar ativo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void removerAtivo(String codeAtv, double quantidade) {
        try {
            Relatorio toRemove = RepositorioMovimentacoes.searchRelatorio(codeAtv, quantidade, this);

            if (toRemove != null) {
                if(RepositorioMovimentacoes.removeRelatorio(toRemove, this)){
                    repositorioRelatorio.removerRelatorio(toRemove);
                    atualizarValorCarteira();
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Erro ao remover ativo: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void atualizarValorCarteira() {
        valorCarteira = repositorioRelatorio.calcularValorAtual();
    }
}
