package org.controladorinvestimentos.controlador_investimentos.GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controladorinvestimentos.controlador_investimentos.beans.conta;

public class menuUser extends Application {

    conta user;

    menuUser(){
        this(null);
    }

    menuUser(conta conta){
        user = conta;
    }

    @Override
    public void start(Stage primaryStage) {
        // Botões
        Button btnSair = new Button("Sair");
        Button btnCarteiras = new Button("Carteiras");
        Button btnComprarAtivos = new Button("Comprar ativos");

        // Usando HBox para os botões ficarem lado a lado
        HBox buttonsBox = new HBox(10, btnCarteiras, btnComprarAtivos);
        buttonsBox.setAlignment(Pos.CENTER);

        // Colocando os botões no VBox, com o botão "Sair" abaixo
        VBox menuBox = new VBox(10, buttonsBox, btnSair);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setPadding(new Insets(20));

        Scene menuScene = new Scene(menuBox, 350, 300);

        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Menu");
        primaryStage.show();

        //funcionamento dos botoes
        btnSair.setOnAction(e -> {
            System.exit(0);
        });

        btnCarteiras.setOnAction(e -> {
            telaCarteiras next = new telaCarteiras();
            Stage carteiras = new Stage() ;
            next.start(carteiras);
            primaryStage.close();
        });


        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Menu");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
