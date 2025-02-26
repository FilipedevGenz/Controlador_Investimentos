package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controladorinvestimentos.controladorInvestimentos.beans.Carteira;
import org.controladorinvestimentos.controladorInvestimentos.beans.ControladorAtivos;
import org.controladorinvestimentos.controladorInvestimentos.beans.Ativo;
import org.controladorinvestimentos.controladorInvestimentos.beans.ControladorCarteira;
import org.controladorinvestimentos.controladorInvestimentos.NewGUI.MenuInicial;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.controladorinvestimentos.controladorInvestimentos.beans.APIrequest.buscarPrecoAtivoEmTempoReal;

public class TelaCarteiras extends Application {

    @Override
    public void start(Stage primaryStage) {

        List<Carteira> ListaCarteiras = ControladorCarteira.getInstance().getCarteiras();
        if (ListaCarteiras == null || ListaCarteiras.isEmpty()) {
            System.out.println("Nenhuma carteira encontrada.");
            return;
        }

        primaryStage.setTitle("Menu de Carteiras");

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);

        // Cria botões dinamicamente para cada carteira
        for (Carteira carteira : ListaCarteiras) {
            Button carteiraButton = new Button(carteira.getNomeCarteira());
            carteiraButton.setMinWidth(200);
            carteiraButton.setOnAction(e -> {
                // Exemplo: abre o menu específico da carteira
                CarteiraMenu carteiraMenu = new CarteiraMenu();
                carteiraMenu.setCarteira(carteira);
                try {
                    carteiraMenu.start(primaryStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            vbox.getChildren().add(carteiraButton);
        }

        // Botão de Voltar (opcional: volta para o MenuInicial)
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setMinWidth(200);
        btnVoltar.setOnAction(e -> {
            MenuInicial menuInicial = new MenuInicial();
            try {
                menuInicial.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        vbox.getChildren().add(btnVoltar);

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
