package org.controladorinvestimentos.controladorInvestimentos.Banco;

import org.controladorinvestimentos.controladorInvestimentos.Banco.Interfaces.IrepositorioAdm;
import org.controladorinvestimentos.controladorInvestimentos.Exceptions.Exist;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Adm;

import java.util.ArrayList;

public class RepositorioAdm implements IrepositorioAdm {

    private static final ArrayList<Adm> ADMINS = new ArrayList<>();
    private static RepositorioAdm instance;

    private RepositorioAdm() {}

    public static synchronized RepositorioAdm getInstance() {
        if (instance == null) {
            synchronized (RepositorioAdm.class) {
                if (instance == null) {
                    instance = new RepositorioAdm();
                }
            }
        }
        return instance;
    }


    public void cadastrarAdm(Adm adm) throws Exist {
        // Verifica se já existe um admin com o mesmo CPF
        try {
            buscarAdm(adm.getCpf());
            throw new Exist("Admin já existe no sistema.");
        } catch (Exist e) {
            // Se não encontrado, prossegue com a adição
            ADMINS.add(adm);
        }
    }

    @Override
    public Adm buscarAdm(int cpf) throws Exist {
        for (Adm admin : ADMINS) {
            if (admin.getCpf() == cpf) {
                return admin;
            }
        }
        throw new Exist("Admin não encontrado");
    }

    @Override
    public void removerAdm(Adm admin) throws Exist {
        Adm adminEncontrado = null;
        for (Adm a : ADMINS) {
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