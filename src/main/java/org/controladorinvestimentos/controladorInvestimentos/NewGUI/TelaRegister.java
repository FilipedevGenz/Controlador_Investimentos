package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controladorinvestimentos.controladorInvestimentos.Exceptions.Exist;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Usuario;
import org.controladorinvestimentos.controladorInvestimentos.beans.ControladorUsuario;

public class TelaRegister extends Application {

    private ControladorUsuario controladorUsuario = new ControladorUsuario();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Registro de Usuário");

        // Layout principal
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        // Campos de entrada
        Label lblCPF = new Label("CPF:");
        TextField txtCPF = new TextField();
        txtCPF.setPromptText("Digite seu CPF");

        Label lblNome = new Label("Nome:");
        TextField txtNome = new TextField();
        txtNome.setPromptText("Digite seu Nome");

        Label lblEmail = new Label("Email:");
        TextField txtEmail = new TextField();
        txtEmail.setPromptText("Digite seu Email");

        Label lblSenha = new Label("Senha:");
        PasswordField txtSenha = new PasswordField();
        txtSenha.setPromptText("Digite sua Senha");

        // Botão Registrar
        Button btnRegistrar = new Button("Registrar");
        btnRegistrar.setOnAction(e -> {
            try {
                int cpf = Integer.parseInt(txtCPF.getText().trim());
                String nome = txtNome.getText().trim();
                String email = txtEmail.getText().trim();
                String senha = txtSenha.getText().trim();

                // Chama o método de cadastro
                controladorUsuario.cadastrarNovoUsuario(cpf, nome, email, senha);

                mostrarAlerta("Sucesso", "Usuário registrado com sucesso!");

                // Volta para a tela de login após o registro
                Login telaLogin = new Login();
                telaLogin.start(primaryStage);

            } catch (NumberFormatException ex) {
                mostrarAlerta("Erro", "CPF deve ser um número válido.");
            } catch (Exist ex) {
                mostrarAlerta("Erro", ex.getMessage());
            }
        });

        // Botão Voltar corrigido
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            try {
                Stage stageAtual = (Stage) btnVoltar.getScene().getWindow(); // Obtém a referência da janela atual
                stageAtual.close(); // Fecha a tela de registro

                Login telaLogin = new Login();
                telaLogin.start(new Stage()); // Abre a tela de login corretamente

            } catch (Exception ex) {
                ex.printStackTrace();
                mostrarAlerta("Erro", "Não foi possível voltar para a tela de login.");
            }
        });

        // Adiciona os elementos ao grid
        grid.add(lblCPF, 0, 0);
        grid.add(txtCPF, 1, 0);
        grid.add(lblNome, 0, 1);
        grid.add(txtNome, 1, 1);
        grid.add(lblEmail, 0, 2);
        grid.add(txtEmail, 1, 2);
        grid.add(lblSenha, 0, 3);
        grid.add(txtSenha, 1, 3);
        grid.add(btnRegistrar, 0, 4);
        grid.add(btnVoltar, 1, 4);

        // Configuração da cena
        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para exibir alertas
    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}