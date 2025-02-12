package org.controladorinvestimentos.controlador_investimentos.beans;
import org.controladorinvestimentos.controlador_investimentos.Banco.RepositorioAtivos;
import org.controladorinvestimentos.controlador_investimentos.Banco.iRepositorioAtivos;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.controladorinvestimentos.controlador_investimentos.beans.Ativo;
import java.io.IOException;
import java.util.ArrayList;

public class controladorAtivos {

    private static iRepositorioAtivos repositorioAtivos;

    public static void CriarAtivo(String nome) throws IOException {
        repositorioAtivos = RepositorioAtivos.getInstance();
        try {
            Ativo _AtivoEncontrado = repositorioAtivos.buscarAtivo(nome);
            if (_AtivoEncontrado != null){
                throw new Exist("esse ativo já existe no sistema.");

            }
        } catch (Exception e) {
            double ValorAtualAtivo = APIrequest.buscarPrecoAtivoEmTempoReal(nome);
            repositorioAtivos.adicionarAtivo(nome,ValorAtualAtivo);
        }
    }

    public static void RemoverAtivo(String nome){
        try {
            Ativo _AtivoEncontrado = repositorioAtivos.buscarAtivo(nome);
            if (_AtivoEncontrado != null) {
                repositorioAtivos.removerAtivo(_AtivoEncontrado);
            }
        }catch (Exception e) {
            throw new Exist("Esse ativo não existe no sistema.");
        }

    }

    }

