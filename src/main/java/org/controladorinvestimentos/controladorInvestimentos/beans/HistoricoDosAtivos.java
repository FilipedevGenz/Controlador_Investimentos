package org.controladorinvestimentos.controladorInvestimentos.beans;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HistoricoDosAtivos {
    private static final String API_URL = "https://brapi.dev/api/quote/";
    private static final String API_TOKEN = "7kfUNQUQm5GxWV6GXAf3ig";
    private static final OkHttpClient client = new OkHttpClient();

    public static List<HistoricoAtivo> retornaListaDadosDeHistorico(String ativo, LocalDate dataCompra) {
        List<HistoricoAtivo> historicoFiltrado = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            String url = API_URL + ativo + "?range=12mo&interval=1mo&token=" + API_TOKEN;
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                throw new IOException("Erro na conexão com a API: " + response.code());
            }

            JsonObject jsonObject = JsonParser.parseString(response.body().string()).getAsJsonObject();
            JsonArray historico = jsonObject.getAsJsonArray("results");

            for (int i = 0; i < historico.size(); i++) {
                JsonObject item = historico.get(i).getAsJsonObject();
                LocalDate data = LocalDate.parse(item.get("date").getAsString(), formatter);
                double preco = item.get("close").getAsDouble();

                // Calcula o período associado ao ativo em meses desde a compra
                int periodoAssociado = (int) ChronoUnit.MONTHS.between(dataCompra, data);
                historicoFiltrado.add(new HistoricoAtivo(data, preco, periodoAssociado));
            }
        } catch (IOException e) {
            System.err.println("Erro ao obter dados da API: " + e.getMessage());
        }

        return historicoFiltrado;
    }

    public static class HistoricoAtivo {
        private LocalDate data;
        private double preco;
        private int periodoAssociado;

        public HistoricoAtivo(LocalDate data, double preco, int periodoAssociado) {
            this.data = data;
            this.preco = preco;
            this.periodoAssociado = periodoAssociado;
        }

        public LocalDate getData() {
            return data;
        }

        public double getPreco() {
            return preco;
        }

        public int getPeriodoAssociado() {
            return periodoAssociado;
        }
    }

    public static double obterPrecoDeCompra(String ativo, LocalDate dataCompra) {
        List<HistoricoAtivo> historico = retornaListaDadosDeHistorico(ativo, dataCompra);
        return historico.isEmpty() ? 0.0 : historico.get(0).getPreco();
    }
}
