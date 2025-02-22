package org.controladorinvestimentos.controlador_investimentos.Banco;
import java.util.ArrayList;
import lombok.Data;
import lombok.Getter;
import org.controladorinvestimentos.controlador_investimentos.beans.Carteira;
import org.controladorinvestimentos.controlador_investimentos.beans.Relatorio;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class RepositorioRelatorio implements IrepositorioRelatorio {

     private Carteira dono;
     @Getter
     private final ArrayList <Relatorio> Relatorios = new ArrayList<>();

     public void addRelatorio (Relatorio relatorio){
        Relatorios.add(relatorio);
     }

     public Double getQuantidadeAtivo(String codigo) {
         return Relatorios.stream().filter(relatorio -> relatorio.getCodigo().
                         equals(codigo)).mapToDouble(Relatorio::getQuantidade).
                 sum();
     }

     public Double valorDeCompraCarteira(){
         //sera usado para comparar com o valor atual e obter a valorizacao
         return Relatorios.stream().mapToDouble(org.controladorinvestimentos.controlador_investimentos.beans.Relatorio::getValorTotal).sum();
         }


         public Double valorMedioDeCompra(String codigo){
             Double valorDeCompra = Relatorios.stream().filter(relatorio -> relatorio.getCodigo().
                             equals(codigo)).mapToDouble(Relatorio::getQuantidade).sum();

             Double qnt = getQuantidadeAtivo(codigo);

             if (qnt == 0) return 0.0;

             BigDecimal resultado = BigDecimal.valueOf(valorDeCompra / qnt)
                     .setScale(2, RoundingMode.HALF_UP);
             String toReturn = String.format("%.2f", valorDeCompra / qnt);

             return resultado.doubleValue();
         }

     }
