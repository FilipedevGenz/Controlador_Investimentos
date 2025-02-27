package org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras;

import org.controladorinvestimentos.controladorInvestimentos.Banco.Interfaces.IrepositorioAtivos;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioAtivos;
import org.controladorinvestimentos.controladorInvestimentos.beans.ControladorAtivos;
import lombok.Getter;

import java.io.IOException;

public class Adm {
    public static IrepositorioAtivos repositorioAtivos = RepositorioAtivos.getInstance();
    @Getter
    private int cpf;
    private String senha;

    public Adm() {}

    public Adm(int cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
        repositorioAtivos = RepositorioAtivos.getInstance(); // Garante que não seja null
    }

    private void criarAtivo(String nome) {
        repositorioAtivos = RepositorioAtivos.getInstance(); // Certifica-se de que está inicializado
        try {
            ControladorAtivos.criarAtivo(nome);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void removerAtivo(String nome) {
        repositorioAtivos = RepositorioAtivos.getInstance();
        ControladorAtivos.removerAtivo(nome);
    }
}
