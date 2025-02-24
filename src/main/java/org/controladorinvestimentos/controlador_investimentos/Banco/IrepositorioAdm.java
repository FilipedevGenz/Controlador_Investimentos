package org.controladorinvestimentos.controlador_investimentos.Banco;

import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.beans.adm;

public interface IrepositorioAdm {
    void adicionarAdm(adm admin) throws Exist;
    adm buscarAdm(int cpf) throws Exist;
    void removerAdm(adm admin) throws Exist;
}