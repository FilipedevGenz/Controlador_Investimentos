package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controladorinvestimentos.controladorInvestimentos.beans.Carteira;
import org.controladorinvestimentos.controladorInvestimentos.beans.ControladorCarteira;

import static org.controladorinvestimentos.controladorInvestimentos.beans.APIrequest.buscarPrecoAtivoEmTempoReal;
import java.util.List;

public class MenuInicial extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Rentabilidade Geral");

        // Acessa o controlador global de carteiras (padrão singleton que ajuda na inserção das carteiras no banco de dados)
        ControladorCarteira controladorCarteira = ControladorCarteira.getInstance();

        // Se não houver carteiras, cria as iniciais
        if (controladorCarteira.getCarteiras().isEmpty()) {
            controladorCarteira.novaCarteira("900.185-400", "carteira1");
            controladorCarteira.novaCarteira("900.185-401", "carteira2");

            try {
                List<Carteira> listaCarteiras = controladorCarteira.getCarteiras();
                // Adiciona ativos à Carteira1
                listaCarteiras.get(0).adicionarAtivoNaCarteira("PETR4", 5);
                listaCarteiras.get(0).adicionarAtivoNaCarteira("CSAN3", 3);
                // Adiciona ativos à Carteira2
                listaCarteiras.get(1).adicionarAtivoNaCarteira("HAPV3", 2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Criação do menu lateral
        VBox menu = new VBox(10);
        menu.setPadding(new Insets(10));
        menu.setStyle("-fx-background-color: #f5f7fa;");
        menu.setPrefWidth(200);

        Label title = new Label("Análises");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button btnProjecoes = new Button("Projeções");
        Button btnCarteira = new Button("Carteira");
        Button btnSimulacao = new Button("Simulação");
        Button btnRelatorio = new Button("Relatório");

        menu.getChildren().addAll(title, btnProjecoes, btnCarteira, btnSimulacao, btnRelatorio);

        // Título principal com rentabilidade
        Label lblTitulo = new Label("Rentabilidade geral (mensal):");
        lblTitulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        double valorRentabilidade = -36712.47; // Valor de exemplo
        Label lblValor = new Label(String.format("%.2f R$", valorRentabilidade));
        String styleValor = "-fx-font-size: 20px; -fx-font-weight: bold; " +
                (valorRentabilidade < 0 ? "-fx-text-fill: red;" : "-fx-text-fill: green;");
        lblValor.setStyle(styleValor);

        HBox hboxTitulo = new HBox(10, lblTitulo, lblValor);
        hboxTitulo.setPadding(new Insets(10, 0, 10, 10));

        // Gráfico de barras empilhadas
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        StackedBarChart<String, Number> barChart = new StackedBarChart<>(xAxis, yAxis);
        barChart.setMinHeight(300);

        xAxis.setLabel("Período");
        yAxis.setLabel("Valor (R$)");

        XYChart.Series<String, Number> aplicacoes = new XYChart.Series<>();
        aplicacoes.setName("Aplicações");
        aplicacoes.getData().add(new XYChart.Data<>("Período", 57591.71));

        XYChart.Series<String, Number> resgates = new XYChart.Series<>();
        resgates.setName("Resgates");
        resgates.getData().add(new XYChart.Data<>("Período", -12533.43));

        XYChart.Series<String, Number> rentabilidade = new XYChart.Series<>();
        rentabilidade.setName("Rentabilidade Geral");
        rentabilidade.getData().add(new XYChart.Data<>("Período", valorRentabilidade));

        barChart.getData().addAll(aplicacoes, resgates, rentabilidade);

        Label lblResultado = new Label("Resultado no período");
        lblResultado.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        VBox content = new VBox(10, hboxTitulo, lblResultado, barChart);
        content.setPadding(new Insets(10));

        btnCarteira.setOnAction(e -> {
            TelaCarteiras telaCarteiras = new TelaCarteiras();
            try {
                telaCarteiras.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        BorderPane root = new BorderPane();
        root.setLeft(menu);
        root.setCenter(content);

        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
