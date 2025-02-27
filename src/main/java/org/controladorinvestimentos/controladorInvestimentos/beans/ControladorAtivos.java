package org.controladorinvestimentos.controladorInvestimentos.beans;
import org.controladorinvestimentos.controladorInvestimentos.Banco.IrepositorioAtivos;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioAtivos;
import org.controladorinvestimentos.controladorInvestimentos.Exceptions.Exist;

import java.io.IOException;

public class ControladorAtivos {

    private static IrepositorioAtivos repositorioAtivos;

    // ao criar o ativo aqui, ele é adicionado no repositório !!
    public static void criarAtivo(String nome) throws IOException {
        repositorioAtivos = RepositorioAtivos.getInstance();

        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do ativo não pode ser vazio.");
        }

        try {
            Ativo ativoEncontrado = repositorioAtivos.buscarAtivo(nome);
            if (ativoEncontrado != null) {
                throw new Exist("Esse ativo já existe no sistema.");
            }
        } catch (Exist e) {
            throw e;
        } catch (Exception e) {
            System.out.println("Buscando preço do ativo: " + nome);
            double valorAtualAtivo = APIrequest.buscarPrecoAtivoEmTempoReal(nome);
            System.out.println("Valor atual do ativo: " + valorAtualAtivo);
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

