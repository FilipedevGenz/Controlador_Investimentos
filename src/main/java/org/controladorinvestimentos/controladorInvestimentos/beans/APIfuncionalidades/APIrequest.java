package org.controladorinvestimentos.controladorInvestimentos.beans.APIfuncionalidades;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;


public class APIrequest {

    private static final String API_KEY = "7kfUNQUQm5GxWV6GXAf3ig";
    private static final String BASE_URL = "https://brapi.dev/api/quote/";

    //codigo api feito pra retornar :  Nome do ativo (shortName)
    // Moeda (currency)
    // Preço atual (regularMarketPrice)
    // Preço máximo do dia (regularMarketDayHigh)
    // Preço mínimo do dia (regularMarketDayLow)
    // Volume negociado (regularMarketVolume)
    static JsonObject getAtivoData(String simbolo) throws IOException {

        simbolo = simbolo.trim().toUpperCase();
        String url = BASE_URL + simbolo + "?token=" + API_KEY;
        System.out.println("URL gerada para a API: " + url);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.out.println("Erro ao buscar ativo (" + simbolo + "): " + response.code() + " - " + response.message());
                return null; // Retorna null ao invés de lançar exceção
            }

            String jsonResponse = response.body().string();
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            return jsonObject.getAsJsonArray("results").get(0).getAsJsonObject();
        }
    }

    public static double buscarPrecoAtivoEmTempoReal(String simbolo) throws IOException {
        JsonObject results = getAtivoData(simbolo);
        return results.get("regularMarketPrice").getAsDouble();
    }


    public static String buscarNomeAtivo(String simbolo) throws IOException {
        JsonObject results = getAtivoData(simbolo);
        return results.get("shortName").getAsString();
    }

    public static String buscarMoedaAtivo(String simbolo) throws IOException {
        JsonObject results = getAtivoData(simbolo);
        return results.get("currency").getAsString();
    }


    //daqui pra baixo sai
    public static double buscarPrecoMaximoDia(String simbolo) throws IOException {
        JsonObject results = getAtivoData(simbolo);
        return results.get("regularMarketDayHigh").getAsDouble();
    }

    public static double buscarPrecoMinimoDia(String simbolo) throws IOException {
        JsonObject results = getAtivoData(simbolo);
        return results.get("regularMarketDayLow").getAsDouble();
    }

    public static long buscarVolumeNegociado(String simbolo) throws IOException {
        JsonObject results = getAtivoData(simbolo);
        return results.get("regularMarketVolume").getAsLong();
    }

    }

