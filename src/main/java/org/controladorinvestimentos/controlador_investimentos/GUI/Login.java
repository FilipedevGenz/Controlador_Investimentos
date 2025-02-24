package org.controladorinvestimentos.controlador_investimentos.GUI;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controladorinvestimentos.controlador_investimentos.Banco.RepositorioAtivos;
import org.controladorinvestimentos.controlador_investimentos.Banco.repositorioUsers;
import org.controladorinvestimentos.controlador_investimentos.beans.Adm;
import org.controladorinvestimentos.controlador_investimentos.beans.ControladorAtivos;
import org.controladorinvestimentos.controlador_investimentos.beans.Usuario;

import java.io.IOException;


public class Login extends Application {

    repositorioUsers repositorio;
    RepositorioAtivos repositorioatv;

    @Override
    public void start(Stage primaryStage) {
        repositorioatv = RepositorioAtivos.getInstance();
        repositorio = repositorioUsers.getInstance();
        Usuario conta = new Usuario(1234,"teste","123","emailAdm");
        Adm admTeste = new Adm(conta);
        conta contaTeste = new conta(12345,"contaTeste","123","emailConta");

        try {
            ControladorAtivos.CriarAtivo("VALE3");
            ControladorAtivos.CriarAtivo("PETR4");
        } catch (IOException e) {
            System.err.println("Erro ao criar o ativo: " + e.getMessage());
        }

        primaryStage.setTitle("Controlador de Investimentos");

        Label loginLabel = new Label("Controlador de investimentos");
        loginLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField userField = new TextField();
        userField.setPromptText("Digite seu CPF");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Senha");

        Button loginButton = new Button("Entrar");
        Button admButton = new Button("ADM");

        loginButton.setOnAction(e -> {
            String user = userField.getText();
            String password = passwordField.getText();
            try {
                Integer cpf = Integer.parseInt(user);
                if (repositorio.buscarCPF(cpf)) {
                    Usuario usuarioEncontrado = repositorio.buscarCPFreturnUser(cpf);
                    if (usuarioEncontrado.getSenha().equals(password)) {
                        if (usuarioEncontrado.isADM) {
                            AdicionarAtivo next = new AdicionarAtivo(usuarioEncontrado);
                            Stage menu = new Stage();
                            next.start(menu);
                        } else {
                            conta conta1 = new conta(usuarioEncontrado.getCpf(), usuarioEncontrado.getNome(), usuarioEncontrado.getSenha(), usuarioEncontrado.getEmail());
                            menuUser next = new menuUser(conta1);
                            Stage menu = new Stage();
                            next.start(menu);
                        }
                        primaryStage.close();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Senha incorreta!", ButtonType.OK).showAndWait();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Usuário não encontrado!", ButtonType.OK).showAndWait();
                }
            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.ERROR, "CPF deve conter apenas números!", ButtonType.OK).showAndWait();
            }
        });

        admButton.setOnAction(e -> {
            TelaCheckAdm telaCheckAdm = new TelaCheckAdm();
            telaCheckAdm.start(primaryStage);
        });

        VBox formLayout = new VBox(15, loginLabel, userField, passwordField, loginButton, admButton);
        formLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(formLayout, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
