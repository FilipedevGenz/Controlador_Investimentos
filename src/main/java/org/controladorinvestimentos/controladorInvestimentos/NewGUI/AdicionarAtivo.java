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
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioAtivos;
import org.controladorinvestimentos.controladorInvestimentos.beans.APIfuncionalidades.APIrequest;

import java.io.IOException;

public class AdicionarAtivo extends Application {

    private RepositorioAtivos repositorioAtivos = RepositorioAtivos.getInstance();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Adicionar Ativo");

        BorderPane root = new BorderPane();
        VBox layoutPrincipal = new VBox(20);
        layoutPrincipal.setPadding(new Insets(20));
        layoutPrincipal.setAlignment(Pos.CENTER);

        // Título
        Label title = new Label("Adicionar Ativo");
        title.setFont(new Font("Arial", 28));

        // Campo de busca
        TextField searchField = new TextField();
        searchField.setPromptText("Buscar Ativo");
        searchField.setStyle("-fx-font-size: 16; -fx-padding: 10; -fx-background-radius: 20;");

        // Botão Adicionar
        Button addButton = new Button("Adicionar");
        addButton.setFont(new Font("Arial", 18));
        addButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 15 30;");

        addButton.setOnAction(e -> {
            String nomeAtivo = searchField.getText().trim();
            if (nomeAtivo.isEmpty()) {
                showAlert("Campo vazio", "Nenhum ativo inserido", "Digite o nome de um ativo antes de adicionar.");
                return;
            }

            try {
                // Verifica se o ativo já existe no repositório
                try {
                    repositorioAtivos.buscarAtivo(nomeAtivo);
                    showAlert("Erro", "Ativo duplicado", "Este ativo já está cadastrado no sistema.");
                    return;
                } catch (Exception ignored) {
                    // Se lançar exceção, significa que o ativo não existe e pode ser adicionado.
                }

                double preco = APIrequest.buscarPrecoAtivoEmTempoReal(nomeAtivo);

                if (preco <= 0) {
                    showAlert("Erro", "Preço inválido", "O preço do ativo não pode ser zero ou negativo.");
                    return;
                }

                repositorioAtivos.adicionarAtivo(nomeAtivo, preco);
                showAlert("Sucesso", "Ativo adicionado!", "O ativo foi adicionado com sucesso.");
            } catch (IOException ex) {
                showAlert("Erro", "Erro na API", "Falha ao obter informações do ativo. Verifique se o nome está correto.");
            } catch (Exception ex) {
                showAlert("Erro", "Erro inesperado", "Houve um erro ao adicionar o ativo. Verifique sua conexão.");
            }
        });

        // Botão Voltar
        Button backButton = new Button("Voltar");
        backButton.setFont(new Font("Arial", 18));
        backButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 15 30;");

        backButton.setOnAction(e -> {
            try {
                new TelaInicial().start(primaryStage); // Reutiliza a mesma janela
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert("Erro", "Erro ao voltar", "Não foi possível voltar para a tela inicial.");
            }
        });

        // Layout para os botões
        HBox buttonBox = new HBox(20, addButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        layoutPrincipal.getChildren().addAll(title, searchField, buttonBox);
        root.setCenter(layoutPrincipal);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
