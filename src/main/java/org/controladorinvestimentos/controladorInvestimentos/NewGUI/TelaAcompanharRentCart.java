package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import org.controladorinvestimentos.controladorInvestimentos.beans.HistoricoDosAtivos;
import org.controladorinvestimentos.controladorInvestimentos.beans.HistoricoDosAtivos.HistoricoAtivo;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class TelaAcompanharRentCart extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Acompanhamento da Taxa de Variação dos Ativos");

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Mês");
        yAxis.setLabel("Preço (R$)");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Evolução dos Ativos na Carteira");

        String[] ativos = {"PETR4", "CSAN3", "HAPV3"};
        LocalDate dataCompra = LocalDate.now().minusMonths(12);
        Random random = new Random();

        for (String ativo : ativos) {
            List<HistoricoAtivo> historico = HistoricoDosAtivos.retornaListaDadosDeHistorico(ativo, dataCompra);
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName(ativo);

            int mesIndex = 1;
            for (HistoricoAtivo dado : historico) {
                series.getData().add(new XYChart.Data<>(mesIndex, dado.getPreco()));
                mesIndex++;
            }

            lineChart.getData().add(series);
        }

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            TelaMenuInicial menuInicial = new TelaMenuInicial();
            try {
                menuInicial.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(10, lineChart, btnVoltar);
        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
