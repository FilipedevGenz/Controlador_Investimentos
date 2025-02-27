package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controladorinvestimentos.controladorInvestimentos.beans.Carteira;

public class TelaCarteiraMenu extends Application {

    private Carteira carteira;

    public TelaCarteiraMenu() {}

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }

    @Override
    public void start(Stage primaryStage) {
        // Nome da carteira
        Text carteiraTitulo = new Text(carteira.getNomeCarteira());
        carteiraTitulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Botão para comprar ativos
        Button btnComprarAtivos = new Button("Comprar Ativos");
        btnComprarAtivos.setOnAction(e -> {
            TelaCompraAtivos telaCompraAtivos = new TelaCompraAtivos(carteira);
            try {
                telaCompraAtivos.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Botão para vender ativos (Novo botão adicionado)
        Button btnVenderAtivos = new Button("Venda de Ativos");
        btnVenderAtivos.setOnAction(e -> {
            TelaVendaAtivos telaVendaAtivos = new TelaVendaAtivos(carteira);
            try {
                telaVendaAtivos.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Botão para acompanhar rentabilidade
        Button btnRentabilidade = new Button("Rentabilidade ou Acompanhamento");
        btnRentabilidade.setOnAction(e -> {
            TelaAcompanharRentCart telaRentabilidade = new TelaAcompanharRentCart(carteira);
            try {
                telaRentabilidade.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Botão para projeções
        Button btnProjecoes = new Button("Projeções");
        btnProjecoes.setOnAction(e -> {
            TelaProjecoes telaProjecoes = new TelaProjecoes();
            try {
                // telaProjecoes.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Botão de Voltar para TelaCarteiras
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            TelaCarteiras telaCarteiras = new TelaCarteiras();
            try {
                telaCarteiras.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Layout atualizado com o novo botão de venda de ativos
        VBox layout = new VBox(15, carteiraTitulo, btnComprarAtivos, btnVenderAtivos, btnRentabilidade, btnProjecoes, btnVoltar);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #F3F4F6; -fx-padding: 20px; -fx-border-radius: 10px;");

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setTitle("Menu da Carteira");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
