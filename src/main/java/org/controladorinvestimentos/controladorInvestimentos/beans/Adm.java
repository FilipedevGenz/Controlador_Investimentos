package org.controladorinvestimentos.controladorInvestimentos.beans;

import lombok.Getter;
import lombok.Setter;
import org.controladorinvestimentos.controladorInvestimentos.Banco.IrepositorioAtivos;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioAtivos;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioAdm;
import java.io.IOException;

@Getter
@Setter
public class Adm {
    public static IrepositorioAtivos repositorioAtivos;
    private int cpf;
    private String senha;

    public Adm() {}

    public Adm(int cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
        RepositorioAdm.getInstance().cadastrarAdm(this);
    }

    private void criarAtivo(String nome){
            repositorioAtivos = RepositorioAtivos.getInstance();
        try {
            ControladorAtivos.criarAtivo(nome);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void removerAtivo(String nome){
        repositorioAtivos = RepositorioAtivos.getInstance();
        ControladorAtivos.removerAtivo(nome);
    }

}
