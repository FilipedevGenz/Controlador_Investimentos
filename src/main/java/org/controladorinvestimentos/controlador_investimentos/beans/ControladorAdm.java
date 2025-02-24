package org.controladorinvestimentos.controlador_investimentos.beans;

import org.controladorinvestimentos.controlador_investimentos.Banco.IrepositorioAdm;
import org.controladorinvestimentos.controlador_investimentos.Banco.RepositorioAdm;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;

public class ControladorAdm {

    private static IrepositorioAdm irepositorioAdm;

    public ControladorAdm() {
        RepositorioAdm.getInstance();
    }

    public void CadastrarAdm(int cpf,String senha) {
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