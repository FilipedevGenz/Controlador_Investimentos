package org.controladorinvestimentos.controladorInvestimentos.NewGUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TelaInicial extends Application {

    @Override
    public void start(Stage primaryStage) {
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
