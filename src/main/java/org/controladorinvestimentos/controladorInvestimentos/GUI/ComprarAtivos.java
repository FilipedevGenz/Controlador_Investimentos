package org.controladorinvestimentos.controladorInvestimentos.GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioAtivos;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioMovimentacoes;
import org.controladorinvestimentos.controladorInvestimentos.beans.*;

import static org.controladorinvestimentos.controladorInvestimentos.beans.ControladorRelatorio.criarRelatorio;

public class ComprarAtivos extends Application {

    Carteira carteira;
    conta user;

    ComprarAtivos(Carteira carteira, conta user) {
        this.carteira = carteira;
        this.user = user;
    }

    ComprarAtivos() {
        this(null, null);
    }

    @Override
    public void start(Stage primaryStage) {
        carteira.repositorioAtvCarteira = new RepositorioMovimentacoes();

        VBox container = new VBox(10);
        container.setPadding(new Insets(20));
        container.setStyle("-fx-background-color: #999; -fx-alignment: center;");

        Text title = new Text("Comprar Ativos");
        title.setFont(new Font("Arial", 20));

        HBox header = new HBox(50);
        header.setAlignment(Pos.CENTER);
        header.getChildren().addAll(new Text("Ativo"), new Text("Valor"), new Text("Quantidade"));

        RepositorioAtivos ativo = RepositorioAtivos.getInstance();
      //  ArrayList<ativo> listAtivos = ativo.getAtivos();

        VBox itemList = new VBox(10);
        for (Ativo atv : listAtivos) {
            itemList.getChildren().add(createItemRow(atv.nome, String.valueOf(atv.getPreco()), "1"));
        }

        Button voltarBtn = new Button("Voltar");
        voltarBtn.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10;");
        voltarBtn.setOnAction(e -> {
                TelaAtivosCarteiras next = new TelaAtivosCarteiras(carteira, user);
                Stage newUser = new Stage();
                next.start(newUser);
                primaryStage.close();
        });

        container.getChildren().addAll(title, header, itemList, voltarBtn);

        Scene scene = new Scene(container, 400, 350);
        primaryStage.setTitle("Comprar Ativos");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createItemRow(String nome1, String valor1, String quantidade1) {
        HBox row = new HBox(50);
        row.setAlignment(Pos.CENTER);
        row.setStyle("-fx-background-color: #667; -fx-padding: 10; -fx-background-radius: 10;");

        Text nome = new Text(nome1);
        Text valor = new Text(valor1);

        TextField quantidade = new TextField(quantidade1);
        quantidade.setPromptText("Digite a quantidade");
        quantidade.setMaxWidth(60);

        quantidade.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                quantidade.setText(oldValue);
            }
        });

        Button comprarBtn = new Button("Comprar");
        comprarBtn.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10;");
        comprarBtn.setOnAction(e -> {
            try {
                double quantidadeCompra = Double.parseDouble(quantidade.getText());
                double valorAtv = Double.parseDouble(valor1);

              //  if (user.getSaldo() >= quantidadeCompra * valorAtv) {
               //     user.debitar(quantidadeCompra * valorAtv);

                    RepositorioAtivos ativo = RepositorioAtivos.getInstance();
                  //  ArrayList<ativo> listAtivos = ativo.getAtivos();
                   // ativo ativoadd = listAtivos.stream().filter(ativo1 -> ativo1.nome.equals(nome1)).findFirst().orElse(null);

                    //carteira.adicionarAtivoNaCarteira(ativoadd, quantidadeCompra);
                  //  Relatorio newRelatorio = criarRelatorio(nome1, valorAtv, LocalDate.now(), quantidadeCompra);
                   // carteira.getRepositorioRelatorio().addRelatorio(newRelatorio);
                    //carteira.atualizarValorCarteira();

                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Compra Realizada");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("A compra de " + quantidadeCompra + " unidades de " + nome1 + " foi realizada com sucesso!");
                    successAlert.showAndWait();
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Saldo Insuficiente");
                    errorAlert.setHeaderText("Erro na Compra");
                    errorAlert.setContentText("Você não tem saldo suficiente para realizar esta compra.");
                    errorAlert.showAndWait();
                }
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Valor Inválido");
                alert.setHeaderText("Erro na Compra");
                alert.setContentText("Por favor, insira uma quantidade válida.");
                alert.showAndWait();
            }
        });

        row.getChildren().addAll(nome, valor, quantidade, comprarBtn);
        return row;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
