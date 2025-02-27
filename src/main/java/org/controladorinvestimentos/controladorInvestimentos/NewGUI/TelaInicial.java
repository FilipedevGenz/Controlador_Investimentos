package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioAtivos;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioCarteira;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioUsuario;
import org.controladorinvestimentos.controladorInvestimentos.beans.APIfuncionalidades.APIrequest;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Carteira;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Usuario;
import org.controladorinvestimentos.controladorInvestimentos.Exceptions.Exist;

import java.io.IOException;

public class TelaInicial extends Application {

    private RepositorioAtivos repositorioAtivos;
    private RepositorioCarteira repositorioCarteira;
    private Usuario usuarioTeste;

    @Override
    public void start(Stage primaryStage) {
        // Instancia os repositórios
        RepositorioUsuario.getInstance();
        repositorioAtivos = RepositorioAtivos.getInstance();
        repositorioCarteira = RepositorioCarteira.getInstance();
        RepositorioUsuario repositorioUsuario = RepositorioUsuario.getInstance();

        // Criar e armazenar ativos no repositório
        try {
            repositorioAtivos.adicionarAtivo("VALE3", APIrequest.buscarPrecoAtivoEmTempoReal("VALE3"));
            repositorioAtivos.adicionarAtivo("ITUB4", APIrequest.buscarPrecoAtivoEmTempoReal("ITUB4"));
            repositorioAtivos.adicionarAtivo("BBDC4", APIrequest.buscarPrecoAtivoEmTempoReal("BBDC4"));
            repositorioAtivos.adicionarAtivo("ABEV3", APIrequest.buscarPrecoAtivoEmTempoReal("ABEV3"));
            repositorioAtivos.adicionarAtivo("BBAS3", APIrequest.buscarPrecoAtivoEmTempoReal("BBAS3"));
            repositorioAtivos.adicionarAtivo("WEGE3", APIrequest.buscarPrecoAtivoEmTempoReal("WEGE3"));
            repositorioAtivos.adicionarAtivo("MGLU3", APIrequest.buscarPrecoAtivoEmTempoReal("MGLU3"));
            repositorioAtivos.adicionarAtivo("PETR3", APIrequest.buscarPrecoAtivoEmTempoReal("PETR3"));
            repositorioAtivos.adicionarAtivo("GGBR4", APIrequest.buscarPrecoAtivoEmTempoReal("GGBR4"));
            repositorioAtivos.adicionarAtivo("LREN3", APIrequest.buscarPrecoAtivoEmTempoReal("LREN3"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Criar um usuário de teste
        usuarioTeste = new Usuario(123456789, "Usuário Teste", "teste@email.com", "senha123");

        // Criar uma carteira para o usuário teste
        Carteira carteiraTeste = new Carteira("123", "Carteira Padrão", 30);
        try {
            carteiraTeste.adicionarAtivoNaCarteira("VALE3", 10, 30);
            carteiraTeste.adicionarAtivoNaCarteira("ITUB4", 15, 30);
            carteiraTeste.adicionarAtivoNaCarteira("BBDC4", 20, 30);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Associar a carteira ao repositório de carteiras
        repositorioCarteira.adicionarCarteira(carteiraTeste);

        primaryStage.setTitle("Bem-vindo ao Controlador de Investimentos");

        // Título
        Text titulo = new Text("Bem-vindo!");
        titulo.setFont(new Font("Arial", 24));

        // Botão de Login
        Button btnLogin = new Button("Login");
        btnLogin.setFont(new Font("Arial", 16));
        btnLogin.setOnAction(e -> {
            Login telaLogin = new Login();
            try {
                telaLogin.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Botão ADM
        Button btnRegistrar = new Button("ADM");
        btnRegistrar.setFont(new Font("Arial", 16));
        btnRegistrar.setOnAction(e -> {
            AdicionarAtivo AddAtv = new AdicionarAtivo();
            try {
                AddAtv.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Layout
        VBox layout = new VBox(20, titulo, btnLogin, btnRegistrar);
        layout.setAlignment(Pos.CENTER);

        // Configuração da cena
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
