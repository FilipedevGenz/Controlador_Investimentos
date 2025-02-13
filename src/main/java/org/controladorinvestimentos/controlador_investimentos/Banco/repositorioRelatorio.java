package org.controladorinvestimentos.controlador_investimentos.Banco;

import java.util.ArrayList;

import org.controladorinvestimentos.controlador_investimentos.beans.relatorio;

public class repositorioRelatorio implements IrepositorioRelatorio {

     private final ArrayList <relatorio> relatorios = new ArrayList<>();

     public boolean existe = false; 

     repositorioRelatorio() {

        existe = true;
     }

     public void addRelatorio (relatorio relatorio) {

        relatorios.add(relatorio);
     }

     public Double getQuantidadeAtivo(String nameAtv) {
         Double qntProcurado = null;
         for (relatorio relatorio1 : relatorios) {
             if(relatorio1.getNomeAtivo().equals(nameAtv)) {
                 qntProcurado += relatorio1.getQuantidade();
             }
         }
         return qntProcurado;
     }

     public Double calcularValorAtual() {

    double valorAtual = 0;

        if (relatorios == null) {
            return valorAtual;
        }

        for (relatorio i: relatorios) {

         valorAtual = valorAtual + i.getvalorTotal();   

        }

        return valorAtual;

     }
     

    
}
