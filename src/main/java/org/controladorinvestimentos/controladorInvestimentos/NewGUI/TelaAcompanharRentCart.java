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
import org.controladorinvestimentos.controladorInvestimentos.beans.APIfuncionalidades.HistoricoDosAtivos;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Carteira;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Relatorio;

import java.time.LocalDate;
import java.util.List;

public class TelaAcompanharRentCart extends Application {
    private Carteira carteira;

    public TelaAcompanharRentCart(Carteira carteira) {
        this.carteira = carteira;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Acompanhamento da Taxa de Variação dos Ativos");

        BorderPane root = new BorderPane();
        VBox layoutPrincipal = new VBox(10);
        layoutPrincipal.setPadding(new Insets(15));

        // Título
        Label lblTitulo = new Label("Carteira de Ativos");
        lblTitulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        // Obtém os ativos da carteira
        List<Relatorio> ativos = carteira.getRepositorioRelatorio().getRelatorios();
        VBox indicadoresVariação = new VBox(5);

        // Criando os eixos do gráfico
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Data");
        yAxis.setLabel("Preço (R$)");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Evolução dos Ativos na Carteira");

        double rentabilidadeTotal = 0.0;

        for (Relatorio ativo : ativos) {
            String nomeAtivo = ativo.getCodigo();
            LocalDate dataInicio = LocalDate.now().minusMonths(3); // Últimos 3 meses
            double variacao = HistoricoDosAtivos.calcularTaxaDeVariacao(nomeAtivo, dataInicio);
            rentabilidadeTotal += variacao;

            Label lblVariacao = new Label(nomeAtivo + ": " + String.format("%.1f", variacao) + "%");
            lblVariacao.setStyle("-fx-font-size: 16px; " + (variacao >= 0 ? "-fx-text-fill: green;" : "-fx-text-fill: red;"));
            indicadoresVariação.getChildren().add(lblVariacao);

            // Criando a série para o ativo
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName(nomeAtivo);

            // Obtendo os dados reais do histórico do ativo
            List<HistoricoDosAtivos.HistoricoAtivo> historico = HistoricoDosAtivos.retornaListaDadosDeHistorico(nomeAtivo, dataInicio);

            for (HistoricoDosAtivos.HistoricoAtivo dado : historico) {
                series.getData().add(new XYChart.Data<>(dado.getData().toEpochDay(), dado.getPreco()));
            }

            lineChart.getData().add(series);
        }

        // Rentabilidade total da carteira
        Label lblRentabilidade = new Label("Rentabilidade da Carteira: " + String.format("%.1f", rentabilidadeTotal) + "%");
        lblRentabilidade.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: green;");

        /*
        // Botão de voltar
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setStyle("-fx-background-color: lightblue;");
        btnVoltar.setOnAction(e -> {
            Scene sceneCarteiras = new Scene(new TelaCarteiras().getRootPane(), 800, 600);
            primaryStage.setScene(sceneCarteiras);
        });

        HBox boxVoltar = new HBox(btnVoltar);
        boxVoltar.setPadding(new Insets(10));


         */
        // Adicionando elementos à interface
        layoutPrincipal.getChildren().addAll(lblTitulo, indicadoresVariação, lblRentabilidade, lineChart);
        root.setTop(layoutPrincipal);
        //root.setBottom(boxVoltar);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}