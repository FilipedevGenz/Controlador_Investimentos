package org.controladorinvestimentos.controladorInvestimentos.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;
import javafx.scene.text.Font;

public class Login {

    private MainApp mainApp;
    private VBox container;

    public Login(MainApp mainApp) {
        this.mainApp = mainApp;
        createView();
    }

    private void createView() {
        container = new VBox(20);
        container.setPadding(new Insets(50));
        container.setAlignment(Pos.CENTER);

        Label label = new Label("Tela de Login");
        label.setFont(new Font("Arial", 28));

        Button loginButton = new Button("Entrar");
        loginButton.setFont(new Font("Arial", 18));
        loginButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 15 30;");
        loginButton.setOnAction(e -> {
            // Após efetuar o login, navega para a tela de Adicionar Ativo.
            mainApp.showAdicionarAtivoScene();
        });

        container.getChildren().addAll(label, loginButton);
    }

    public Parent getView() {
        return container;
    }
}
