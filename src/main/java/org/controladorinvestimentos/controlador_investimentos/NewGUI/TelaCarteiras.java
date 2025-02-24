package org.controladorinvestimentos.controlador_investimentos.NewGUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaCarteiras extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Menu de Carteiras");

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);

        // Criando botões de carteiras
        for (int i = 1; i <= 5; i++) { // Número de carteiras pode ser ajustado
            Button carteiraButton = new Button("Carteira " + i);
            carteiraButton.setMinWidth(200);
            vbox.getChildren().add(carteiraButton);
        }

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
