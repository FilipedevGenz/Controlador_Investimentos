package org.controladorinvestimentos.controlador_investimentos.Banco;

import org.controladorinvestimentos.controlador_investimentos.beans.ativo;

import java.util.ArrayList;

public class repositorioAtivosCarteira {

    private final ArrayList<ativo> listaAtivos;

    public repositorioAtivosCarteira() {
        listaAtivos = new ArrayList<>();
    }

    public void addToAtivosCarteira(ativo ativo, double quantidade){
        listaAtivos.add(ativo);
    }

    public void removeFromAtivosCarteira(ativo ativo, double quantidade) throws Exception{
        try {
            listaAtivos.remove(ativo);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
    public ativo buscarAtivo(ativo ativo) throws RuntimeException{
        for (org.controladorinvestimentos.controlador_investimentos.beans.ativo a : listaAtivos) {
            if (a.getNome().equals(ativo.getNome())) {
                return a;
            }
        }
        return null;
    }

    public ArrayList<ativo> getListaAtivos() {
        return listaAtivos;
    }
}
