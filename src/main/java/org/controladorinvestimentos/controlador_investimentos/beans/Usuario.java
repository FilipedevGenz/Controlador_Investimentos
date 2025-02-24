package org.controladorinvestimentos.controlador_investimentos.beans;


import lombok.Getter;
import lombok.Setter;
import org.controladorinvestimentos.controlador_investimentos.Banco.IrepositorioCarteira;
@Getter
@Setter
public class Usuario {

    private int cpf;
    private String nome;
    private String email;
    private String senha;
    public IrepositorioCarteira repositorioCarteira;


    public Usuario(int cpf, String nome, String senha, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
    }

    public Usuario(){}

}
