/*
package org.controladorinvestimentos.controladorInvestimentos.GUI;

import org.controladorinvestimentos.controladorInvestimentos.beans.HistoricoDosAtivos;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import javax.swing.JFrame;
import java.time.LocalDate;
import java.util.List;

public class GraficoAtivo {

    // Novo método para gerar o conjunto de dados (dataset) para o gráfico
    public static TimeSeriesCollection gerarDataset(String ativo, HistoricoDosAtivos api) {
        List<HistoricoDosAtivos.HistoricoAtivo> dados = api.retornaListaDadosDeHistorico(ativo);

        TimeSeries series = new TimeSeries("Preço do Ativo");
        for (HistoricoDosAtivos.HistoricoAtivo dado : dados) {
            LocalDate data = dado.getData();
            Month mes = new Month(data.getMonthValue(), data.getYear());
            series.add(mes, dado.getPreco());
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    public static JFreeChart criarGraficoSemMostrar(String ativo) {
        // Usa o novo método para gerar os dados
        TimeSeriesCollection dataset = gerarDataset(ativo, new HistoricoDosAtivos());

        // Cria e retorna o gráfico sem abrir a janela
        return ChartFactory.createTimeSeriesChart(
                "Preço do Ativo ao Longo do Tempo",
                "Data",
                "Preço",
                dataset,
                true,
                true,
                false
        );
    }

    public static void criarGrafico(String ativo) {
        JFreeChart chart = criarGraficoSemMostrar(ativo);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        JFrame frame = new JFrame("Gráfico do Ativo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

 */
