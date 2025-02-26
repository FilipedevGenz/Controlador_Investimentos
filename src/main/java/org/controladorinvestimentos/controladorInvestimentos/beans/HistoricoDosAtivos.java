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
import java.util.stream.Collectors;

public class HistoricoDosAtivos {
    private static final String API_URL = "https://brapi.dev/api/quote/";
    private static final String API_TOKEN = "7kfUNQUQm5GxWV6GXAf3ig";
    private static final OkHttpClient client = new OkHttpClient();

        public static double calcularTaxaParaCompra(String ativo) {
            LocalDate dataInicio = LocalDate.now().minusMonths(6);
            List<HistoricoAtivo> historico = retornaListaDadosDeHistorico(ativo, dataInicio);

            if (historico.isEmpty()) {
                return 0.0;
            }

            // Obtendo os preços dos últimos 6 meses
            List<Double> precos = historico.stream()
                    .map(HistoricoAtivo::getPreco)
                    .sorted()
                    .collect(Collectors.toList());

            // Calculando a mediana
            double mediana;
            int size = precos.size();
            if (size % 2 == 0) {
                mediana = (precos.get(size / 2 - 1) + precos.get(size / 2)) / 2.0;
            } else {
                mediana = precos.get(size / 2);
            }

            // Obtendo o valor atual do ativo
            double precoAtual;
            try {
                precoAtual = APIrequest.buscarPrecoAtivoEmTempoReal(ativo);
            } catch (IOException e) {
                System.err.println("Erro ao obter preço atual do ativo: " + ativo);
                return 0.0;
            }

            return mediana - precoAtual;
        }



    public static double calcularTaxaDeVariacao(String ativo, LocalDate dataCompra) {
        double precoCompra = obterPrecoDeCompra(ativo, dataCompra);
        double precoAtual;
        try {
            precoAtual = APIrequest.buscarPrecoAtivoEmTempoReal(ativo);
        } catch (IOException e) {
            System.err.println("Erro ao obter preço atual do ativo: " + ativo);
            return 0.0;
        }

        if (precoCompra == 0) return 0.0;

        return ((precoAtual - precoCompra) / precoCompra) * 100;
    }

    public static double obterPrecoDeCompra(String ativo, LocalDate dataCompra) {
        List<HistoricoAtivo> historico = retornaListaDadosDeHistorico(ativo, dataCompra);
        return historico.isEmpty() ? 0.0 : historico.get(0).getPreco();
    }

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

                historicoFiltrado.add(new HistoricoAtivo(data, preco, (int) ChronoUnit.MONTHS.between(dataCompra, data)));
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
}
