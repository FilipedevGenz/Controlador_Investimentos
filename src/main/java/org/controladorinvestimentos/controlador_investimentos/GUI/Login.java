package org.controladorinvestimentos.controlador_investimentos.GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Login extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Controlador de Investimentos");


        Label loginLabel = new Label("Controlador de investimentos");
        loginLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField userField = new TextField();
        userField.setPromptText("Usuário");
        userField.setStyle("-fx-background-color: #E0E0E0; -fx-background-radius: 20px; -fx-padding: 10px; -fx-font-weight:" +
                " bold; -fx-alignment: center;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Senha");
        passwordField.setStyle("-fx-background-color: #E0E0E0; -fx-background-radius: 20px; -fx-padding: 10px; " +
                "-fx-font-weight: bold; -fx-alignment: center;");

        Button loginButton = new Button("Entrar");
        loginButton.setStyle("-fx-background-color: #555; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: " +
                "20px; -fx-padding: 10px 20px;");

        loginButton.setOnAction(e -> {

            String user = userField.getText();
            String password = passwordField.getText();


        });

        Label registerLabel = new Label("Não tem conta?\nclique aqui para criar uma conta");
        registerLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: gray; -fx-text-alignment: center;");
        registerLabel.setOnMouseClicked(event -> {
            System.out.println("Redirecionando para página de cadastro...");
        });

        //estutura do layout
        VBox formLayout = new VBox(15, loginLabel, userField, passwordField, loginButton, registerLabel);
        formLayout.setAlignment(Pos.CENTER);
        formLayout.setStyle("-fx-padding: 20px;");

        Rectangle background = new Rectangle(350, 300);
        background.setFill(Color.WHITE);
        background.setArcWidth(30);
        background.setArcHeight(30);

        StackPane formContainer = new StackPane(background, formLayout);
        formContainer.setAlignment(Pos.CENTER);

        VBox rootLayout = new VBox(20, formContainer);
        rootLayout.setAlignment(Pos.CENTER);
        rootLayout.setStyle("-fx-background-color: #F5F5F5; -fx-padding: 40px;");

        Scene scene = new Scene(rootLayout, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

