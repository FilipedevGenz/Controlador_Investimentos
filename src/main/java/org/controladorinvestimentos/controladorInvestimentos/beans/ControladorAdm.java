package org.controladorinvestimentos.controladorInvestimentos.beans;

import org.controladorinvestimentos.controladorInvestimentos.Banco.IrepositorioAdm;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioAdm;
import org.controladorinvestimentos.controladorInvestimentos.Exceptions.Exist;

public class ControladorAdm {

    private static IrepositorioAdm irepositorioAdm;

    public ControladorAdm() {
        RepositorioAdm.getInstance();
    }

    public void cadastrarAdm(int cpf, String senha) {
        try {
            Adm admEncontrado = irepositorioAdm.buscarAdm(cpf);
            if (admEncontrado != null) {
                throw new Exist("Usuário já existe no sistema.");
            }
        } catch (Exception e) {
            Adm newAdm = new Adm(cpf, senha);
        }
    }
}