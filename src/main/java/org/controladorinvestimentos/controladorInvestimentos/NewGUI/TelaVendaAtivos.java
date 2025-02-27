package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.controladorinvestimentos.controladorInvestimentos.beans.APIfuncionalidades.HistoricoDosAtivos;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Carteira;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Relatorio;

import java.time.LocalDate;
import java.util.List;

public class TelaVendaAtivos extends Application {
    private Carteira carteira;

    public TelaVendaAtivos(Carteira carteira) {
        this.carteira = carteira;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(carteira.getNomeCarteira());

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        Label lblTitulo = new Label("Venda de Ativos");
        lblTitulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        grid.add(lblTitulo, 0, 0, 5, 1);

        grid.add(new Label("Ativo"), 4, 1);
        grid.add(new Label("Preço Compra"), 3, 1);
        grid.add(new Label("Preço Atual"), 2, 1);
        grid.add(new Label("Quantidade"), 1, 1);
        grid.add(new Label("Ação"), 0, 1);

        List<Relatorio> ativos = carteira.repositorioRelatorio.getRelatorios();
        for (int i = 0; i < ativos.size(); i++) {
            Relatorio ativo = ativos.get(i);
            Button btnVender = new Button("Vender");
            int index = i;
            btnVender.setOnAction(e -> venderAtivo(ativo, index));
            grid.add(btnVender, 0, i + 2);

            TextField txtQuantidade = new TextField(String.valueOf((int) ativo.getQuantidade()));
            txtQuantidade.setMaxWidth(50);
            grid.add(txtQuantidade, 1, i + 2);

            double precoCompra = HistoricoDosAtivos.obterPrecoDeCompra(ativo.getCodigo(), LocalDate.now().minusMonths(6));
            grid.add(new Label("R$ " + String.format("%.2f", precoCompra)), 3, i + 2);
            grid.add(new Label("R$ " + String.format("%.2f", ativo.getValorCompra())), 2, i + 2);
            grid.add(new Label(ativo.getNomeAtivo()), 4, i + 2);
        }

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> primaryStage.close());
        grid.add(btnVoltar, 0, ativos.size() + 3);

        Scene scene = new Scene(grid, 600, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void venderAtivo(Relatorio ativo, int index) {
        try {
            carteira.removerAtivo(ativo.getCodigo(), ativo.getQuantidade());
            mostrarAlerta("Sucesso", "Ativo vendido com sucesso!");
        } catch (Exception e) {
            mostrarAlerta("Erro", "Falha ao vender ativo.");
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
