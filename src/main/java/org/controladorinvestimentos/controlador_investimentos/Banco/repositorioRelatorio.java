package org.controladorinvestimentos.controlador_investimentos.Banco;
import java.util.ArrayList;
import lombok.Data;
import org.controladorinvestimentos.controlador_investimentos.beans.relatorio;

@Data
public class repositorioRelatorio implements IrepositorioRelatorio {

     private final ArrayList <relatorio> relatorios = new ArrayList<>();

     public void addRelatorio (relatorio relatorio){
        relatorios.add(relatorio);
     }

     public Double getQuantidadeAtivo(String nameAtv) {
         return relatorios.stream().filter(relatorio -> relatorio.getNomeAtivo().
                         equals(nameAtv)).
                 mapToDouble(relatorio::getValorTotal).
                 sum();
     }

     public Double calcularValorAtual() {
         return relatorios.stream().mapToDouble(relatorio::getValorTotal).sum();
         }

     }
