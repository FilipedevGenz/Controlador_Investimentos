package org.controladorinvestimentos.controlador_investimentos.Banco;
import java.util.ArrayList;
import lombok.Data;
import org.controladorinvestimentos.controlador_investimentos.beans.Carteira;
import org.controladorinvestimentos.controlador_investimentos.beans.Relatorio;

@Data
public class RepositorioRelatorio implements IrepositorioRelatorio {

     private Carteira dono;
     private final ArrayList <Relatorio> Relatorios = new ArrayList<>();

     public void addRelatorio (Relatorio relatorio){
        Relatorios.add(relatorio);
     }

     public Double getQuantidadeAtivo(String nameAtv) {
         return Relatorios.stream().filter(relatorio -> relatorio.getNomeAtivo().
                         equals(nameAtv)).mapToDouble(Relatorio::getQuantidade).
                 sum();
     }

     public Double valorDeCompraCarteira(){
         //sera usado para comparar com o valor atual e obter a valorizacao
         return Relatorios.stream().mapToDouble(org.controladorinvestimentos.controlador_investimentos.beans.Relatorio::getValorTotal).sum();
         }

    @Override
    public double calcularValorAtual() {
        return 2;
    }


    //implementaro metodo de valorizaca
     }
