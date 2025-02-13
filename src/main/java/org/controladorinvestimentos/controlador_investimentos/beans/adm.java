package org.controladorinvestimentos.controlador_investimentos.beans;

import org.controladorinvestimentos.controlador_investimentos.Banco.IrepositorioAtivos;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;

import java.io.IOException;


public class adm extends usuario {
    public static boolean isADM = true;
    private static IrepositorioAtivos repositorioAtivos;
    public controladorAtivos controladorAtivos;


    private void CriarAtivo(int idAtv, double ValorAtv, String nome){
            repositorioAtivos = org.controladorinvestimentos.controlador_investimentos.Banco.repositorioAtivos.getInstance();
        try {
            controladorAtivos.CriarAtivo(nome);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void RemoverAtivo(String nome){
        controladorAtivos.RemoverAtivo(nome);
    }

    private void AlterarPreco(String nome,double preco){
        try {
            ativo _ativoEncontrado = repositorioAtivos.buscarAtivo(nome);

            if (_ativoEncontrado != null) {
                repositorioAtivos.AlterarPreco(preco, _ativoEncontrado);
            }
        }catch (Exception e) {
            throw new Exist("Ativo não existe no sistema.");
        }

    }

}
