package org.controladorinvestimentos.controlador_investimentos.Banco;

import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.beans.Adm;

public interface IrepositorioAdm {
    Adm buscarAdm(int cpf) throws Exist;
    void removerAdm(Adm admin) throws Exist;
}