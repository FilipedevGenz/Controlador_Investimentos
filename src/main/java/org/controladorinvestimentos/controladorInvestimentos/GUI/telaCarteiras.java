/*
package org.controladorinvestimentos.controladorInvestimentos.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioAtivos;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioCarteira;
import org.controladorinvestimentos.controladorInvestimentos.Banco.repositorioUsers;
import org.controladorinvestimentos.controladorInvestimentos.beans.Carteira;

public class telaCarteiras extends Application {

    private conta user;

    telaCarteiras(){
        this(null);
    }

    // Construtor da telaCarteiras
    public telaCarteiras(conta conta) {
        this.user = conta;
        // Certificando que o repositorioCarteira foi inicializado na conta
        if (user.repositorioCarteira == null) {
            user.repositorioCarteira = new RepositorioCarteira(user); // Inicializa o repositorio se for nulo
        }
    }

    @Override
    public void start(Stage primaryStage) {

        RepositorioAtivos repositorioatv = RepositorioAtivos.getInstance();  // Esta linha inicializa o repositorio de ativos
        repositorioUsers repositorio = repositorioUsers.getInstance();  // Inicializa o repositório de usuários

// Verifica se a variável carteira está nula e a inicializa antes de usá-la

        primaryStage.setTitle("Carteiras");

        // Criando o título com fundo verde arredondado e menor
        StackPane titlePane = new StackPane();
        Rectangle titleBackground = new Rectangle(150, 30); // Reduzindo o tamanho do fundo
        titleBackground.setArcWidth(15);
        titleBackground.setArcHeight(15);
        titleBackground.setFill(Color.GREEN);

        Text titleText = new Text("Carteiras");
        titleText.setFill(Color.WHITE);
        titleText.setFont(new Font(16)); // Ajustando o tamanho do texto

        titlePane.getChildren().addAll(titleBackground, titleText);
        titlePane.setAlignment(Pos.CENTER);

        // Container das carteiras
        VBox walletContainer = new VBox(10);
        walletContainer.setStyle("-fx-padding: 10; -fx-background-color: #d3d3d3;");
        walletContainer.setAlignment(Pos.TOP_CENTER);

        // Acessando diretamente o repositorioCarteira da conta do usuário
        if (user.repositorioCarteira != null) {
            // Criando botões de carteiras arredondados
            user.repositorioCarteira.getCarteiras().forEach(carteira -> {

                int i = carteira.ID; // Pegando o ID da carteira
                Button walletButton = new Button("Carteira " + i);
                walletButton.setMinWidth(250);
                walletButton.setMinHeight(50);
                walletButton.setStyle("-fx-font-size: 14px; -fx-background-color: white; -fx-border-radius: 25; -fx-background-radius: 25; -fx-border-color: #A9A9A9;");

                walletButton.setOnAction(e -> {
                    // Procurando a carteira com o ID igual a 'i'
                    Carteira selectedCarteira = user.repositorioCarteira.getCarteiras().stream()
                            .filter(c -> c.ID == i) // Filtrando pelo ID da carteira
                            .findFirst() // Encontrando a primeira carteira que atende à condição
                            .orElse(null); // Retorna null se não encontrar

                    if (selectedCarteira != null) {
                        // Passando a instância da carteira selecionada e o usuário para TelaAtivosCarteiras
                        Stage ativosStage = new Stage();
                        TelaAtivosCarteiras ativosCarteiraScreen = new TelaAtivosCarteiras(selectedCarteira, user);
                        ativosCarteiraScreen.start(ativosStage);
                        primaryStage.close(); // Fecha a tela atual
                    } else {
                        System.out.println("Carteira com ID " + i + " não encontrada");
                    }
                });


                walletContainer.getChildren().add(walletButton);
            });
        }

        // Área rolável para exibir as carteiras
        ScrollPane scrollPane = new ScrollPane(walletContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #d3d3d3; -fx-border-color: transparent;");

        // Botão Voltar arredondado
        Button backButton = new Button("Voltar");
        backButton.setMinWidth(100);
        backButton.setMinHeight(40);
        backButton.setStyle("-fx-font-size: 14px; -fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: #A9A9A9;");
        backButton.setOnAction(e -> {
            conta conta1 = new conta(user.getCpf(), user.getNome(), user.getSenha(), user.getEmail());  // Usando a instância 'user'
            menuUser next = new menuUser(conta1);  // Passando diretamente uma instância de 'conta'
            Stage menu = new Stage();
            next.start(menu);
        });

        StackPane bottomPane = new StackPane(backButton);
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setStyle("-fx-padding: 10;");

        // Layout principal
        BorderPane root = new BorderPane();
        root.setTop(titlePane);
        root.setCenter(scrollPane);
        root.setBottom(bottomPane);
        BorderPane.setAlignment(titlePane, Pos.CENTER);

        Scene scene = new Scene(root, 300, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
*/