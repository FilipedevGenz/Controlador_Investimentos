package org.controladorinvestimentos.controlador_investimentos.beans;

import org.controladorinvestimentos.controlador_investimentos.Banco.iRepositorioAtivos;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class Carteira {

    private static int Ncarteiras = 0;
    public int ID;
    private double ValorCarteira;
    private static Map<Ativo, Double> ativos = new HashMap<>(); // Alterado para HashMap

    public Carteira(int ID) {
        Ncarteiras++;
        this.ID = ID;
        this.ValorCarteira = 0.0;
    }

    public int getID() {
        return ID;
    }

    public double getValorCarteira() {
        return ValorCarteira;
    }

    public void adicionarAtivoNaCarteira(Ativo ativo, double quantidade) throws Exist {
        ativos.put(ativo, ativos.getOrDefault(ativo, 0.0) + quantidade);
        atualizarValorCarteira();
    }

    public void removerAtivo(Ativo ativo, double quantidade) {
        if (ativos.containsKey(ativo)) {
            double qtdAtual = ativos.get(ativo);
            if (qtdAtual <= quantidade) {
                ativos.remove(ativo);
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
