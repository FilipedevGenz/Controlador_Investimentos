package org.controladorinvestimentos.controlador_investimentos.beans;


import lombok.Getter;
import lombok.Setter;
import org.controladorinvestimentos.controlador_investimentos.Banco.IrepositorioCarteira;
@Getter
@Setter
public class usuario {

    private int cpf;
    private String nome;
    private String email;
    private String senha;
    public IrepositorioCarteira repositorioCarteira;


    public usuario(int cpf, String nome, String senha, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
    }

    public usuario(){}

}
