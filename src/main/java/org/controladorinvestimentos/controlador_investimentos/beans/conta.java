package org.controladorinvestimentos.controlador_investimentos.beans;

import org.controladorinvestimentos.controlador_investimentos.Banco.repositorioCarteira;

import java.util.ArrayList;

public class conta extends usuario {

    private double saldo;
    public repositorioCarteira repositorioCarteira;
    private controladorCarteira controladorCarteira;

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

    public double getSaldo() {return saldo;}
}