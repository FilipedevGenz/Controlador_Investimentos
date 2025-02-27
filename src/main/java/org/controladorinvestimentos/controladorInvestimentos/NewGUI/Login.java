package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioUsuario;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Usuario;

public class Login extends Application {
    private RepositorioUsuario repositorioUsuario = RepositorioUsuario.getInstance();

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

        // Campo de entrada para CPF
        TextField cpfField = new TextField();
        cpfField.setPromptText("Digite seu CPF");
        cpfField.setMaxWidth(250);

        // Campo de entrada para Senha
        PasswordField senhaField = new PasswordField();
        senhaField.setPromptText("Digite sua senha");
        senhaField.setMaxWidth(250);

        // Botão de login
        Button loginButton = new Button("Entrar");
        loginButton.setFont(new Font("Arial", 18));
        loginButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 10 20;");

        // Botão de registro
        Button registerButton = new Button("Registrar");
        registerButton.setFont(new Font("Arial", 18));
        registerButton.setStyle("-fx-background-color: #007BFF; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 10 20;");

        // Layout para os botões
        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(loginButton, registerButton);

        // Ação do botão de login
        loginButton.setOnAction(e -> {
            String cpfTexto = cpfField.getText();
            String senhaTexto = senhaField.getText();

            // Validar se CPF e Senha foram preenchidos
            if (cpfTexto.isEmpty() || senhaTexto.isEmpty()) {
                mostrarAlerta("Erro", "Por favor, preencha CPF e Senha.");
                return;
            }

            try {
                int cpf = Integer.parseInt(cpfTexto); // Converter CPF para número
                Usuario usuarioEncontrado = repositorioUsuario.buscarCPFreturnUser(cpf);

                if (usuarioEncontrado != null && usuarioEncontrado.getSenha().equals(senhaTexto)) {
                    // Login bem-sucedido -> Abre TelaMenuInicial
                    TelaMenuInicial telaMenu = new TelaMenuInicial(usuarioEncontrado);
                    Stage telaMenuStage = new Stage();
                    telaMenu.start(telaMenuStage);
                    primaryStage.close(); // Fecha a tela de login
                } else {
                    mostrarAlerta("Erro", "CPF ou Senha incorretos!");
                }
            } catch (NumberFormatException ex) {
                mostrarAlerta("Erro", "CPF deve conter apenas números.");
            }
        });

        // Ação do botão de registro
        registerButton.setOnAction(e -> {
            TelaRegister telaRegister = new TelaRegister();
            Stage registerStage = new Stage();
            telaRegister.start(registerStage);
        });

        // Adicionando os elementos ao layout
        layoutPrincipal.getChildren().addAll(label, cpfField, senhaField, buttonBox);
        root.setCenter(layoutPrincipal);

        // Configuração da cena
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
