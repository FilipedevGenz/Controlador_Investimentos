package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import org.controladorinvestimentos.controladorInvestimentos.beans.APIfuncionalidades.HistoricoDosAtivos;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Carteira;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Relatorio;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Usuario;
import org.controladorinvestimentos.controladorInvestimentos.beans.ControladorCarteira;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TelaMenuInicial extends Application {

    private Usuario usuarioLogado;

    public TelaMenuInicial(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Painel de Investimentos");

        VBox menu = new VBox(15);
        menu.setPadding(new Insets(15));
        menu.setStyle("-fx-background-color: #f7f9fc; -fx-border-color: #e0e4ea; -fx-border-width: 1px;");
        menu.setPrefWidth(220);

        Label title = new Label("Análises");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #333;");

        Button btnProjecoes = criarBotaoMenu("Projeções");
        Button btnCarteira = criarBotaoMenu("Carteira");
        Button btnRelatorio = criarBotaoMenu("Relatório");

        menu.getChildren().addAll(title, btnProjecoes, btnCarteira, btnRelatorio);

        Label lblTitulo = new Label("BEM VINDO, " + usuarioLogado.getNome().toUpperCase());
        lblTitulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #333;");

        double rentabilidadeCarteira = calcularRentabilidadeCarteiras();

        Label lblRentabilidade = new Label("Rentabilidade da Carteira: " + String.format("%.1f", rentabilidadeCarteira) + "%");
        lblRentabilidade.setStyle("-fx-font-size: 14px; -fx-text-fill: " + (rentabilidadeCarteira >= 0 ? "#28a745;" : "#ff4d4d;"));

        HBox header = new HBox(lblTitulo, lblRentabilidade);
        header.setSpacing(10);
        header.setPadding(new Insets(10, 20, 10, 20));
        header.setAlignment(Pos.TOP_RIGHT);

        StackedBarChart<String, Number> barChart = criarGraficoRentabilidade();

        Label lblResultado = new Label("Evolução da Carteira");
        lblResultado.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333;");

        VBox content = new VBox(10, header, lblResultado, barChart);
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

    private double calcularRentabilidadeCarteiras() {
        ControladorCarteira controladorCarteira = new ControladorCarteira();
        List<Carteira> carteiras = controladorCarteira.getCarteiras(usuarioLogado);
        double rentabilidadeTotal = 0.0;

        for (Carteira carteira : carteiras) {
            List<Relatorio> ativos = carteira.getRepositorioRelatorio().getRelatorios();
            double rentabilidadeCarteira = 0.0;

            for (Relatorio ativo : ativos) {
                String nomeAtivo = ativo.getCodigo();
                LocalDate dataInicio = LocalDate.now().minusMonths(3); // Últimos 3 meses
                rentabilidadeCarteira += HistoricoDosAtivos.calcularTaxaDeVariacao(nomeAtivo, dataInicio);
            }

            rentabilidadeTotal += rentabilidadeCarteira;
        }

        return rentabilidadeTotal / (carteiras.isEmpty() ? 1 : carteiras.size());
    }

    private StackedBarChart<String, Number> criarGraficoRentabilidade() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        StackedBarChart<String, Number> barChart = new StackedBarChart<>(xAxis, yAxis);
        barChart.setMinHeight(350);
        barChart.setStyle("-fx-background-color: #ffffff; -fx-padding: 15px; -fx-border-radius: 10px; -fx-border-color: #ddd;");

        xAxis.setLabel("Data");
        yAxis.setLabel("Variação (%)");

        ControladorCarteira controladorCarteira = new ControladorCarteira();
        List<Carteira> carteiras = controladorCarteira.getCarteiras(usuarioLogado);

        XYChart.Series<String, Number> seriesRentabilidade = new XYChart.Series<>();
        seriesRentabilidade.setName("Rentabilidade");

        LocalDate dataInicio = LocalDate.now().minusMonths(3);
        LocalDate dataAtual = LocalDate.now();

        while (!dataInicio.isAfter(dataAtual)) {
            double rentabilidadeTotal = 0.0;
            int countAtivos = 0;

            for (Carteira carteira : carteiras) {
                List<Relatorio> ativos = carteira.getRepositorioRelatorio().getRelatorios();
                for (Relatorio ativo : ativos) {
                    String nomeAtivo = ativo.getCodigo();
                    double variacao = HistoricoDosAtivos.calcularTaxaDeVariacao(nomeAtivo, dataInicio);
                    rentabilidadeTotal += variacao;
                    countAtivos++;
                }
            }

            double rentabilidadeMedia = (countAtivos == 0) ? 0 : rentabilidadeTotal / countAtivos;
            seriesRentabilidade.getData().add(new XYChart.Data<>(dataInicio.toString(), rentabilidadeMedia));

            dataInicio = dataInicio.plusWeeks(1); // Incremento semanal
        }

        barChart.getData().add(seriesRentabilidade);
        return barChart;
    }

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
        return botao;
    }

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
