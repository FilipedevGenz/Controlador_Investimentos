package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controladorinvestimentos.controladorInvestimentos.beans.Carteira;
import org.controladorinvestimentos.controladorInvestimentos.beans.ControladorCarteira;

import java.util.List;

public class TelaCarteiras extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Escolha uma Carteira");

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);

        // Obtém carteiras do repositório
        List<Carteira> listaCarteiras = ControladorCarteira.getInstance().getCarteiras();

        for (Carteira carteira : listaCarteiras) {
            Button carteiraButton = new Button(carteira.getNomeCarteira());
            carteiraButton.setMinWidth(200);
            carteiraButton.setOnAction(e -> abrirCarteiraMenu(carteira, primaryStage));
            vbox.getChildren().add(carteiraButton);
        }

        // Botão de Voltar
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setMinWidth(200);
        btnVoltar.setOnAction(e -> primaryStage.close());
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
