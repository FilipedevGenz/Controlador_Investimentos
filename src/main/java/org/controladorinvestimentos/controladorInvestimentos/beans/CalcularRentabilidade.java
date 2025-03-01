package org.controladorinvestimentos.controladorInvestimentos.beans;

import org.controladorinvestimentos.controladorInvestimentos.beans.APIfuncionalidades.HistoricoDosAtivos;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Carteira;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Relatorio;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Usuario;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalcularRentabilidade {

    public static Map<String, Double> calcularRentabilidadeCarteiras(Usuario usuarioLogado) {
        ControladorCarteira controladorCarteira = new ControladorCarteira();
        List<Carteira> carteiras = controladorCarteira.getCarteiras(usuarioLogado);

        double rentabilidadeTotal = 0.0;
        double carteirasComAtivos = 0.0;

        for (Carteira carteira : carteiras) {
            List<Relatorio> ativos = carteira.getRepositorioRelatorio().getRelatorios();

            if (ativos.isEmpty()) {
                continue; // Ignora carteiras vazias
            }

            double rentabilidadeCarteira = 0.0;

            for (Relatorio ativo : ativos) {
                String nomeAtivo = ativo.getCodigo();
                LocalDate dataInicio = LocalDate.now().minusMonths(3);
                double variacao = HistoricoDosAtivos.calcularTaxaDeVariacao(nomeAtivo, dataInicio);
                rentabilidadeCarteira += variacao;
            }

            rentabilidadeTotal += rentabilidadeCarteira;
            carteirasComAtivos++; // Contabiliza apenas carteiras que possuem ativos
        }

        // Criando o mapa de retorno
        Map<String, Double> resultado = new HashMap<>();
        resultado.put("rentabilidadeTotal", rentabilidadeTotal);
        resultado.put("carteirasComAtivos", carteirasComAtivos);

        return resultado;
    }

    public static double calcularRentabilidadePorAtivo(String codigoAtivo) {
        LocalDate dataInicio = LocalDate.now().minusMonths(3); // Considera os últimos 3 meses
        return HistoricoDosAtivos.calcularTaxaDeVariacao(codigoAtivo, dataInicio);
    }

}
