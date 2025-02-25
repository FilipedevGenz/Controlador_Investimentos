package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CarteiraMenu extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Título da carteira (você pode parametrizar para cada carteira)
        Text carteiraTitulo = new Text("Carteira 1");
        carteiraTitulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Botões do menu
        Button btnProjecoes = new Button("Projeções");
        btnProjecoes.setStyle("-fx-background-color: #6366F1; -fx-text-fill: white; -fx-padding: 10px; -fx-font-size: 14px;");

        Button btnRentabilidade = new Button("Rentabilidade ou Acompanhamento");
        btnRentabilidade.setStyle("-fx-background-color: #3B82F6; -fx-text-fill: white; -fx-padding: 10px; -fx-font-size: 14px;");

        Button btnComprarAtivos = new Button("Comprar Ativos");
        btnComprarAtivos.setStyle("-fx-background-color: #10B981; -fx-text-fill: white; -fx-padding: 10px; -fx-font-size: 14px;");

        // Botão de Voltar para retornar à TelaCarteiras
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setStyle("-fx-background-color: #E5E7EB; -fx-text-fill: black; -fx-padding: 10px; -fx-font-size: 14px;");
        btnVoltar.setOnAction(e -> {
            TelaCarteiras telaCarteiras = new TelaCarteiras();
            try {
                telaCarteiras.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Layout principal
        VBox layout = new VBox(15, carteiraTitulo, btnProjecoes, btnRentabilidade, btnComprarAtivos, btnVoltar);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #F3F4F6; -fx-padding: 20px; -fx-border-radius: 10px;");

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setTitle("Carteira de Investimentos");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
