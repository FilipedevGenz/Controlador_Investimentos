package org.controladorinvestimentos.controlador_investimentos.Banco;

import org.controladorinvestimentos.controlador_investimentos.beans.Relatorio;

import java.util.ArrayList;
import java.util.List;

public interface IrepositorioRelatorio {
    public void addRelatorio (Relatorio relatorio);
    public Double getQuantidadeAtivo(String nameAtv);
    public Double valorDeCompraCarteira();
    public ArrayList<Relatorio> getRelatorios();
    public Double valorMedioDeCompra(String nomeAtivo);

    public double calcularValorAtual();
    public List<String> getAtivos();
}
