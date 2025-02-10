package org.controladorinvestimentos.controlador_investimentos.beans;

import org.controladorinvestimentos.controlador_investimentos.Banco.iRepositorioAtivos;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;


public class Adm extends Usuario{

    private iRepositorioAtivos repositorioAtivos;

    private void CriarAtivo(int idAtv, double ValorAtv, String nome){
        try {
            Ativo _AtivoEncontrado = repositorioAtivos.buscarAtivo(nome);
            if (_AtivoEncontrado != null){
                throw new Exist("esse ativo já existe no sistema.");

            }
        } catch (Exception e) {
            repositorioAtivos.adicionarAtivo(nome,ValorAtv);
        }
    }

    private void RemoverAtivo(int idAtv, double ValorAtv, String nome){
        try {
           Ativo _AtivoEncontrado = repositorioAtivos.buscarAtivo(nome);
            if (_AtivoEncontrado != null) {
                repositorioAtivos.removerAtivo(_AtivoEncontrado);
            }
        }catch (Exception e) {
            throw new Exist("Esse ativo não existe no sistema.");
        }

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
