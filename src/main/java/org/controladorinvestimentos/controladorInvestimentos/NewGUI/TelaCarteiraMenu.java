package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Carteira;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Usuario;
import org.controladorinvestimentos.controladorInvestimentos.beans.Simulador;

public class TelaCarteiraMenu extends Application {

    private Carteira carteira;
    private Usuario usuarioLogado;

    public TelaCarteiraMenu(Usuario usuario, Carteira carteira) {
        this.usuarioLogado = usuario;
        this.carteira = carteira;

    }
    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }

    @Override
    public void start(Stage primaryStage) {
        // Nome da carteira
        Text carteiraTitulo = new Text(carteira.getNomeCarteira());
        carteiraTitulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Botões de navegação
        Button btnComprarAtivos = new Button("Comprar Ativos");

        btnComprarAtivos.setOnAction(e -> {
            try {
                TelaCompraAtivos telaCompraAtivos = new TelaCompraAtivos(usuarioLogado, carteira);
                telaCompraAtivos.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button btnVenderAtivos = new Button("Vender Ativos");
        btnVenderAtivos.setOnAction(e -> {
            TelaVendaAtivos telaVendaAtivos = new TelaVendaAtivos(usuarioLogado, carteira);
            try {
                telaVendaAtivos.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button btnSimulacao = new Button("Simulação");
        btnSimulacao.setOnAction(e -> {
            ProjecaoInvestimentos telaSimuladorInvestimento = new  ProjecaoInvestimentos(usuarioLogado,carteira);
            try {
                telaSimuladorInvestimento.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button btnRentabilidade = new Button("Rentabilidade ou Acompanhamento");
        btnRentabilidade.setOnAction(e -> {
            TelaAcompanharRentCart telaRentabilidade = new TelaAcompanharRentCart(usuarioLogado,carteira);
            try {
                telaRentabilidade.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        Button btnCalcularValorCarteira = new Button("Calcular Valor da Carteira");
        btnCalcularValorCarteira.setOnAction(e -> {
            try {
                double valorCarteira = Simulador.atualizarValorCarteira(carteira);
                carteiraTitulo.setText("Valor da Carteira: R$ " + String.format("%.2f", valorCarteira));
            } catch (Exception ex) {
                ex.printStackTrace();
                carteiraTitulo.setText("Erro ao calcular o valor da carteira.");
            }
        });

        // Botão de Voltar para TelaCarteiras
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            TelaCarteiras telaCarteiras = new TelaCarteiras(usuarioLogado);
            try {
                telaCarteiras.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Layout
        VBox layout = new VBox(15, carteiraTitulo, btnComprarAtivos, btnVenderAtivos, btnRentabilidade, btnSimulacao, btnCalcularValorCarteira, btnVoltar);
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