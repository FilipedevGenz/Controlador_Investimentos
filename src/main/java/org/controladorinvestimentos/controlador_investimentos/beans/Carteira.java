package org.controladorinvestimentos.controlador_investimentos.beans;



import org.controladorinvestimentos.controlador_investimentos.Banco.iRepositorioAtivos;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;

import java.util.HashMap;
import java.util.Map;

public class Carteira {

    private static int Ncarteiras = 0;
    public int ID;
    private double ValorCarteira;
    private Map<Ativo, Double> ativos; // Mapeia cada Ativo para sua quantidade.
    private static iRepositorioAtivos repositorioAtivos;

    public Carteira(int ID) {
        Ncarteiras++;
        this.ID = ID;
        this.ativos = new HashMap<>();
        this.ValorCarteira = 0.0;
    }

    public int getID() {
        return ID;
    }

    public double getValorCarteira() {
        return ValorCarteira;
    }

    public Map<Ativo, Double> getAtivos() {
        return ativos;
    }

    public void adicionarAtivo(Ativo ativo, double quantidade) throws Exist {
        try {
            // Tenta buscar o ativo no repositório
            if (repositorioAtivos.buscarAtivo(ativo.getNome()) == null) {
                throw new Exist("O ativo não existe no sistema.");
            }
            // Adiciona o ativo à carteira
            ativos.put(ativo, ativos.getOrDefault(ativo, 0.0) + quantidade);
            atualizarValorCarteira();
        } catch (Exception e) {
            throw new Exist("Erro ao adicionar ativo: " + e.getMessage());
        }
    }



    public void removerAtivo(Ativo ativo, double quantidade) {
        if (ativos.containsKey(ativo)) {
            double qtdAtual = ativos.get(ativo);
            if (qtdAtual <= quantidade) {
                ativos.remove(ativo); // Remove completamente se a quantidade for zero ou menor.
            } else {
                ativos.put(ativo, qtdAtual - quantidade);
            }
            atualizarValorCarteira();
        }
    }

    private void atualizarValorCarteira() {
        ValorCarteira = 0.0;
        for (Map.Entry<Ativo, Double> entry : ativos.entrySet()) {
            ValorCarteira += entry.getKey().getPreco() * entry.getValue();
        }
    }

    public double consultarQuantidade(String nomeAtivo) {
        for (Ativo ativo : ativos.keySet()) {
            if (ativo.getNome().equals(nomeAtivo)) {
                return ativos.get(ativo);
            }
        }
        return 0.0; // Retorna 0 se o ativo não estiver na carteira.
    }

    public String listarAtivos() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Ativo, Double> entry : ativos.entrySet()) {
            sb.append("Ativo: ").append(entry.getKey().getNome())
                    .append(", Quantidade: ").append(entry.getValue())
                    .append(", Preço: ").append(entry.getKey().getPreco()).append("\n");
        }
        return sb.toString();
    }
}

