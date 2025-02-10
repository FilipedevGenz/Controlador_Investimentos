package org.controladorinvestimentos.controlador_investimentos.Banco;

import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.beans.Ativo;

import java.util.ArrayList;

public class RepositorioAtivos implements iRepositorioAtivos{

         static final ArrayList<Ativo> Ativos = new ArrayList<>();

        public void adicionarAtivo(String nome,double preco){
            Ativo _NewAtv = new Ativo(nome,preco);
            Ativos.add(_NewAtv);
        }

        public void removerAtivo(Ativo ativo){
            Ativos.remove(ativo);
        }

        public Ativo buscarAtivo(String nome) throws Exist {
            for(Ativo u : Ativos){
                if(u.getNome().equals(nome)){
                    return u;
                }
            }
            throw new Exist("ativo nao encontrado");
        }

        public void AlterarPreco(double preco,Ativo ativo) {ativo.setPreco(preco);}
    }