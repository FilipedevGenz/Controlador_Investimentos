package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioRelatorio;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Carteira;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Usuario;


public class ProjecaoInvestimentos extends Application {
    private TextField aporteField;
    private TextField mesesField;
    private Label rentabilidadeLabel;
    private RepositorioRelatorio repositorioRelatorio = new RepositorioRelatorio();
    private LineChart<Number, Number> grafico;
    private Usuario usuarioLogado;
    private Carteira carteira;

    public ProjecaoInvestimentos(Usuario usuario, Carteira carteira) {
        this.usuarioLogado = usuario;
        this.carteira = carteira;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Projeção de Rentabilidade");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label aporteLabel = new Label("Aporte Mensal:");
        aporteField = new TextField();
        GridPane.setConstraints(aporteLabel, 0, 0);
        GridPane.setConstraints(aporteField, 1, 0);

        Label mesesLabel = new Label("Quantidade de Meses:");
        mesesField = new TextField();
        GridPane.setConstraints(mesesLabel, 0, 1);
        GridPane.setConstraints(mesesField, 1, 1);

        Button calcularButton = new Button("Calcular Rentabilidade");
        GridPane.setConstraints(calcularButton, 1, 2);
        calcularButton.setOnAction(e -> calcularRentabilidade());

        rentabilidadeLabel = new Label("Rentabilidade: --");
        GridPane.setConstraints(rentabilidadeLabel, 1, 3);

        Button btnVoltar = new Button("Voltar");
        GridPane.setConstraints(btnVoltar, 1, 4);
        btnVoltar.setOnAction(e -> {
            TelaCarteiraMenu telaCarteiraMenu = new TelaCarteiraMenu(usuarioLogado, carteira);
            telaCarteiraMenu.setCarteira(carteira);
            try {
                telaCarteiraMenu.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Meses");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Valor Projetado");

        grafico = new LineChart<>(xAxis, yAxis);
        grafico.setTitle("Projeção do Valor da Carteira");

        grid.getChildren().addAll(aporteLabel, aporteField, mesesLabel, mesesField, calcularButton, rentabilidadeLabel, btnVoltar);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(grid, grafico);

        Scene scene = new Scene(layout, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calcularRentabilidade() {
        try {
            double aporteMensal = Double.parseDouble(aporteField.getText());
            int meses = Integer.parseInt(mesesField.getText());

            double rentabilidade = repositorioRelatorio.calcularRentabilidadeCarteira() / 100;
            double valorProjetado = 0;

            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName("Projeção da Carteira");

            for (int i = 1; i <= meses; i++) {
                valorProjetado = (valorProjetado + aporteMensal) * (1 + rentabilidade);
                series.getData().add(new XYChart.Data<>(i, valorProjetado));
            }

            rentabilidadeLabel.setText("Valor Projetado: " + String.format("%.2f", valorProjetado));
            grafico.getData().clear();
            grafico.getData().add(series);
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, insira valores válidos.");
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
