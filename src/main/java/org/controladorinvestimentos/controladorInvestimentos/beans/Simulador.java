package org.controladorinvestimentos.controladorInvestimentos.beans;

import org.controladorinvestimentos.controladorInvestimentos.beans.APIfuncionalidades.APIrequest;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Carteira;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Relatorio;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class Simulador {

    //passar o codigo do ativo como parametro
    public static Double simularVariacao(Carteira carteira,String ativo) throws IOException {
        Double valorMedioCompra = carteira.repositorioRelatorio.retornaValorMedioDeCompra(ativo);
        Double valorAtual = APIrequest.buscarPrecoAtivoEmTempoReal(ativo);
        return valorMedioCompra - valorAtual;
    }

    public static Double influenciaNaCarteira(Carteira carteira, String ativo) throws IOException {
        Double quantidade = carteira.repositorioRelatorio.getQuantidadeAtivo(ativo);

        if (quantidade == 0) {
            return 0.0;
        }

        Double variacaoPreco = simularVariacao(carteira, ativo);
        carteira.setValorCarteira(variacaoPreco);
        Double impactoFinanceiro = quantidade * variacaoPreco;

        // Obtém o valor atual da carteira, considerando os preços em tempo real de cada ativo
        Double valorAtualCarteira = atualizarValorCarteira(carteira);


        if (valorAtualCarteira == 0) {
            return 0.0;
        }

        Double impactoPercentual = (impactoFinanceiro / valorAtualCarteira) * 100;
        return impactoPercentual;
    }


    public static Double atualizarValorCarteira(Carteira carteira) throws IOException {

        // Agrupa os relatórios pelo código do ativo e soma as quantidades de cada grupo
        Map<String, Double> totalPorAtivo = carteira.repositorioRelatorio.getRelatorios().stream()
                .collect(Collectors.groupingBy(Relatorio::getCodigo, Collectors.summingDouble(Relatorio::getQuantidade)));

        Double valorAtualizado = 0.0;

        // Para cada ativo (código), busca o preço atual e multiplica pela quantidade total
        for (Map.Entry<String, Double> entry : totalPorAtivo.entrySet()) {
            String codigoAtivo = entry.getKey();
            Double quantidadeTotal = entry.getValue();

            // Busca o preço atual do ativo
            Double precoAtual = APIrequest.buscarPrecoAtivoEmTempoReal(codigoAtivo);

            // Acumula o valor do ativo na carteira
            valorAtualizado += quantidadeTotal * precoAtual;
        }

        return valorAtualizado;
    }
}
