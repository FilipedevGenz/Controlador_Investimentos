package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioAtivos;
import org.controladorinvestimentos.controladorInvestimentos.beans.APIfuncionalidades.HistoricoDosAtivos;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Ativo;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Carteira;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TelaCompraAtivos extends Application {
    private Carteira carteira;
    private Usuario usuarioLogado;
    private Map<Integer, TextField> quantidadeInputs = new HashMap<>();

    public TelaCompraAtivos(Usuario usuario, Carteira carteira) {
        this.usuarioLogado = usuario;
        this.carteira = carteira;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Compra de Ativos");

        VBox mainLayout = new VBox(15);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.CENTER);

        Label lblTitulo = new Label("Compra de Ativos");
        lblTitulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Ativo"), 4, 1);
        grid.add(new Label("Variação"), 3, 1);
        grid.add(new Label("Preço Atual"), 2, 1);
        grid.add(new Label("Quantidade"), 1, 1);
        grid.add(new Label("Ação"), 0, 1);

        RepositorioAtivos repositorioAtivos = RepositorioAtivos.getInstance();
        List<Ativo> ativosDisponiveis = repositorioAtivos.getAtivos();

        if (ativosDisponiveis.isEmpty()) {
            Label lblAviso = new Label("Nenhum ativo disponível para compra.");
            lblAviso.setStyle("-fx-font-size: 16px; -fx-text-fill: red;");
            grid.add(lblAviso, 0, 2, 5, 1);
        } else {
            for (int i = 0; i < ativosDisponiveis.size(); i++) {
                final Ativo ativoFinal = ativosDisponiveis.get(i);
                final int indexFinal = i;

                Button btnComprar = new Button("Comprar");
                TextField txtQuantidade = new TextField("1");
                txtQuantidade.setMaxWidth(50);
                quantidadeInputs.put(indexFinal, txtQuantidade);

                btnComprar.setOnAction(e -> comprarAtivo(ativoFinal, indexFinal));

                grid.add(btnComprar, 0, i + 2);
                grid.add(txtQuantidade, 1, i + 2);
                grid.add(new Label("R$ " + String.format("%.2f", ativoFinal.getPreco())), 2, i + 2);
            }
        }

        // Botão de voltar para a tela de carteiras
        Button btnVoltarParaCarteiras = new Button("Voltar para Carteiras");
        btnVoltarParaCarteiras.setStyle("-fx-font-size: 14px; -fx-padding: 10px;");
        btnVoltarParaCarteiras.setOnAction(e -> voltarParaCarteiras(primaryStage));

        HBox buttonsLayout = new HBox(20, btnVoltarParaCarteiras);
        buttonsLayout.setAlignment(Pos.CENTER);

        mainLayout.getChildren().addAll(lblTitulo, grid, buttonsLayout);

        Scene scene = new Scene(mainLayout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void comprarAtivo(Ativo ativo, int index) {
        try {
            if (carteira == null) {
                mostrarAlerta("Erro", "Nenhuma carteira foi associada a este usuário.");
                return;
            }

            if (!quantidadeInputs.containsKey(index)) {
                mostrarAlerta("Erro", "Erro interno: Campo de quantidade não encontrado.");
                return;
            }

            String quantidadeTexto = quantidadeInputs.get(index).getText().trim();
            if (quantidadeTexto.isEmpty()) {
                mostrarAlerta("Erro", "Digite uma quantidade válida.");
                return;
            }

            int quantidade = Integer.parseInt(quantidadeTexto);
            if (quantidade <= 0) {
                mostrarAlerta("Erro", "A quantidade deve ser maior que zero.");
                return;
            }

            if (ativo.getPreco() <= 0) {
                mostrarAlerta("Erro", "Preço do ativo inválido. Tente novamente.");
                return;
            }

            carteira.adicionarAtivoNaCarteira(ativo.getNome(), quantidade, (int) ativo.getPreco());

            mostrarAlerta("Sucesso", "Ativo comprado com sucesso!");

        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "Digite um número válido para a quantidade.");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Erro", "Erro ao comprar ativo: " + e.getMessage());
        }

    }

    private void voltarParaCarteiras(Stage primaryStage) {
        try {
            new TelaCarteiras(usuarioLogado).start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
            mostrarAlerta("Erro", "Não foi possível voltar para a tela de carteiras.");
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
