package org.controladorinvestimentos.controladorInvestimentos.beans.APIfuncionalidades;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Relatorio;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.controladorinvestimentos.controladorInvestimentos.beans.APIfuncionalidades.APIrequest.getAtivoData;

public class HistoricoDosAtivos {
    private static final String API_URL = "https://brapi.dev/api/quote/";
    private static final String API_TOKEN = "7kfUNQUQm5GxWV6GXAf3ig";
    private static final OkHttpClient client = new OkHttpClient();

    public static double calcularTaxaParaCompra(String ativo) {
        LocalDate dataInicio = LocalDate.now().minusMonths(3);
        List<HistoricoAtivo> historico = retornaListaDadosDeHistorico(ativo, dataInicio);

        if (historico.isEmpty()) {
            return 0.0;
        }

        // Obtendo os preços dos últimos 6 meses
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
            System.err.println("Erro ao obter preço atual do ativo: " + ativo);
            return 0.0;
        }

        return mediana - precoAtual;
    }

    public static double buscarPrecoAtivoEmTempoReal(String simbolo) {
        try {
            JsonObject results = getAtivoData(simbolo);
            if (results.has("regularMarketPrice")) {
                return results.get("regularMarketPrice").getAsDouble();
            } else {
                System.err.println("Erro: regularMarketPrice não encontrado para " + simbolo);
                return 0.0;
            }
        } catch (IOException e) {
            System.err.println("Erro ao buscar preço do ativo " + simbolo + ": " + e.getMessage());
            return 0.0;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao buscar preço do ativo " + simbolo);
            return 0.0;
        }
    }


    public static double calcularTaxaDeVariacao(String ativo, LocalDate dataCompra) {
        double precoCompra = obterPrecoDeCompra(ativo, dataCompra);
        double precoAtual;
        try {
            precoAtual = APIrequest.buscarPrecoAtivoEmTempoReal(ativo);
        } catch (IOException e) {
            System.err.println("Erro ao obter preço atual do ativo: " + ativo);
            return 0.0;
        }

        if (precoCompra == 0) return 0.0;

        return ((precoAtual - precoCompra) / precoCompra) * 100;
    }

    public static double atribuirVariacaoPreco(Relatorio ativo, LocalDate dataCompra) {
        return calcularTaxaDeVariacao(ativo.getCodigo(), dataCompra);
    }

    public static double obterPrecoDeCompra(String ativo, LocalDate dataCompra) {
        List<HistoricoAtivo> historico = retornaListaDadosDeHistorico(ativo, dataCompra);
        return historico.isEmpty() ? 0.0 : historico.get(0).getPreco();
    }

    public static List<HistoricoAtivo> retornaListaDadosDeHistorico(String ativo, LocalDate dataCompra) {
        List<HistoricoAtivo> historicoFiltrado = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            String url = "https://brapi.dev/api/quote/" + ativo + "?range=3mo&interval=1d&token=7kfUNQUQm5GxWV6GXAf3ig";

            // DEBUG: Printando a URL usada na API
            System.out.println("URL da API: " + url);

            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();

            // Verificar se a resposta da API foi bem-sucedida
            if (!response.isSuccessful()) {
                System.err.println("Erro na conexão com a API: " + response.code() + " - " + response.message());
                return historicoFiltrado;
            }

            String responseBody = response.body().string();
            System.out.println("Resposta da API: " + responseBody);

            JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();

            // Verificar se "results" contém dados
            if (!jsonObject.has("results")) {
                System.err.println("Erro: campo 'results' não encontrado na resposta da API.");
                return historicoFiltrado;
            }

            JsonArray results = jsonObject.getAsJsonArray("results");
            if (results.size() == 0) {
                System.err.println("Erro: Nenhum resultado encontrado para o ativo.");
                return historicoFiltrado;
            }

            // Pegar o primeiro objeto (correspondente ao ativo consultado)
            JsonObject ativoData = results.get(0).getAsJsonObject();

            // Verificar se "historicalDataPrice" está presente
            if (!ativoData.has("historicalDataPrice")) {
                System.err.println("Erro: campo 'historicalDataPrice' não encontrado na resposta da API.");
                return historicoFiltrado;
            }

            JsonArray historico = ativoData.getAsJsonArray("historicalDataPrice");

            for (int i = 0; i < historico.size(); i++) {
                JsonObject item = historico.get(i).getAsJsonObject();

                if (!item.has("date") || !item.has("close")) {
                    continue; // Pular entradas inválidas
                }

                // Convertendo timestamp UNIX (segundos) para LocalDate
                long timestamp = item.get("date").getAsLong();
                LocalDate data = LocalDate.ofEpochDay(timestamp / 86400);

                double preco = item.get("close").getAsDouble();

                // Apenas adicionar os dados que são posteriores à dataCompra
                if (!data.isBefore(dataCompra)) {
                    historicoFiltrado.add(new HistoricoAtivo(data, preco, (int) ChronoUnit.MONTHS.between(dataCompra, data)));
                }
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