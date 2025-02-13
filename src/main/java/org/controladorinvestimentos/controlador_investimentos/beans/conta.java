package org.controladorinvestimentos.controlador_investimentos.beans;

import java.util.ArrayList;

public class conta extends usuario {

    private double saldo;
    private ArrayList<carteira> carteiras;
    private controladorCarteira _controladorCarteira;

    conta(){}

    conta(int cpf, String nome, String senha, String email){
        setCpf(cpf);
        setNome(nome);
        setSenha(senha);
        setEmail(email);
        saldo = 0.0;
    }

    public void debitar(double valor){
        saldo -= valor;
    }

    public void creditar(double valor){
        saldo += valor;
    }

    public  ArrayList<carteira> getCarteiras(){return carteiras;}

    public double getSaldo() {return saldo;}
}