package org.controladorinvestimentos.controladorInvestimentos.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private Scene loginScene;
    private Scene adicionarAtivoScene;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Cria as telas passando a referência desta MainApp para que elas possam trocar a Scene
        Login loginScreen = new Login(this);
        AdicionarAtivo adicionarAtivoScreen = new AdicionarAtivo(this);

        // Cria as Scenes a partir dos nós raiz fornecidos por cada tela
        loginScene = new Scene(loginScreen.getView(), 500, 400);
        adicionarAtivoScene = new Scene(adicionarAtivoScreen.getView(), 500, 400);

        // Inicia com a tela de Login
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    // Métodos para trocar as Scenes
    public void showLoginScene() {
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
    }

    public void showAdicionarAtivoScene() {
        primaryStage.setScene(adicionarAtivoScene);
        primaryStage.setTitle("Adicionar Ativo");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
