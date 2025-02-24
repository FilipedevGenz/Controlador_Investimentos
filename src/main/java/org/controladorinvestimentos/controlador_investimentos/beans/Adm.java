package org.controladorinvestimentos.controlador_investimentos.beans;

import lombok.Getter;
import lombok.Setter;
import org.controladorinvestimentos.controlador_investimentos.Banco.IrepositorioAtivos;
import org.controladorinvestimentos.controlador_investimentos.Banco.RepositorioAtivos;
import org.controladorinvestimentos.controlador_investimentos.Banco.RepositorioAdm;
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

    private void CriarAtivo(String nome){
            repositorioAtivos = RepositorioAtivos.getInstance();
        try {
            ControladorAtivos.CriarAtivo(nome);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void RemoverAtivo(String nome){
        repositorioAtivos = RepositorioAtivos.getInstance();
        ControladorAtivos.RemoverAtivo(nome);
    }

}
