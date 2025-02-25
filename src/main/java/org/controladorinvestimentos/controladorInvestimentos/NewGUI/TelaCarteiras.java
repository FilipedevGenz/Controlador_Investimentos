package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

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
            carteiraButton.setOnAction(e -> {
                // Ao clicar, abre a CarteiraMenu
                CarteiraMenu carteiraMenu = new CarteiraMenu();
                try {
                    carteiraMenu.start(primaryStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            vbox.getChildren().add(carteiraButton);
        }

        // Botão de Voltar (opcional: volta para o MenuInicial)
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setMinWidth(200);
        btnVoltar.setOnAction(e -> {
            MenuInicial menuInicial = new MenuInicial();
            try {
                menuInicial.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        vbox.getChildren().add(btnVoltar);

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
