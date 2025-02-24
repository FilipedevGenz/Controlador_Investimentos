package org.controladorinvestimentos.controlador_investimentos.beans;

import org.controladorinvestimentos.controlador_investimentos.Banco.IrepositorioAdm;
import org.controladorinvestimentos.controlador_investimentos.Banco.repositorioAdm;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;

public class controladorAdm {

    private static IrepositorioAdm irepositorioAdm;

    public controladorAdm() {
        repositorioAdm.getInstance();
    }

    public void CadastrarAdm(int cpf,String senha) {
        try {
            adm admEncontrado = irepositorioAdm.buscarAdm(cpf);
            if (admEncontrado != null) {
                throw new Exist("Usuário já existe no sistema.");
            }
        } catch (Exception e) {
            adm newAdm = new adm(cpf, senha);
        }
    }
}