package org.controladorinvestimentos.controlador_investimentos.GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controladorinvestimentos.controlador_investimentos.beans.conta;
import org.controladorinvestimentos.controlador_investimentos.beans.controladorAtivos;
import org.controladorinvestimentos.controlador_investimentos.beans.adm;
import org.controladorinvestimentos.controlador_investimentos.beans.usuario;

import java.io.IOException;


public class AdicionarAtivo extends Application {
    conta user;

    AdicionarAtivo(usuario conta) {
        adm user = new adm(conta);
    }

    conta adm;

    @Override
    public void start(Stage primaryStage) {
        VBox container = new VBox(20);
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
            String nomeAtivo = searchField.getText(); // Obtém o nome do ativo digitado
            try {
                controladorAtivos.CriarAtivo(nomeAtivo);
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Código inválido");
                alert.setHeaderText("Ativo inválido");
                alert.setContentText("O ativo inserido não é válido. Tente novamente.");
                alert.showAndWait();
            }
        });

        // Botão Voltar
        Button voltarButton = new Button("Voltar");
        voltarButton.setFont(new Font("Arial", 18));
        voltarButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 15 30;");
        voltarButton.setOnAction(e -> {
            Login.launch(Login.class);  //
            primaryStage.close();  //
        });

        container.getChildren().addAll(title, searchField, addButton, voltarButton);

        Scene scene = new Scene(container, 500, 400);
        primaryStage.setTitle("Adicionar Ativo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
