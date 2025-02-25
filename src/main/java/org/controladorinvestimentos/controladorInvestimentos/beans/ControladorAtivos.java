package org.controladorinvestimentos.controladorInvestimentos.beans;
import org.controladorinvestimentos.controladorInvestimentos.Banco.IrepositorioAtivos;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioAtivos;
import org.controladorinvestimentos.controladorInvestimentos.Exceptions.Exist;

import java.io.IOException;

public class ControladorAtivos {

    private static IrepositorioAtivos repositorioAtivos;

    public static void criarAtivo(String nome) throws IOException {
        repositorioAtivos = RepositorioAtivos.getInstance();
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

    public static void removerAtivo(String nome){
        try {
            RepositorioAtivos.getInstance();
            Ativo _ativoEncontrado = repositorioAtivos.buscarAtivo(nome);
            if (_ativoEncontrado != null) {
                repositorioAtivos.removerAtivo(_ativoEncontrado);
            }
        }catch (Exception e) {
            throw new Exist("Esse ativo não existe no sistema.");
        }

    }

    }

