package org.controladorinvestimentos.controlador_investimentos.GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaCheckAdm {
    private static final String SENHA_ADM = "senhaADM123";

    public void start(Stage primaryStage) {
        Label label = new Label("Digite a senha do Administrador:");
        PasswordField senhaField = new PasswordField();
        Button btnEntrar = new Button("Entrar");
        Label mensagemErro = new Label();

        btnEntrar.setOnAction(e -> {
            if (senhaField.getText().equals(SENHA_ADM)) {
                TelaAdm telaAdm = new TelaAdm();
                telaAdm.start(primaryStage);
            } else {
                mensagemErro.setText("Senha incorreta! Tente novamente.");
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, senhaField, btnEntrar, mensagemErro);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Autenticação ADM");
        primaryStage.show();
    }
}
