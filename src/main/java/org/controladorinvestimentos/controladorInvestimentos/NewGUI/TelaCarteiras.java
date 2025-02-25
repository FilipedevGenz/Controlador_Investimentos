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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.controladorinvestimentos.controladorInvestimentos.beans.APIrequest.buscarPrecoAtivoEmTempoReal;

public class TelaCarteiras extends Application {

    Ativo ativo1 = new Ativo("PETR4",buscarPrecoAtivoEmTempoReal("PETR4"));
    Ativo ativo2 = new Ativo("PETR4",buscarPrecoAtivoEmTempoReal("HAPV3"));
    Ativo ativo3 = new Ativo("CSAN3",buscarPrecoAtivoEmTempoReal("CSAN3"));

    // adicionarAtivoNaCarteira(String codeAtv, double quantidade)
    Carteira carteira1 = new Carteira("900.309-01", "Carteira1");
    Carteira carteira2 = new Carteira("900.309-02", "Carteira2");

    List<Carteira> carteiras = new ArrayList<>();

    carteira1.adicionarAtivoNaCarteira("PETR4", 5);
    carteira1.adicionarAtivoNaCarteira("CSAN3", 3);
    carteira2.adicionarAtivoNaCarteira("HAPV3", 2);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Menu de Carteiras");

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);

        // Criando botões de carteiras
        for (int i = 1; i <= 5; i++) { // Número de carteiras pode ser ajustado
            Button carteiraButton = new Button("Carteira " + i);
            carteiraButton.setMinWidth(200);
            carteiraButton.setOnAction(e -> {
                // Ao clicar, abre a CarteiraMenu
                CarteiraMenu carteiraMenu = new CarteiraMenu();
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
