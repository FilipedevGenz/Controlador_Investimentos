package org.controladorinvestimentos.controlador_investimentos.Banco;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.beans.Ativo;
import java.io.IOException;


import java.util.ArrayList;
public class RepositorioAtivos implements iRepositorioAtivos{

        private static final ArrayList<Ativo> Ativos = new ArrayList<>();

        private static RepositorioAtivos instance;

        private RepositorioAtivos() {}

    public static synchronized RepositorioAtivos getInstance(){

        if(instance == null){
            synchronized (RepositorioUsers.class){
                if(instance == null){
                    instance = new RepositorioAtivos();
                }
            }
        }
        return instance;
    }

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