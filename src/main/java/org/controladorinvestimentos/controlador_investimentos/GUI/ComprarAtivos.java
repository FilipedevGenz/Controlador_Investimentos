package org.controladorinvestimentos.controlador_investimentos.GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controladorinvestimentos.controlador_investimentos.Banco.repositorioAtivos;
import org.controladorinvestimentos.controlador_investimentos.Banco.repositorioAtivosCarteira;
import org.controladorinvestimentos.controlador_investimentos.beans.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.controladorinvestimentos.controlador_investimentos.beans.controladorRelatorio.criarRelatorio;

public class ComprarAtivos extends Application {


    carteira carteira;
    conta user;

    ComprarAtivos(carteira carteira,conta user) {
        this.carteira = carteira;
        this.user = user;
    }

    ComprarAtivos() {
        this(null,null);
    }

    @Override
    public void start(Stage primaryStage) {
        carteira.repositorioAtvCarteira = new repositorioAtivosCarteira();

        VBox container = new VBox(10);
        container.setPadding(new Insets(20));
        container.setStyle("-fx-background-color: #999; -fx-alignment: center;");

        Text title = new Text("Comprar Ativos");
        title.setFont(new Font("Arial", 20));

        HBox header = new HBox(50);
        header.setAlignment(Pos.CENTER);
        header.getChildren().addAll(new Text("Ativo"), new Text("Valor"), new Text("Quantidade"));

        repositorioAtivos ativo = repositorioAtivos.getInstance();
        ArrayList<ativo> listAtivos = ativo.getAtivos();
        int i = listAtivos.size();

        VBox itemList = new VBox(10);
        for (int j = 0; j < i; j++) {
            itemList.getChildren().add(createItemRow(listAtivos.get(j).nome, String.valueOf(listAtivos.get(j).getPreco()), "1"));
        }

        container.getChildren().addAll(title, header, itemList);

        Scene scene = new Scene(container, 400, 300);
        primaryStage.setTitle("Comprar Ativos");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createItemRow(String nome1, String valor1, String quantidade1) {
        HBox row = new HBox(50);
        row.setAlignment(Pos.CENTER);
        row.setStyle("-fx-background-color: #667; -fx-padding: 10; -fx-background-radius: 10;");

        // Nome do ativo
        Text nome = new Text(nome1);

        // Valor do ativo
        Text valor = new Text(valor1);

        // Caixa de texto para quantidade
        TextField quantidade = new TextField(quantidade1);  // Usando o valor de quantidade passado
        quantidade.setPromptText("Digite a quantidade");
        quantidade.setMaxWidth(60);

        // Validar para aceitar apenas números decimais (double)
        quantidade.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {  // Expressão regular para aceitar números e decimais
                quantidade.setText(oldValue);  // Se o texto não for válido, volta ao anterior
            }
        });

        // Botão de comprar
        Button comprarBtn = new Button("Comprar");
        comprarBtn.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10;");
        comprarBtn.setOnAction(e -> {
            try {

                // Tentando obter o valor de quantidade
                double quantidadeCompra = Double.parseDouble(quantidade.getText());
                double valorAtv = Double.parseDouble(valor1);

                if (user.getSaldo() >= quantidadeCompra * valorAtv ) {

                    user.debitar(quantidadeCompra * valorAtv);

                    repositorioAtivos ativo = repositorioAtivos.getInstance();
                    ArrayList<ativo> listAtivos = ativo.getAtivos();
                    ativo ativoadd =listAtivos.stream().filter(ativo1 -> ativo1.nome.equals(nome1)).findFirst().orElse(null);

                    carteira.adicionarAtivoNaCarteira(ativoadd, quantidadeCompra);
                    relatorio newRelatorio = criarRelatorio(nome1, valorAtv, LocalDate.now(), quantidadeCompra);
                    carteira.getRepositorioRelatorio().addRelatorio(newRelatorio);
                    carteira.atualizarValorCarteira();
                }
            } catch (NumberFormatException ex) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Valor Invalido");
                alert.setHeaderText("Valor Invalido");
                alert.setContentText("Por favor, insira uma quantidade válida.");

                alert.showAndWait();

            }
        });

        // Adicionando os elementos à linha
        row.getChildren().addAll(nome, valor, quantidade, comprarBtn);
        return row;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
