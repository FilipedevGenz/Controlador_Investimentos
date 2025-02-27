package org.controladorinvestimentos.controladorInvestimentos.Banco.Interfaces;

import org.controladorinvestimentos.controladorInvestimentos.Exceptions.Exist;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Adm;

public interface IrepositorioAdm {
    Adm buscarAdm(int cpf) throws Exist;
    void removerAdm(Adm admin) throws Exist;
}