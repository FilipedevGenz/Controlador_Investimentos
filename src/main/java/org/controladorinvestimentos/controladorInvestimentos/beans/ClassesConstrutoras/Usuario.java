package org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras;


import lombok.Getter;
import lombok.Setter;
import org.controladorinvestimentos.controladorInvestimentos.Banco.Interfaces.IrepositorioCarteira;
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
