package org.controladorinvestimentos.controladorInvestimentos.NewGUI;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
                showAlert("Campo vazio", "Nenhum ativo inserido", "Por favor, digite o nome de um ativo antes de adicionar.");
                return;
            }
            try {
                double preco = APIrequest.buscarPrecoAtivoEmTempoReal(nomeAtivo);
                repositorioAtivos.adicionarAtivo(nomeAtivo, preco);
                showAlert("Sucesso", "Ativo adicionado!", "O ativo foi adicionado com sucesso ao repositório.");
            } catch (IOException ex) {
                showAlert("Erro", "Ativo inválido", "O ativo inserido não é válido ou não foi encontrado. Tente novamente.");
            }
        });

        // Botão Voltar
        Button voltarButton = new Button("Voltar");
        voltarButton.setFont(new Font("Arial", 18));
        voltarButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 15 30;");
        voltarButton.setOnAction(e -> primaryStage.close());

        HBox buttonBox = new HBox(20, addButton, voltarButton);
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
