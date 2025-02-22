package org.controladorinvestimentos.controlador_investimentos.Banco;

import lombok.Getter;
import lombok.Setter;
import org.controladorinvestimentos.controlador_investimentos.beans.Ativo;
import org.controladorinvestimentos.controlador_investimentos.beans.Relatorio;
import org.controladorinvestimentos.controlador_investimentos.beans.Carteira;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class RepositorioMovimentacoes {

    // Criar um IrepositorioMovimentacoes

    private static final ArrayList<Map<Relatorio, Carteira>> listaAtivos = new ArrayList<>();
    private final Map<Ativo, Double> ativosCarteira;

    public RepositorioMovimentacoes() {
        this.ativosCarteira = new HashMap<>();
    }

    // Adiciona um relatório a uma carteira
    public void AddRelatorio(Relatorio relatorio, Carteira carteira) {
        Map<Relatorio, Carteira> relatorioCarteira = new HashMap<>();
        relatorioCarteira.put(relatorio, carteira);
        listaAtivos.add(relatorioCarteira);
    }

    // Remove um relatório de uma carteira
    public boolean RemoveRelatorio(Relatorio relatorio, Carteira carteira) {
        Map<Relatorio, Carteira> relatorioCarteira = new HashMap<>();
        relatorioCarteira.put(relatorio, carteira);
        return listaAtivos.remove(relatorioCarteira);
    }

    // Retorna a lista de relatórios associados a uma carteira específica
    public ArrayList<Relatorio> getRelatorios(Relatorio relatorio, Carteira carteira) {
        ArrayList<Relatorio> toReturn = new ArrayList<>();

        for (Map<Relatorio, Carteira> map : listaAtivos) {
            for (Map.Entry<Relatorio, Carteira> entry : map.entrySet()) {
                Relatorio relatorio2 = entry.getKey();
                Carteira carteira2 = entry.getValue();

                // Verifica se o nome do ativo do relatório é igual e a carteira também corresponde
                if (relatorio2.getNomeAtivo().equals(relatorio.getNomeAtivo()) && carteira2.equals(carteira)) {
                    toReturn.add(relatorio2);
                }
            }
        }
        return toReturn;
    }

    // Adiciona um ativo à carteira
    public void addToAtivosCarteira(Ativo ativo, double quantidade) {
        if (ativosCarteira.containsKey(ativo)) {
            ativosCarteira.put(ativo, ativosCarteira.get(ativo) + quantidade);
        } else {
            ativosCarteira.put(ativo, quantidade);
        }
    }

    // Remove um ativo da carteira
    public Ativo removeFromAtivosCarteira(Ativo ativo, double quantidade) throws Exist {
        if (!ativosCarteira.containsKey(ativo)) {
            throw new Exist("Ativo não encontrado na carteira.");
        }

        double qtdAtual = ativosCarteira.get(ativo);
        if (qtdAtual < quantidade) {
            throw new Exist("Quantidade insuficiente para remover.");
        }

        if (qtdAtual == quantidade) {
            ativosCarteira.remove(ativo);
        } else {
            ativosCarteira.put(ativo, qtdAtual - quantidade);
        }
        return ativo;
    }

    // Retorna todos os ativos da carteira
    public Set<Ativo> getListaAtivos() {
        return ativosCarteira.keySet();
    }

    // Busca um ativo na carteira pelo nome
    public Ativo buscarAtivo(String nome) throws Exist {
        for (Ativo a : ativosCarteira.keySet()) {
            if (a.getNome().equals(nome)) {
                return a;
            }
        }
        throw new Exist("Ativo não encontrado.");
    }
}
