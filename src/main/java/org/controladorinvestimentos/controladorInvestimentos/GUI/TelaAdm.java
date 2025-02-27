/*
package org.controladorinvestimentos.controladorInvestimentos.GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioAtivos;

public class TelaAdm {
    private RepositorioAtivos repositorio;

    public TelaAdm() {
        this.repositorio = RepositorioAtivos.getInstance();
    }

    public void start(Stage primaryStage) {
        Label label = new Label("Adicionar Novo Ativo:");
        TextField ativoField = new TextField();
        ativoField.setPromptText("Nome do Ativo");
        TextField precoField = new TextField();
        precoField.setPromptText("Preço do Ativo");
        Button btnAdicionar = new Button("Adicionar");
        Label mensagem = new Label();

        btnAdicionar.setOnAction(e -> {
            String ativoNome = ativoField.getText();
            String precoTexto = precoField.getText();

            if (!ativoNome.isEmpty() && !precoTexto.isEmpty()) {
                try {
                    double preco = Double.parseDouble(precoTexto);
                    repositorio.adicionarAtivo(ativoNome, preco);
                    mensagem.setText("Ativo " + ativoNome + " adicionado com sucesso!");
                    ativoField.clear();
                    precoField.clear();
                } catch (NumberFormatException ex) {
                    mensagem.setText("Preço inválido! Digite um número válido.");
                }
            } else {
                mensagem.setText("O nomeCarteira do ativo e o preço não podem estar vazios.");
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, ativoField, precoField, btnAdicionar, mensagem);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Administrador - Adicionar Ativos");
        primaryStage.show();
    }
}

 */