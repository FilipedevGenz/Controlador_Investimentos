package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Carteira;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Usuario;

class TelaSimuladorInvestimento extends Application {

     private Carteira carteira;
     private Usuario usuarioLogado; // Adicionado para manter o usuário logado

     // Novo construtor para receber o usuário logado e a carteira
     public TelaSimuladorInvestimento(Usuario usuario, Carteira carteira) {
         this.usuarioLogado = usuario;
         this.carteira = carteira;
     }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Simulador de Investimentos");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label lblValorInicial = new Label("Valor Inicial:");
        GridPane.setConstraints(lblValorInicial, 0, 0);

        TextField txtValorInicial = new TextField();
        GridPane.setConstraints(txtValorInicial, 1, 0);

        Label lblTaxa = new Label("Taxa Anual (%):");
        GridPane.setConstraints(lblTaxa, 0, 1);

        TextField txtTaxa = new TextField();
        GridPane.setConstraints(txtTaxa, 1, 1);

        Label lblAnos = new Label("Anos:");
        GridPane.setConstraints(lblAnos, 0, 2);

        TextField txtAnos = new TextField();
        GridPane.setConstraints(txtAnos, 1, 2);

        Label lblAporte = new Label("Aporte Mensal:");
        GridPane.setConstraints(lblAporte, 0, 3);

        TextField txtAporte = new TextField();
        GridPane.setConstraints(txtAporte, 1, 3);

        Button btnCalcular = new Button("Calcular");
        GridPane.setConstraints(btnCalcular, 1, 4);

        Label lblResultado = new Label("Resultado: ");
        GridPane.setConstraints(lblResultado, 1, 5);

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            if (carteira != null) {
                TelaCarteiraMenu telaCarteiraMenu = new TelaCarteiraMenu(usuarioLogado,carteira);
                telaCarteiraMenu.setCarteira(carteira); // Passa a carteira corretamente
                try {
                    telaCarteiraMenu.start(primaryStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("Erro: Nenhuma carteira foi definida antes de voltar.");
            }
        });

        GridPane.setConstraints(btnVoltar, 1, 6);

        grid.getChildren().addAll(lblValorInicial, txtValorInicial, lblTaxa, txtTaxa, lblAnos, txtAnos, lblAporte, txtAporte, btnCalcular, lblResultado, btnVoltar);

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}