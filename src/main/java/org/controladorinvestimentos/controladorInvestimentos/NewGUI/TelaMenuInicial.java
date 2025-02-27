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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioAtivos;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioCarteira;
import org.controladorinvestimentos.controladorInvestimentos.beans.APIfuncionalidades.APIrequest;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Carteira;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Usuario;
import org.controladorinvestimentos.controladorInvestimentos.beans.ControladorCarteira;

import java.io.IOException;
import java.util.List;

public class TelaMenuInicial extends Application {

    private RepositorioAtivos repositorioAtivos;
    private RepositorioCarteira repositorioCarteira;
    private Usuario usuarioLogado;

    public TelaMenuInicial(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    @Override
    public void start(Stage primaryStage) {
        repositorioAtivos = RepositorioAtivos.getInstance();
        repositorioCarteira = RepositorioCarteira.getInstance();

        primaryStage.setTitle("Rentabilidade Geral");

        // ** Menu lateral estilizado **
        VBox menu = new VBox(15);
        menu.setPadding(new Insets(15));
        menu.setStyle("-fx-background-color: #f7f9fc; -fx-border-color: #e0e4ea; -fx-border-width: 1px;");
        menu.setPrefWidth(220);

        Label title = new Label("Análises");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #333;");

        Button btnProjecoes = criarBotaoMenu("Projeções");
        Button btnCarteira = criarBotaoMenu("Carteira");
        Button btnRelatorio = criarBotaoMenu("Relatório");

        menu.getChildren().addAll(title, btnProjecoes, btnCarteira, btnRelatorio);

        // ** Título principal e rentabilidade **
        Label lblTitulo = new Label("Rentabilidade geral (trimestral):");
        lblTitulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #333;");

        double valorRentabilidade = -36712.47;
        Label lblValor = new Label(String.format("R$ %.2f", valorRentabilidade));
        lblValor.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; " +
                (valorRentabilidade < 0 ? "-fx-text-fill: #ff4d4d;" : "-fx-text-fill: #28a745;"));

        HBox hboxTitulo = new HBox(10, lblTitulo, lblValor);
        hboxTitulo.setPadding(new Insets(15, 0, 15, 15));

        // ** Gráfico remodelado **
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        StackedBarChart<String, Number> barChart = new StackedBarChart<>(xAxis, yAxis);
        barChart.setMinHeight(350);
        barChart.setStyle("-fx-background-color: #ffffff; -fx-padding: 15px; -fx-border-radius: 10px; -fx-border-color: #ddd;");

        xAxis.setLabel("Período");
        yAxis.setLabel("Valor (R$)");

        XYChart.Series<String, Number> aplicacoes = new XYChart.Series<>();
        aplicacoes.setName("Aplicações");
        aplicacoes.getData().add(new XYChart.Data<>("Período", 57591.71));

        XYChart.Series<String, Number> resgates = new XYChart.Series<>();
        resgates.setName("Resgates");
        resgates.getData().add(new XYChart.Data<>("Período", -12533.43));

        XYChart.Series<String, Number> rentabilidade = new XYChart.Series<>();
        rentabilidade.setName("Rentabilidade Geral");
        rentabilidade.getData().add(new XYChart.Data<>("Período", valorRentabilidade));

        barChart.getData().addAll(aplicacoes, resgates, rentabilidade);

        Label lblResultado = new Label("Resultado no período");
        lblResultado.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333;");

        VBox content = new VBox(10, hboxTitulo, lblResultado, barChart);
        content.setPadding(new Insets(15));

        btnCarteira.setOnAction(e -> abrirTela(new TelaCarteiras(usuarioLogado), primaryStage));

        BorderPane root = new BorderPane();
        root.setLeft(menu);
        root.setCenter(content);
        root.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(root, 1100, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para criar botões do menu com hover
    private Button criarBotaoMenu(String texto) {
        Button botao = new Button(texto);
        botao.setPrefWidth(180);
        botao.setStyle(
                "-fx-font-size: 16px; " +
                        "-fx-background-color: #ffffff; " +
                        "-fx-border-color: #ccc; " +
                        "-fx-border-radius: 8px; " +
                        "-fx-padding: 10px; " +
                        "-fx-cursor: hand;"
        );
        botao.setOnMouseEntered(e -> botao.setStyle(
                "-fx-font-size: 16px; " +
                        "-fx-background-color: #e0e4ea; " +
                        "-fx-border-color: #ccc; " +
                        "-fx-border-radius: 8px; " +
                        "-fx-padding: 10px; " +
                        "-fx-cursor: hand;"
        ));
        botao.setOnMouseExited(e -> botao.setStyle(
                "-fx-font-size: 16px; " +
                        "-fx-background-color: #ffffff; " +
                        "-fx-border-color: #ccc; " +
                        "-fx-border-radius: 8px; " +
                        "-fx-padding: 10px; " +
                        "-fx-cursor: hand;"
        ));
        return botao;
    }

    // Método para abrir uma nova tela
    private void abrirTela(Application tela, Stage primaryStage) {
        try {
            tela.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}