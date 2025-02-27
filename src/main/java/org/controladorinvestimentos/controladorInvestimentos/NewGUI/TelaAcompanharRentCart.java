package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Carteira;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Relatorio;
import org.controladorinvestimentos.controladorInvestimentos.beans.APIfuncionalidades.HistoricoDosAtivos;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class TelaAcompanharRentCart extends Application {
    private Carteira carteira;

    public TelaAcompanharRentCart(Carteira carteira) {
        this.carteira = carteira;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Acompanhamento da Taxa de Variação dos Ativos");

        BorderPane root = new BorderPane();
        VBox layoutPrincipal = new VBox(10);
        layoutPrincipal.setPadding(new Insets(15));

        // Título
        Label lblTitulo = new Label("Carteira de Ativos");
        lblTitulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        // Obtém os ativos da carteira
        List<Relatorio> ativos = carteira.getRepositorioRelatorio().getRelatorios();
        VBox indicadoresVariação = new VBox(5);

        XYChart.Series<Number, Number> seriesAtivoA = new XYChart.Series<>();
        XYChart.Series<Number, Number> seriesAtivoB = new XYChart.Series<>();

        seriesAtivoA.setName("Ativo A");
        seriesAtivoB.setName("Ativo B");

        Random random = new Random();
        double rentabilidadeTotal = 0.0;

        // O erro está aqui !!
        for (Relatorio ativo : ativos) {
            String nomeAtivo = ativo.getCodigo();
            LocalDate dataInicio = LocalDate.now().minusMonths(3); // Últimos 3 meses
            double variacao = HistoricoDosAtivos.calcularTaxaDeVariacao(nomeAtivo, dataInicio);
            rentabilidadeTotal += variacao;

            Label lblVariacao = new Label(nomeAtivo + ": " + String.format("%.1f", variacao) + "%");
            lblVariacao.setStyle("-fx-font-size: 16px; " + (variacao >= 0 ? "-fx-text-fill: green;" : "-fx-text-fill: red;"));
            indicadoresVariação.getChildren().add(lblVariacao);

            // Simula dados para o gráfico
            for (int i = 1; i <= 3; i++) {
                double precoSimulado = 90 + random.nextDouble() * 20; // Preço aleatório entre 90 e 110
                if (nomeAtivo.equals("Ativo A")) {
                    seriesAtivoA.getData().add(new XYChart.Data<>(i, precoSimulado));
                } else {
                    seriesAtivoB.getData().add(new XYChart.Data<>(i, precoSimulado));
                }
            }
        }

        // Rentabilidade total da carteira
        Label lblRentabilidade = new Label("Rentabilidade da Carteira: " + String.format("%.1f", rentabilidadeTotal) + "%");
        lblRentabilidade.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: green;");

        // Criando o gráfico
        NumberAxis xAxis = new NumberAxis(0, 3, 1);
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Mês");
        yAxis.setLabel("Preço (R$)");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.getData().addAll(seriesAtivoA, seriesAtivoB);
        lineChart.setTitle("Evolução dos Ativos na Carteira");

        // Botão de voltar
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setStyle("-fx-background-color: lightblue;");
        btnVoltar.setOnAction(e -> primaryStage.close());

        HBox boxVoltar = new HBox(btnVoltar);
        boxVoltar.setPadding(new Insets(10));

        // Adicionando elementos à interface
        layoutPrincipal.getChildren().addAll(lblTitulo, indicadoresVariação, lblRentabilidade, lineChart);
        root.setTop(layoutPrincipal);
        root.setBottom(boxVoltar);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
