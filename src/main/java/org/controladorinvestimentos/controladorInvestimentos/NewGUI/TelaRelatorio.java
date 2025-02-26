package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controladorinvestimentos.controladorInvestimentos.beans.Carteira;
import org.controladorinvestimentos.controladorInvestimentos.beans.Relatorio;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioRelatorio;
import java.util.List;

public class TelaRelatorios extends Application {
    private Carteira carteira;

    public TelaRelatorios(Carteira carteira) {
        this.carteira = carteira;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Relatórios da Carteira");

        VBox layout = new VBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(10);

        Label titulo = new Label("Relatório da Carteira: " + carteira.getNomeCarteira());
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TableView<Relatorio> tabela = new TableView<>();

        TableColumn<Relatorio, String> colunaAtivo = new TableColumn<>("Ativo");
        colunaAtivo.setCellValueFactory(new PropertyValueFactory<>("nomeAtivo"));

        TableColumn<Relatorio, String> colunaCodigo = new TableColumn<>("Código");
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

        TableColumn<Relatorio, Double> colunaQuantidade = new TableColumn<>("Quantidade");
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        TableColumn<Relatorio, Double> colunaValorCompra = new TableColumn<>("Valor de Compra");
        colunaValorCompra.setCellValueFactory(new PropertyValueFactory<>("valorCompra"));

        TableColumn<Relatorio, Double> colunaValorTotal = new TableColumn<>("Valor Total");
        colunaValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));

        tabela.getColumns().addAll(colunaAtivo, colunaCodigo, colunaQuantidade, colunaValorCompra, colunaValorTotal);

        List<Relatorio> relatorios = carteira.getRepositorioRelatorio().getRelatorios();
        tabela.getItems().addAll(relatorios);

        layout.getChildren().addAll(titulo, tabela);
        Scene scene = new Scene(layout, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
