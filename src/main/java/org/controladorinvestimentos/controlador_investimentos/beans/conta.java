package org.controladorinvestimentos.controlador_investimentos.beans;

import org.controladorinvestimentos.controlador_investimentos.Banco.repositorioCarteira;
import org.controladorinvestimentos.controlador_investimentos.Banco.repositorioUsers;

import java.util.ArrayList;

import static org.controladorinvestimentos.controlador_investimentos.beans.carteira.Ncarteiras;

public class conta extends usuario {

    private double saldo = 20000.0;
    public repositorioCarteira repositorioCarteira;
    private controladorCarteira controladorCarteira;

    conta(){}

    public conta(int cpf, String nome, String senha, String email){
        setCpf(cpf);
        setNome(nome);
        setSenha(senha);
        setEmail(email);
        saldo = 0.0;
        this.repositorioCarteira = new repositorioCarteira(this);
        this.controladorCarteira = new controladorCarteira();
        carteira carteira = new carteira(Ncarteiras);
        repositorioCarteira.adicionarCarteira(carteira);
        repositorioUsers.getInstance().adicionarUsuario(this);
    }

    public void debitar(double valor){
        saldo -= valor;
    }

    public void creditar(double valor){
        saldo += valor;
    }

    public double getSaldo() {return saldo;}
}