package org.controladorinvestimentos.controlador_investimentos.beans;

import java.util.ArrayList;

public class Conta extends Usuario{

    private double saldo;
    private ArrayList<Carteira> carteiras;
    private ControladorCarteira _controladorCarteira;

    Conta(){}

    Conta(int cpf, String nome, String senha, String email){
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

    public  ArrayList<Carteira> getCarteiras(){return carteiras;}

    public double getSaldo() {return saldo;}
}