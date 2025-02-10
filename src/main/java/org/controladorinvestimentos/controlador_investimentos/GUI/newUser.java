package org.controladorinvestimentos.controlador_investimentos.GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import org.controladorinvestimentos.controlador_investimentos.Banco.RepositorioUsers;
import org.controladorinvestimentos.controlador_investimentos.Banco.iRepositorioUsers;

public class newUser extends Application {

    private static iRepositorioUsers repositorio;

    @Override
    public void start(Stage primaryStage) {
        // Título
        Text title = new Text("Criar uma nova conta");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        // Subtítulo
        Text subtitle = new Text("É rápido e fácil");
        subtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        subtitle.setFill(Color.GRAY);

        // Campos de entrada
        TextField nameField = createRoundedTextField("Nome");
        TextField cpfField = createRoundedTextField("Cpf");
        TextField emailField = createRoundedTextField("Email");
        PasswordField passwordField = createRoundedPasswordField("Senha");

        // Botão de cadastro
        Button signUpButton = new Button("Cadastre-se");
        signUpButton.setStyle("-fx-background-color: #008000; -fx-text-fill: white;" +
                " -fx-font-size: 14px; -fx-background-radius: 20;");
        signUpButton.setMinWidth(200);

        signUpButton.setOnAction(e -> {

            String nome = nameField.getText();
            String email = emailField.getText();
            String userCPF = cpfField.getText();
            String password = passwordField.getText();

            try {
                Integer cpf = Integer.parseInt(userCPF);
                if (repositorio.buscarCPF(cpf)){

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ja existe esse usuario");
                    alert.setHeaderText("esse CPF ja pertence a um usuario");
                    alert.setContentText("O CPF informado não é válido. Por favor, insira um CPF válido.");

                    alert.showAndWait();
                }

                repositorio.construtorUsuario(cpf,nome,email,password);


        }catch (NumberFormatException ex) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro de Formato");
                alert.setHeaderText("CPF Inválido");
                alert.setContentText("O CPF informado não é válido. Por favor, insira um CPF válido.");

                alert.showAndWait();
            }
        });

        // Texto de login
        Text loginText = new Text("Já tem uma conta?");
        loginText.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        loginText.setFill(Color.BLACK);
        loginText.setOnMouseClicked(event -> {

            Login login = new Login();
            Stage loginStage = new Stage();
            login.start(loginStage);
            primaryStage.close();

        });

        // Layout principal
        VBox root = new VBox(10, title, subtitle, nameField, cpfField, emailField, passwordField, signUpButton, loginText);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 20; -fx-background-radius: 20;");
        root.setMinWidth(350);

        StackPane container = new StackPane(root);
        container.setStyle("-fx-background-color: #F5F5F5; -fx-padding: 50;");

        // Cena e exibição
        Scene scene = new Scene(container, 500, 400);
        primaryStage.setTitle("Cadastro");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TextField createRoundedTextField(String placeholder) {
        TextField textField = new TextField();
        textField.setPromptText(placeholder);
        textField.setStyle("-fx-background-radius: 20; -fx-background-color: #D3D3D3; -fx-padding: 10;" +
                " -fx-border-width: 0; -fx-font-size: 14px;");
        textField.setMaxWidth(250);
        return textField;
    }

    private PasswordField createRoundedPasswordField(String placeholder) {
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(placeholder);
        passwordField.setStyle("-fx-background-radius: 20; -fx-background-color: #D3D3D3" +
                "; -fx-padding: 10; -fx-border-width: 0; -fx-font-size: 14px;");
        passwordField.setMaxWidth(250);
        return passwordField;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
