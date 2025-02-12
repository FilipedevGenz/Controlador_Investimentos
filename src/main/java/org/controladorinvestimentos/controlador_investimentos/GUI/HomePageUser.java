package org.controladorinvestimentos.controlador_investimentos.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controladorinvestimentos.controlador_investimentos.Banco.RepositorioAtivos;
import org.controladorinvestimentos.controlador_investimentos.beans.Ativo;

public class HomePageUser {

    private RepositorioAtivos repositorioAtivos;
    private double saldoUsuario = 10000.0; // Exemplo de saldo do usuário

    public HomePageUser() {
        this.repositorioAtivos = RepositorioAtivos.getInstance();
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Compra de Ativos");

        TableView<Ativo> tabelaAtivos = new TableView<>();
        ObservableList<Ativo> listaAtivos = FXCollections.observableArrayList(repositorioAtivos.getAtivos());

        TableColumn<Ativo, String> colunaNome = new TableColumn<>("Ativo");
        colunaNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());

        TableColumn<Ativo, Double> colunaPreco = new TableColumn<>("Preço");
        colunaPreco.setCellValueFactory(cellData -> cellData.getValue().precoProperty().asObject());

        TableColumn<Ativo, Double> colunaQuantidade = new TableColumn<>("Quantidade Comprada");
        colunaQuantidade.setCellValueFactory(cellData -> cellData.getValue().quantidadeCompradaProperty().asObject());

        TableColumn<Ativo, String> colunaAcao = new TableColumn<>("Comprar");
        colunaAcao.setCellFactory(param -> new TableCell<>() {
            final TextField inputQuantidade = new TextField();
            final Button botaoCompra = new Button("Comprar");

            {
                inputQuantidade.setPromptText("Qtd");
                botaoCompra.setOnAction(event -> {
                    Ativo ativo = getTableView().getItems().get(getIndex());
                    try {
                        double quantidade = Double.parseDouble(inputQuantidade.getText());
                        double custo = quantidade * ativo.getPreco();
                        if (quantidade > 0 && custo <= saldoUsuario) {
                            saldoUsuario -= custo;
                            ativo.comprar(quantidade);
                            tabelaAtivos.refresh();
                        } else {
                            mostrarAlerta("Saldo insuficiente ou quantidade inválida.");
                        }
                    } catch (NumberFormatException e) {
                        mostrarAlerta("Digite um número válido.");
                    }
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    VBox vbox = new VBox(inputQuantidade, botaoCompra);
                    vbox.setSpacing(5);
                    setGraphic(vbox);
                }
            }
        });

        tabelaAtivos.setItems(listaAtivos);
        tabelaAtivos.getColumns().addAll(colunaNome, colunaPreco, colunaQuantidade, colunaAcao);

        Label saldoLabel = new Label("Saldo disponível: R$ " + saldoUsuario);
        saldoLabel.setPadding(new Insets(10, 0, 10, 0));

        VBox layout = new VBox(10, saldoLabel, tabelaAtivos);
        layout.setPadding(new Insets(15));
        primaryStage.setScene(new Scene(layout, 600, 400));
        primaryStage.show();
    }

    private void mostrarAlerta(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
