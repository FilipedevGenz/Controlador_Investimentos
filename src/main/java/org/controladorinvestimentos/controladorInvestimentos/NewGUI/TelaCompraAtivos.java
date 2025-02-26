package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioAtivos;
import org.controladorinvestimentos.controladorInvestimentos.beans.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioAtivos;

public class TelaCompraAtivos extends Application {
    private Carteira carteira;

    public TelaCompraAtivos(Carteira carteira) {
        this.carteira = carteira;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Compra de Ativos");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        Label lblTitulo = new Label("Compra de Ativos");
        lblTitulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        grid.add(lblTitulo, 0, 0, 5, 1);

        // Cabeçalhos das colunas
        grid.add(new Label("Ativo"), 4, 1);
        grid.add(new Label("Variação"), 3, 1);
        grid.add(new Label("Preço Atual"), 2, 1);
        grid.add(new Label("Quantidade"), 1, 1);
        grid.add(new Label("Ação"), 0, 1);

        for (int i = 0; i < ATIVOS.size(); i++) {
            Relatorio ativo = getATIVOS.size(i);
            LocalDate dataCompra = LocalDate.now().minusMonths(6);
            double variacao = HistoricoDosAtivos.calcularTaxaDeVariacao(ativo.getCodigo(), dataCompra);

            grid.add(new Label(String.format("%.2f%%", variacao)), 3, i + 2);
        }

        // Inicializando ativos no repositório
        RepositorioAtivos repositorioAtivos = RepositorioAtivos.getInstance();
        try {
            repositorioAtivos.adicionarAtivo("VALE3", 69.45);
            repositorioAtivos.adicionarAtivo("ITUB4", 28.90);
            repositorioAtivos.adicionarAtivo("BBDC4", 23.40);
            repositorioAtivos.adicionarAtivo("BBAS3", 47.30);
            repositorioAtivos.adicionarAtivo("ABEV3", 14.80);
            repositorioAtivos.adicionarAtivo("MGLU3", 2.30);
            repositorioAtivos.adicionarAtivo("PETR3", 30.50);
            repositorioAtivos.adicionarAtivo("LREN3", 31.75);
            repositorioAtivos.adicionarAtivo("HYPE3", 42.60);
            repositorioAtivos.adicionarAtivo("GGBR4", 24.10);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Obtendo ativos disponíveis para compra
        List<Ativo> ativosDisponiveis = repositorioAtivos.getAtivos();
        for (int i = 0; i < ativosDisponiveis.size(); i++) {
            Ativo ativo = ativosDisponiveis.get(i);
            Button btnComprar = new Button("Comprar");
            int index = i;
            btnComprar.setOnAction(e -> comprarAtivo(ativo, index));
            grid.add(btnComprar, 0, i + 2);

            TextField txtQuantidade = new TextField("0");
            txtQuantidade.setMaxWidth(50);
            grid.add(txtQuantidade, 1, i + 2);

            grid.add(new Label("R$ " + String.format("%.2f", ativo.getPreco())), 2, i + 2);
            grid.add(new Label("0.00%"), 3, i + 2); // Valor fictício até implementação real
            grid.add(new Label(ativo.getNome()), 4, i + 2);
        }

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> primaryStage.close());
        grid.add(btnVoltar, 0, ativosDisponiveis.size() + 3);

        Scene scene = new Scene(grid, 600, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void comprarAtivo(Ativo ativo, int index) {
        try {
            carteira.adicionarAtivoNaCarteira(ativo.getNome(), 1, (int) (Math.random() % 10)); // Compra 1 unidade por padrão
            mostrarAlerta("Sucesso", "Ativo comprado com sucesso!");
        } catch (Exception e) {
            mostrarAlerta("Erro", "Falha ao comprar ativo.");
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
