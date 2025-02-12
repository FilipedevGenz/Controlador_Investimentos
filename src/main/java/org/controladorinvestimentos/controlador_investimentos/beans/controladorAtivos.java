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

    public double buscarPrecoAtivoEmTempoReal(String simbolo) throws IOException {
        String apiKey = "7kfUNQUQm5GxWV6GXAf3ig";
        String url = "https://brapi.dev/api/quote/" + simbolo + "?token=" + apiKey;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erro na requisição: " + response.code());
            }


            String jsonResponse = response.body().string();


            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            JsonObject results = jsonObject.getAsJsonArray("results").get(0).getAsJsonObject();
            double preco = results.get("regularMarketPrice").getAsDouble();

            return preco;
        }
    }

    public void BuscarPreco(String simbolo, Ativo ativo) {
        try {
            double preco = buscarPrecoAtivoEmTempoReal(simbolo);
            ativo.setPreco(preco);
            System.out.println("Preço atualizado para o ativo " + ativo.getNome() + ": " + preco);
        } catch (IOException e) {
            System.err.println("Erro ao buscar o preço do ativo: " + e.getMessage());
        }
    }

    private static iRepositorioAtivos repositorioAtivos;

    private void CriarAtivo(int idAtv, double ValorAtv, String nome){
        repositorioAtivos = RepositorioAtivos.getInstance();
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
