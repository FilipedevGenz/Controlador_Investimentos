package org.controladorinvestimentos.controladorInvestimentos.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Parent;
import org.controladorinvestimentos.controladorInvestimentos.beans.ControladorAtivos;

import java.io.IOException;

public class AdicionarAtivo {

    private MainApp mainApp;
    private VBox container;

    public AdicionarAtivo(MainApp mainApp) {
        this.mainApp = mainApp;
        createView();
    }

    private void createView() {
        container = new VBox(20);
        container.setPadding(new Insets(50));
        container.setAlignment(Pos.CENTER);

        Text title = new Text("Adicionar Ativo");
        title.setFont(new Font("Arial", 28));

        TextField searchField = new TextField();
        searchField.setPromptText("Buscar Ativo");
        searchField.setStyle("-fx-background-color: #aaa; -fx-font-size: 16; -fx-padding: 10; -fx-background-radius: 20; -fx-border-color: transparent;");

        Button addButton = new Button("Adicionar");
        addButton.setFont(new Font("Arial", 18));
        addButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 15 30;");
        addButton.setOnAction(e -> {
            String nomeAtivo = searchField.getText(); // obtém o nomeCarteira do ativo digitado
            try {
                ControladorAtivos.criarAtivo(nomeAtivo);
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Código inválido");
                alert.setHeaderText("Ativo inválido");
                alert.setContentText("O ativo inserido não é válido. Tente novamente.");
                alert.showAndWait();
            }
        });

        // Botão de voltar: ao clicar, volta para a tela de Login
        Button voltarButton = new Button("Voltar");
        voltarButton.setFont(new Font("Arial", 18));
        voltarButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 15 30;");
        voltarButton.setOnAction(e -> {
            mainApp.showLoginScene();
        });

        container.getChildren().addAll(title, searchField, addButton, voltarButton);
    }

    public Parent getView() {
        return container;
    }
}
