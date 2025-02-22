package org.controladorinvestimentos.controlador_investimentos.beans;

import org.controladorinvestimentos.controlador_investimentos.Banco.IrepositorioAtivos;
import org.controladorinvestimentos.controlador_investimentos.Banco.repositorioUsers;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;

import java.io.IOException;


public class adm extends usuario {
    public boolean isADM;
    private static IrepositorioAtivos repositorioAtivos;
    public controladorAtivos controladorAtivos;

    public adm() {}

    public adm(usuario conta) {
        super(conta.getCpf(),conta.getNome(),conta.getSenha(),conta.getEmail());
        this.isADM = true;
        repositorioUsers.getInstance().adicionarADM(this);
    }

    private void CriarAtivo(int idAtv, double ValorAtv, String nome){
            repositorioAtivos = org.controladorinvestimentos.controlador_investimentos.Banco.repositorioAtivos.getInstance();
        try {
            controladorAtivos.CriarAtivo(nome);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void RemoverAtivo(String nome){
        repositorioAtivos = org.controladorinvestimentos.controlador_investimentos.Banco.repositorioAtivos.getInstance();
        controladorAtivos.RemoverAtivo(nome);
    }

}
