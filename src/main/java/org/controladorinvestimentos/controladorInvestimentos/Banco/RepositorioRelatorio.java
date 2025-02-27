package org.controladorinvestimentos.controladorInvestimentos.Banco;
import java.io.IOException;
import java.util.ArrayList;
import lombok.Data;
import org.controladorinvestimentos.controladorInvestimentos.Banco.Interfaces.IrepositorioRelatorio;
import org.controladorinvestimentos.controladorInvestimentos.beans.APIfuncionalidades.APIrequest;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Carteira;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Relatorio;
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

     public Double retornaValorDeCompraCarteira(){
         //sera usado para comparar com o valor atual e obter a valorizacao
         return relatorios.stream().mapToDouble(Relatorio::getValorTotal).sum();
         }

    public Double retornaValorMedioDeCompra(String codigo){
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


    public Double calcularRentabilidadeCarteira() {
        Double valorDeCompra = retornaValorDeCompraCarteira();
        Double valorAtual = calcularValorAtual();

        if (valorDeCompra == 0) return 0.0; // Evita divisão por zero

        BigDecimal rentabilidade = BigDecimal.valueOf(((valorAtual - valorDeCompra) / valorDeCompra) * 100)
                .setScale(2, RoundingMode.HALF_UP);

        return rentabilidade.doubleValue();
    }


    public void removerRelatorio(Relatorio relatorio){
        relatorios.remove(relatorio);
    }
}
