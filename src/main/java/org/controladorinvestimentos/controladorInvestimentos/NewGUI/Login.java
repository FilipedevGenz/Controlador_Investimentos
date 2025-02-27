package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.controladorinvestimentos.controladorInvestimentos.NewGUI.AdicionarAtivo;

public class Login extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tela de Login");

        BorderPane root = new BorderPane();
        VBox layoutPrincipal = new VBox(20);
        layoutPrincipal.setPadding(new Insets(50));
        layoutPrincipal.setAlignment(Pos.CENTER);

        // Título
        Label label = new Label("Tela de Login");
        label.setFont(new Font("Arial", 28));

        // Botão de login
        Button loginButton = new Button("Entrar");
        loginButton.setFont(new Font("Arial", 18));
        loginButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 15 30;");

        loginButton.setOnAction(e -> {
            AdicionarAtivo adicionarAtivo = new AdicionarAtivo();
            Stage adicionarAtivoStage = new Stage();
            try {
                adicionarAtivo.start(adicionarAtivoStage);
                primaryStage.close(); // Fecha a tela de login ao abrir a próxima
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Adicionando os elementos ao layout
        layoutPrincipal.getChildren().addAll(label, loginButton);
        root.setCenter(layoutPrincipal);

        // Configuração da cena
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
