package org.controladorinvestimentos.controlador_investimentos.GUI;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.controladorinvestimentos.controlador_investimentos.Banco.repositorioAtivos;
import org.controladorinvestimentos.controlador_investimentos.Banco.repositorioUsers;
import org.controladorinvestimentos.controlador_investimentos.beans.adm;
import org.controladorinvestimentos.controlador_investimentos.beans.conta;
import org.controladorinvestimentos.controlador_investimentos.beans.controladorAtivos;
import org.controladorinvestimentos.controlador_investimentos.beans.usuario;

import java.io.IOException;


public class Login extends Application {

    repositorioUsers repositorio;
    repositorioAtivos repositorioatv;

    @Override
    public void start(Stage primaryStage) {

        repositorioatv = repositorioAtivos.getInstance();
        repositorio = repositorioUsers.getInstance();
        usuario conta = new usuario(1234,"teste","123","emailAdm");
        adm admTeste = new adm(conta);
        conta contaTeste = new conta(12345,"contaTeste","123","emailConta");
        try {
            controladorAtivos.CriarAtivo("VALE3");
        } catch (IOException e) {
            System.err.println("Erro ao criar o ativo: " + e.getMessage());
        }
        try {
            controladorAtivos.CriarAtivo("PETR4");
        } catch (IOException e) {
            System.err.println("Erro ao criar o ativo: " + e.getMessage());
        }

        primaryStage.setTitle("Controlador de Investimentos");


        Label loginLabel = new Label("Controlador de investimentos");
        loginLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField userField = new TextField();
        userField.setPromptText("digite seu cpf");
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

            try {
                Integer cpf = Integer.parseInt(user);

                // Verifica se o CPF existe no repositório
                if (repositorio.buscarCPF(cpf)) {
                    usuario usuarioEncontrado = repositorio.buscarCPFreturnUser(cpf);

                    // Verifica se a senha está correta
                    if (usuarioEncontrado.getSenha().equals(password)) {
                        // Verifica se o usuário é administrador
                        if (usuarioEncontrado.isADM) {  // Verificação correta para ADM
                            AdicionarAtivo next = new AdicionarAtivo(usuarioEncontrado);
                            Stage menu = new Stage();
                            next.start(menu);
                        } else {
                            conta conta1 = new conta(usuarioEncontrado.getCpf(), usuarioEncontrado.getNome(), usuarioEncontrado.getSenha(), usuarioEncontrado.getEmail());
                            menuUser next = new menuUser(conta1);  // Passando diretamente uma instância de 'conta'
                            Stage menu = new Stage();
                            next.start(menu);
                        }
                        primaryStage.close();  // Fecha a tela de login
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Senha incorreta");
                        alert.setHeaderText("Senha incorreta");
                        alert.setContentText("A senha informada está errada. Tente novamente.");
                        alert.showAndWait();
                    }
                } else {
                    // CPF não encontrado
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Usuário não encontrado");
                    alert.setHeaderText("CPF Inválido");
                    alert.setContentText("O CPF informado não está cadastrado.");
                    alert.showAndWait();
                }
            } catch (NumberFormatException ex) {
                // CPF inválido (não é um número)
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro de Formato");
                alert.setHeaderText("CPF Inválido");
                alert.setContentText("O CPF informado deve conter apenas números.");
                alert.showAndWait();
            }
        });


        Label registerLabel = new Label("Não tem conta?\nclique aqui para criar uma conta");
        registerLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: gray; -fx-text-alignment: center; -fx-underline: true;");

        //logica para alternar de tela
        registerLabel.setOnMouseClicked(event -> {

            newUser next = new newUser();
            Stage newUser = new Stage();
            next.start(newUser);
            primaryStage.close();

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

