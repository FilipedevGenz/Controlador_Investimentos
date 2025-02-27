package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import org.controladorinvestimentos.controladorInvestimentos.beans.*;
import java.time.LocalDate;
import java.util.List;

public class TelaAcompanharRentCart extends Application {
    private Carteira carteira;

    public TelaAcompanharRentCart(Carteira carteira) {
        this.carteira = carteira;
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Rentabilidade - " + carteira.getNomeCarteira());

        double somaPonderada = 0.0;
        double somaQuantidades = 0.0;

        for (Relatorio ativo : carteira.repositorioRelatorio.getRelatorios()) {
            LocalDate dataCompra = LocalDate.now().minusMonths(6);
            double variacao = HistoricoDosAtivos.calcularTaxaDeVariacao(ativo.getCodigo(), dataCompra);

            somaPonderada += variacao * ativo.getQuantidade();
            somaQuantidades += ativo.getQuantidade();
        }

        double mediaPonderada = (somaQuantidades == 0) ? 0.0 : somaPonderada / somaQuantidades;
        primaryStage.setTitle(carteira.getNomeCarteira() + " - Rentabilidade: " + String.format("%.2f%%", mediaPonderada));


        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Evolução dos Ativos");

        List<Relatorio> ativos = carteira.repositorioRelatorio.getRelatorios();
        double somaPesos = 0, somaValores = 0;

        for (Relatorio rel : ativos) {
            List<HistoricoDosAtivos.HistoricoAtivo> historico = HistoricoDosAtivos.retornaListaDadosDeHistorico(rel.getCodigo(), LocalDate.now().minusMonths(6));
            double precoCompra = historico.isEmpty() ? rel.getValorCompra() : historico.get(0).getPreco();
            double taxaVariacao = ((rel.getValorCompra() - precoCompra) / precoCompra) * 100;
            somaValores += taxaVariacao * rel.getQuantidade();
            somaPesos += rel.getQuantidade();

            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName(rel.getCodigo());

            int mesIndex = 1;
            for (HistoricoDosAtivos.HistoricoAtivo dado : historico) {
                series.getData().add(new XYChart.Data<>(mesIndex, dado.getPreco()));
                mesIndex++;
            }
            lineChart.getData().add(series);
        }

        //double mediaPonderada = somaPesos > 0 ? (somaValores / somaPesos) : 0;
        primaryStage.setTitle("Rentabilidade - " + carteira.getNomeCarteira() + " (Variação: " + String.format("%.2f%%", mediaPonderada) + ")");

        VBox layout = new VBox(10, lineChart);
        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
