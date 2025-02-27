package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TelaSimuladorInvestimento extends Application {

    public static double calcularValorFuturo(double valorInicial, double taxaAnual, int anos) {
        return valorInicial * Math.pow(1 + (taxaAnual / 100), anos);
    }

    public static double calcularValorFuturoComAportes(double valorInicial, double taxaAnual, double aporteMensal, int anos) {
        double montante = valorInicial;
        double taxaMensal = taxaAnual / 12 / 100;
        int meses = anos * 12;

        for (int i = 0; i < meses; i++) {
            montante += aporteMensal;
            montante *= (1 + taxaMensal);
        }
        return montante;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Simulador de Investimentos");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label lblValorInicial = new Label("Valor Inicial:");
        GridPane.setConstraints(lblValorInicial, 0, 0);

        TextField txtValorInicial = new TextField();
        GridPane.setConstraints(txtValorInicial, 1, 0);

        Label lblTaxa = new Label("Taxa Anual (%):");
        GridPane.setConstraints(lblTaxa, 0, 1);

        TextField txtTaxa = new TextField();
        GridPane.setConstraints(txtTaxa, 1, 1);

        Label lblAnos = new Label("Anos:");
        GridPane.setConstraints(lblAnos, 0, 2);

        TextField txtAnos = new TextField();
        GridPane.setConstraints(txtAnos, 1, 2);

        Label lblAporte = new Label("Aporte Mensal:");
        GridPane.setConstraints(lblAporte, 0, 3);

        TextField txtAporte = new TextField();
        GridPane.setConstraints(txtAporte, 1, 3);

        Button btnCalcular = new Button("Calcular");
        GridPane.setConstraints(btnCalcular, 1, 4);

        Label lblResultado = new Label("Resultado: ");
        GridPane.setConstraints(lblResultado, 1, 5);

        btnCalcular.setOnAction(e -> {
            double valorInicial = Double.parseDouble(txtValorInicial.getText());
            double taxaAnual = Double.parseDouble(txtTaxa.getText());
            int anos = Integer.parseInt(txtAnos.getText());
            double aporteMensal = txtAporte.getText().isEmpty() ? 0 : Double.parseDouble(txtAporte.getText());

            double resultado;
            if (aporteMensal > 0) {
                resultado = calcularValorFuturoComAportes(valorInicial, taxaAnual, aporteMensal, anos);
            } else {
                resultado = calcularValorFuturo(valorInicial, taxaAnual, anos);
            }
            lblResultado.setText("Resultado: R$ " + String.format("%.2f", resultado));
        });

        grid.getChildren().addAll(lblValorInicial, txtValorInicial, lblTaxa, txtTaxa, lblAnos, txtAnos, lblAporte, txtAporte, btnCalcular, lblResultado);

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
