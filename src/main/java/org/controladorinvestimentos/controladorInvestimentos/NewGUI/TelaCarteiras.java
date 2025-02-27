package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Carteira;
import org.controladorinvestimentos.controladorInvestimentos.beans.ControladorCarteira;

import java.util.List;

public class TelaCarteiras extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Escolha uma Carteira");

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            try {
                new TelaMenuInicial().start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(15, btnVoltar);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #F3F4F6; -fx-padding: 20px; -fx-border-radius: 10px;");
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);

        // Obtém carteiras do repositório
        List<Carteira> listaCarteiras = ControladorCarteira.getInstance().getCarteiras();

        for (Carteira carteira : listaCarteiras) {
            Button carteiraButton = new Button(carteira.getNomeCarteira());
            carteiraButton.setMinWidth(200);
            carteiraButton.setOnAction(e -> abrirCarteiraMenu(carteira, primaryStage));
            vbox.getChildren().add(carteiraButton);
        }

        vbox.getChildren().add(btnVoltar);
        Scene scene = new Scene(vbox, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void abrirCarteiraMenu(Carteira carteira, Stage primaryStage) {
        TelaCarteiraMenu carteiraMenu = new TelaCarteiraMenu();
        carteiraMenu.setCarteira(carteira);
        try {
            carteiraMenu.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}