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
import org.controladorinvestimentos.controladorInvestimentos.beans.CalcularRentabilidade;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Carteira;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Usuario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class ProjecaoInvestimentosGeral extends Application {
    private TextField aporteField;
    private TextField mesesField;
    private Label rentabilidadeLabel;
    private RepositorioRelatorio repositorioRelatorio = new RepositorioRelatorio();
    private LineChart<Number, Number> grafico;
    private Usuario usuarioLogado;
    private Carteira carteira;

    public ProjecaoInvestimentosGeral(Usuario usuario, Carteira carteira) {
        this.usuarioLogado = usuario;
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
            TelaMenuInicial TelaMenuInicial = new TelaMenuInicial(usuarioLogado);
            try {
                TelaMenuInicial.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Meses");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Valor Projetado");

        grafico = new LineChart<>(xAxis, yAxis);
        grafico.setTitle("Projeção do Valor das Carteiras");

        grid.getChildren().addAll(aporteLabel, aporteField, mesesLabel, mesesField, calcularButton, rentabilidadeLabel, btnVoltar);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(grid, grafico);

        Scene scene = new Scene(layout, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calcularRentabilidade() {
        try {
            BigDecimal aporteMensal = new BigDecimal(aporteField.getText());
            int meses = Integer.parseInt(mesesField.getText());

            Map<String, Double> rentabilidadeDados = CalcularRentabilidade.calcularRentabilidadeCarteiras(usuarioLogado);
            BigDecimal rentabilidadeTotal = BigDecimal.valueOf(rentabilidadeDados.get("rentabilidadeTotal"));
            BigDecimal carteirasComAtivos = BigDecimal.valueOf(rentabilidadeDados.get("carteirasComAtivos"));

            // Evita divisão por zero
            BigDecimal rentabilidadeMedia = (carteirasComAtivos.compareTo(BigDecimal.ZERO) == 0)
                    ? BigDecimal.ZERO
                    : rentabilidadeTotal.divide(carteirasComAtivos, 8, RoundingMode.HALF_UP);

            rentabilidadeMedia = rentabilidadeMedia.divide(BigDecimal.valueOf(100), 8, RoundingMode.HALF_UP);

            BigDecimal valorProjetado = BigDecimal.ZERO;
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName("Projeção da Carteira");

            for (int i = 1; i <= meses; i++) {
                valorProjetado = valorProjetado.add(aporteMensal)
                        .multiply(BigDecimal.ONE.add(rentabilidadeMedia))
                        .setScale(2, RoundingMode.HALF_UP);

                series.getData().add(new XYChart.Data<>(i, valorProjetado));
            }

            rentabilidadeLabel.setText("Valor Projetado: R$ " + valorProjetado.toPlainString());
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
