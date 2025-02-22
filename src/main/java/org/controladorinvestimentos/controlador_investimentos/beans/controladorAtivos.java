package org.controladorinvestimentos.controlador_investimentos.beans;
import org.controladorinvestimentos.controlador_investimentos.Banco.IrepositorioAtivos;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;

import java.io.IOException;

public class controladorAtivos {

    private static IrepositorioAtivos repositorioAtivos;

    public static void CriarAtivo(String nome) throws IOException {
        repositorioAtivos = org.controladorinvestimentos.controlador_investimentos.Banco.repositorioAtivos.getInstance();
        try {
            Ativo ativoEncontrado = repositorioAtivos.buscarAtivo(nome);
            if (ativoEncontrado != null){
                throw new Exist("esse ativo já existe no sistema.");

            }
        } catch (Exception e) {
            double ValorAtualAtivo = APIrequest.buscarPrecoAtivoEmTempoReal(nome);
            repositorioAtivos.adicionarAtivo(nome);
        }
    }

    public static void RemoverAtivo(String nome){
        try {
            Ativo _ativoEncontrado = repositorioAtivos.buscarAtivo(nome);
            if (_ativoEncontrado != null) {
                repositorioAtivos.removerAtivo(_ativoEncontrado);
            }
        }catch (Exception e) {
            throw new Exist("Esse ativo não existe no sistema.");
        }

    }

    }

