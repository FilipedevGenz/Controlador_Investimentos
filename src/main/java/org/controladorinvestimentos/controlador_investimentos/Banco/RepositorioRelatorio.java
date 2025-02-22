package org.controladorinvestimentos.controlador_investimentos.Banco;
import java.io.IOException;
import java.util.ArrayList;
import lombok.Data;
import org.controladorinvestimentos.controlador_investimentos.beans.APIrequest;
import org.controladorinvestimentos.controlador_investimentos.beans.Carteira;
import org.controladorinvestimentos.controlador_investimentos.beans.Relatorio;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class RepositorioRelatorio implements IrepositorioRelatorio {


     private Carteira dono;

     private final ArrayList <Relatorio> relatorios = new ArrayList<>();

    public Carteira getDono() {
        return dono;
    }

    public ArrayList <Relatorio> getRelatorios() {
        return relatorios;
    }

     public void addRelatorio (Relatorio relatorio){
        relatorios.add(relatorio);
     }

     public Double getQuantidadeAtivo(String codigo) {
         return relatorios.stream().filter(relatorio -> relatorio.getCodigo().
                         equals(codigo)).mapToDouble(Relatorio::getQuantidade).
                 sum();
     }

     public Double valorDeCompraCarteira(){
         //sera usado para comparar com o valor atual e obter a valorizacao
         return relatorios.stream().mapToDouble(org.controladorinvestimentos.controlador_investimentos.beans.Relatorio::getValorTotal).sum();
         }

    public Double valorMedioDeCompra(String codigo){
        Double valorDeCompra = relatorios.stream().filter(relatorio -> relatorio.getCodigo().
                equals(codigo)).mapToDouble(Relatorio::getQuantidade).sum();

        Double qnt = getQuantidadeAtivo(codigo);

        if (qnt == 0) return 0.0;

        BigDecimal resultado = BigDecimal.valueOf(valorDeCompra / qnt)
                .setScale(2, RoundingMode.HALF_UP);
        String toReturn = String.format("%.2f", valorDeCompra / qnt);

        return resultado.doubleValue();
    }

    @Override
    public Double calcularValorAtual() {
        return relatorios.stream()
                .mapToDouble(relatorio -> {
                    try {
                        double preco = APIrequest.buscarPrecoAtivoEmTempoReal(relatorio.getCodigo());
                        return relatorio.getQuantidade() * preco;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .sum();
    }
}
