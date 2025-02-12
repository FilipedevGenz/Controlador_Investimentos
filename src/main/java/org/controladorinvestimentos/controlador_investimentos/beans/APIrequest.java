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


public class APIrequest {

    public static double buscarPrecoAtivoEmTempoReal(String simbolo) throws IOException {
        String apiKey = "7kfUNQUQm5GxWV6GXAf3ig";
        String url = "https://brapi.dev/api/quote/" + simbolo + "?token=" + apiKey;
        //Simbolo = nome/codigo do ativo em questao.
        //Token = codigo de acesso retirado pela criacao de conta no side da Brapi api usada no codigo


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url) //URL de requisicao
                .build(); //constroi a ponte para o http

        //jsonresponse armazena a resposta capturada pela api
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erro na requisição: " + response.code());
            }

            //converte o dado retirado na api e transforma em String
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

}
