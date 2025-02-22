package org.controladorinvestimentos.controlador_investimentos.Banco;

import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.beans.Ativo;


import java.util.ArrayList;
public class repositorioAtivos implements IrepositorioAtivos {

        private static final ArrayList<Ativo> ATIVOS = new ArrayList<>();

        private static repositorioAtivos instance;

        private repositorioAtivos() {}

    public static synchronized repositorioAtivos getInstance(){

        if(instance == null){
            synchronized (repositorioUsers.class){
                if(instance == null){
                    instance = new repositorioAtivos();
                }
            }
        }
        return instance;
    }

        public void adicionarAtivo(String nome,double preco){
            Ativo newAtv = new Ativo(nome,preco);
            ATIVOS.add(newAtv);
        }

        public void removerAtivo(Ativo ativo){
            ATIVOS.remove(ativo);
        }

        public Ativo buscarAtivo(String nome) throws Exist {
            for(Ativo u : ATIVOS){
                if(u.getNome().equals(nome)){
                    return u;
                }
            }
            throw new Exist("ativo nao encontrado");
        }

    @Override
    public void adicionarAtivo(String nome) {
        Ativo newAtv = new Ativo(nome);
        ATIVOS.add(newAtv);
    }


    public ArrayList<Ativo> getAtivos() {
            return ATIVOS;
        }
    }