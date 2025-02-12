package org.controladorinvestimentos.controlador_investimentos.beans;



import org.controladorinvestimentos.controlador_investimentos.Banco.iRepositorioAtivos;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;

import java.util.ArrayList;
import java.util.Map;

public class Carteira {

    //CORRIGIR FUNÇÃO LISTAR ATIVOS!!!

    private static int Ncarteiras = 0;
    public int ID;
    private double ValorCarteira;
    private static ArrayList<Ativo> ativos = new ArrayList<Ativo>();

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

            ativos.add(ativo);

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

    //CORRIGIR FUNÇÃO LISTAR ATIVOS!!!
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