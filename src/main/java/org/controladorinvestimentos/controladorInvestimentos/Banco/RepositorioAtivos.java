package org.controladorinvestimentos.controladorInvestimentos.Banco;

import org.controladorinvestimentos.controladorInvestimentos.Banco.Interfaces.IrepositorioAtivos;
import org.controladorinvestimentos.controladorInvestimentos.Exceptions.Exist;
import org.controladorinvestimentos.controladorInvestimentos.beans.APIfuncionalidades.APIrequest;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Ativo;
import lombok.Getter;
import lombok.Setter;


import java.io.IOException;
import java.util.ArrayList;

public class RepositorioAtivos implements IrepositorioAtivos {

        // RepositórioAtivos é um singleton, pois deve conter todos os ativos disponíveis para compra e venda de ativos
        // instance vai armazenar um arrayList ATIVOS, que contem esse ativos disponiveis
        @Getter @Setter
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

        @Override
        public void adicionarAtivo(String code, double preco) throws IOException {
            String formattedCode = code.trim().replaceAll("\\s+", "").toUpperCase();
            Ativo newAtv = new Ativo(formattedCode, preco);
            ATIVOS.add(newAtv);
        }

        public void removerAtivo(Ativo ativo){
            ATIVOS.remove(ativo);
        }


    //Busca dentro do arrayList o ativo desejado. Essa operação garante que busquemos o ativo dentro do repositorio
        public Ativo buscarAtivo(String nome) throws Exist {

            String formattedName = nome.trim().replaceAll("\\s+", "").toUpperCase();
            for(Ativo u : ATIVOS){
                if(u.getNome().equals(formattedName)){
                    return u;
                }
            }
            throw new Exist("ativo nao encontrado");
        }


    public ArrayList<Ativo> getAtivos() {

            return ATIVOS;
        }
    }