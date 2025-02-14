package org.controladorinvestimentos.controlador_investimentos.GUI;

import org.controladorinvestimentos.controlador_investimentos.beans.ativo;
import org.controladorinvestimentos.controlador_investimentos.beans.Carteira;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import org.controladorinvestimentos.controlador_investimentos.beans.conta;

import java.util.ArrayList;

public class TelaAtivosCarteiras extends Application {

        Carteira carteira;
        conta user;
        TelaAtivosCarteiras(Carteira carteira, conta user){
            this.carteira = carteira;
            this.user = user;
        }

        TelaAtivosCarteiras(){
            this(null,null);
        }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Carteira");

        // Criando o título com fundo verde arredondado
        StackPane titlePane = new StackPane();
        Rectangle titleBackground = new Rectangle(180, 30);
        titleBackground.setArcWidth(15);
        titleBackground.setArcHeight(15);
        titleBackground.setFill(Color.GREEN);

        Text titleText = new Text("Carteira n");
        titleText.setFill(Color.WHITE);
        titleText.setFont(new Font(16));

        titlePane.getChildren().addAll(titleBackground, titleText);
        titlePane.setAlignment(Pos.CENTER);

        // Botão "Comprar ativos"
        Button buyAssetsButton = new Button("Comprar ativos");
        buyAssetsButton.setMinWidth(150);
        buyAssetsButton.setMinHeight(40);
        buyAssetsButton.setStyle("-fx-font-size: 14px; -fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: #A9A9A9;");
        buyAssetsButton.setOnAction(e -> {

            ComprarAtivos next = new ComprarAtivos(carteira, user);
            Stage newUser = new Stage();
            next.start(newUser);
            primaryStage.close();

        });

        // Container dos ativos
        VBox assetContainer = new VBox(15);
        assetContainer.setAlignment(Pos.CENTER);
        assetContainer.setStyle("-fx-padding: 15; -fx-background-color: #d3d3d3;");

        // Criando botões de ativos arredondados e maiores
        ArrayList<ativo> ativos = carteira.repositorioAtvCarteira.getListaAtivos();
        int i = ativos.size();

            for (ativo atv : ativos  ) {

                Double qnt = carteira.getRepositorioRelatorio().getQuantidadeAtivo(atv.nome);

            Button assetButton = new Button("Ativo " + atv.nome + " - R$ " + atv.getPreco() + " - Qnt: " + qnt);
            assetButton.setMinWidth(280);
            assetButton.setMinHeight(60);
            assetButton.setStyle("-fx-font-size: 16px; -fx-background-color: white; -fx-border-radius: 30; -fx-background-radius: 30; -fx-border-color: #A9A9A9;");
            assetContainer.getChildren().add(assetButton);
        }

        // Área rolável para os ativos
        ScrollPane scrollPane = new ScrollPane(assetContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #d3d3d3; -fx-border-color: transparent;");

        // Botão Voltar arredondado
        Button backButton = new Button("Voltar");
        backButton.setMinWidth(100);
        backButton.setMinHeight(40);
        backButton.setStyle("-fx-font-size: 14px; -fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: #A9A9A9;");
        backButton.setOnAction(e -> {

            telaCarteiras next = new telaCarteiras(user);
            Stage newUser = new Stage();
            next.start(newUser);
            primaryStage.close();

        });

        StackPane bottomPane = new StackPane(backButton);
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setStyle("-fx-padding: 10;");

        // Layout principal
        BorderPane root = new BorderPane();
        root.setTop(titlePane);        // Título
        root.setCenter(scrollPane);    // Área rolável com ativos
        root.setBottom(bottomPane);    // Botão "Voltar"

        // Colocando o botão "Comprar ativos" abaixo do título
        VBox topContainer = new VBox(10, titlePane, buyAssetsButton); // Título + botão "Comprar ativos"
        topContainer.setAlignment(Pos.CENTER);

        root.setTop(topContainer); // Substitui a seção superior

        BorderPane.setAlignment(titlePane, Pos.CENTER);



        Scene scene = new Scene(root, 350, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
