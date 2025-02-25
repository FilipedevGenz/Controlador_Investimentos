package org.controladorinvestimentos.controladorInvestimentos.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;

public class InfoAtivo extends Application {
    private String codigoAtivo = "Ativo X";

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Cabeçalho com o código do ativo e preço médio
        Text titulo = new Text(codigoAtivo);
        Text precoMedio = new Text("O preço médio de " + codigoAtivo + " na sua carteira é xx");
        HBox header = new HBox(10, titulo, precoMedio);
        header.setStyle("-fx-alignment: center;");

        // Gráfico gerado com JFreeChart
        JFreeChart chart = GraficoAtivo.criarGraficoSemMostrar(codigoAtivo);
        ChartViewer chartViewer = new ChartViewer(chart);

        // Botões de interação
        Button btnRemover = new Button("Remover");
        Button btnAdicionar = new Button("Adicionar");
        HBox botoes = new HBox(10, btnRemover, btnAdicionar);
        botoes.setStyle("-fx-alignment: center;");

        // Botão de voltar
        Button btnVoltar = new Button("Voltar");

        // Layout principal
        root.setTop(header);
        root.setCenter(chartViewer);
        root.setBottom(new HBox(10, botoes, btnVoltar));

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Detalhes do Ativo");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
