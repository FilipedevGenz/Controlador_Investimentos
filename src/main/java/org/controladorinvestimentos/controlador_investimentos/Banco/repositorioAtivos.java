package org.controladorinvestimentos.controlador_investimentos.Banco;

import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.beans.ativo;


import java.util.ArrayList;
public class repositorioAtivos implements IrepositorioAtivos {

        private static final ArrayList<ativo> ATIVOS = new ArrayList<>();

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
            ativo _NewAtv = new ativo(nome,preco);
            ATIVOS.add(_NewAtv);
        }

        public void removerAtivo(ativo ativo){
            ATIVOS.remove(ativo);
        }

        public ativo buscarAtivo(String nome) throws Exist {
            for(ativo u : ATIVOS){
                if(u.getNome().equals(nome)){
                    return u;
                }
            }
            throw new Exist("ativo nao encontrado");
        }

        public void AlterarPreco(double preco, ativo ativo) {ativo.setPreco(preco);}

        public ArrayList<ativo> getAtivos() {
            return ATIVOS;
        }
    }