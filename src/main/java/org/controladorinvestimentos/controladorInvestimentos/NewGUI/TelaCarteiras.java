package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Carteira;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Usuario;
import org.controladorinvestimentos.controladorInvestimentos.beans.ControladorCarteira;

import java.util.List;

public class TelaCarteiras extends Application {

    private Usuario usuarioLogado;
    private VBox vbox;
    private Stage primaryStage;

    public TelaCarteiras(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Escolha uma Carteira");

        vbox = new VBox(15);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: #F3F4F6; -fx-padding: 20px; -fx-border-radius: 10px;");

        Label lblTitulo = new Label("Selecione sua Carteira:");
        lblTitulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button btnNovaCarteira = new Button("Criar Nova Carteira");
        btnNovaCarteira.setStyle("-fx-font-size: 14px; -fx-padding: 10px;");
        btnNovaCarteira.setOnAction(e -> criarNovaCarteira());

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setStyle("-fx-font-size: 14px; -fx-padding: 10px;");
        btnVoltar.setOnAction(e -> voltarParaMenuInicial());

        vbox.getChildren().addAll(lblTitulo, btnNovaCarteira);

        carregarCarteiras();

        vbox.getChildren().add(btnVoltar);

        Scene scene = new Scene(vbox, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void carregarCarteiras() {
        ControladorCarteira controladorCarteira = ControladorCarteira.getInstance();
        List<Carteira> listaCarteiras = controladorCarteira.getCarteiras();

        if (listaCarteiras.isEmpty()) {
            controladorCarteira.novaCarteira("001", "Carteira Padrão", 5);
            listaCarteiras = controladorCarteira.getCarteiras();
        }

        for (Carteira carteira : listaCarteiras) {
            Button carteiraButton = new Button(carteira.getNomeCarteira());
            carteiraButton.setMinWidth(200);
            carteiraButton.setOnAction(e -> abrirCarteiraMenu(carteira));
            vbox.getChildren().add(carteiraButton);
        }
    }

    private void criarNovaCarteira() {
        TextInputDialog dialog = new TextInputDialog("Nova Carteira");
        dialog.setTitle("Criar Carteira");
        dialog.setHeaderText("Digite o nome da nova carteira:");
        dialog.setContentText("Nome:");

        dialog.showAndWait().ifPresent(nomeCarteira -> {
            if (!nomeCarteira.trim().isEmpty()) {
                ControladorCarteira controladorCarteira = ControladorCarteira.getInstance();
                String novaID = String.valueOf(System.currentTimeMillis() % 100000);
                controladorCarteira.novaCarteira(novaID, nomeCarteira, 5);

                vbox.getChildren().clear();
                start(primaryStage);
            }
        });
    }

    private void abrirCarteiraMenu(Carteira carteira) {
        try {
            primaryStage.close();
            TelaCarteiraMenu carteiraMenu = new TelaCarteiraMenu(usuarioLogado, carteira);
            carteiraMenu.setCarteira(carteira);
            carteiraMenu.start(new Stage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void voltarParaMenuInicial() {
        try {
            new TelaMenuInicial(usuarioLogado).start(primaryStage); // Reutiliza o mesmo Stage
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
