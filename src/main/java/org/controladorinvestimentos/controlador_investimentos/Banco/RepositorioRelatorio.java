package org.controladorinvestimentos.controlador_investimentos.Banco;

import java.util.ArrayList;

import org.controladorinvestimentos.controlador_investimentos.beans.relatorio;

public class RepositorioRelatorio implements irepositorioRelatorio {

     private ArrayList <relatorio> relatorios = new ArrayList<>();

     public boolean existe = false; 

     RepositorioRelatorio () {

        existe = true;
     }

     public void addRelatorio (relatorio relatorio) {

        relatorios.add(relatorio);
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
