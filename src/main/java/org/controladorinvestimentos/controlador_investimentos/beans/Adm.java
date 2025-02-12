package org.controladorinvestimentos.controlador_investimentos.beans;

import com.dlsc.formsfx.model.structure.StringField;
import org.controladorinvestimentos.controlador_investimentos.Banco.RepositorioAtivos;
import org.controladorinvestimentos.controlador_investimentos.Banco.iRepositorioAtivos;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;

import java.io.IOException;


public class Adm extends Usuario{

    private static iRepositorioAtivos repositorioAtivos;
    public controladorAtivos controladorAtivos;


    private void CriarAtivo(int idAtv, double ValorAtv, String nome){
            repositorioAtivos = RepositorioAtivos.getInstance();
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
            Ativo _AtivoEncontrado = repositorioAtivos.buscarAtivo(nome);

            if (_AtivoEncontrado != null) {
                repositorioAtivos.AlterarPreco(preco,_AtivoEncontrado);
            }
        }catch (Exception e) {
            throw new Exist("Ativo não existe no sistema.");
        }

    }

}
