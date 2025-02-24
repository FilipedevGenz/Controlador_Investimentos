package org.controladorinvestimentos.controlador_investimentos.Banco;

import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.beans.adm;

import java.util.ArrayList;

public class repositorioAdm implements IrepositorioAdm {

    private static final ArrayList<adm> ADMINS = new ArrayList<>();
    private static repositorioAdm instance;

    private repositorioAdm() {}

    public static synchronized repositorioAdm getInstance() {
        if (instance == null) {
            synchronized (repositorioAdm.class) {
                if (instance == null) {
                    instance = new repositorioAdm();
                }
            }
        }
        return instance;
    }

    @Override
    public void adicionarAdm(adm admin) throws Exist {
        // Verifica se já existe um admin com o mesmo CPF
        try {
            buscarAdm(admin.getCpf());
            throw new Exist("Admin já existe no sistema.");
        } catch (Exist e) {
            // Se não encontrado, prossegue com a adição
            ADMINS.add(admin);
        }
    }

    @Override
    public adm buscarAdm(int cpf) throws Exist {
        for (adm admin : ADMINS) {
            if (admin.getCpf() == cpf) {
                return admin;
            }
        }
        throw new Exist("Admin não encontrado");
    }

    @Override
    public void removerAdm(adm admin) throws Exist {
        adm adminEncontrado = null;
        for (adm a : ADMINS) {
            if (a.getCpf() == admin.getCpf()) {
                adminEncontrado = a;
                break;
            }
        }
        if (adminEncontrado != null) {
            ADMINS.remove(adminEncontrado);
        } else {
            throw new Exist("Admin não encontrado");
        }
    }
}