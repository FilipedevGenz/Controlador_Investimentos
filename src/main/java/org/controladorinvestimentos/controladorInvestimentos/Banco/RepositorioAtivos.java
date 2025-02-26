package org.controladorinvestimentos.controladorInvestimentos.Banco;

import org.controladorinvestimentos.controladorInvestimentos.Exceptions.Exist;
import org.controladorinvestimentos.controladorInvestimentos.beans.APIrequest;
import org.controladorinvestimentos.controladorInvestimentos.beans.Ativo;


import java.io.IOException;
import java.util.ArrayList;

public class RepositorioAtivos implements IrepositorioAtivos {

        // RepositórioAtivos é um singleton, pois deve conter todos os ativos disponíveis para compra e venda de ativos
        // instance vai armazenar um arrayList ATIVOS, que contem esse ativos disponiveis
        private static final ArrayList<Ativo> ATIVOS = new ArrayList<>();

        private static RepositorioAtivos instance;

        private RepositorioAtivos() {}

    public static synchronized RepositorioAtivos getInstance(){

        if(instance == null){
            synchronized (RepositorioUsuario.class){
                if(instance == null){
                    instance = new RepositorioAtivos();
                }
            }
        }
        return instance;
    }

        //CRUD de ativos

        public void adicionarAtivo(String code, double preco) throws IOException {
            String name = APIrequest.buscarNomeAtivo(code);
            Ativo newAtv = new Ativo(name,preco);

            ATIVOS.add(newAtv);
        }

        public void removerAtivo(Ativo ativo){
            ATIVOS.remove(ativo);
        }

        //Busca dentro do arrayList o ativo desejado. Essa operação garante que busquemos o ativo dentro do repositorio
        public Ativo buscarAtivo(String nome) throws Exist {
            for(Ativo u : ATIVOS){
                if(u.getNome().equals(nome)){
                    return u;
                }
            }
            throw new Exist("ativo nao encontrado");
        }

        // Não entendi esse override
    @Override
    public void adicionarAtivo(String nome) throws IOException {

    }


    public ArrayList<Ativo> getAtivos() {

            return ATIVOS;
        }
    }