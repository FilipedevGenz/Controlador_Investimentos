package org.controladorinvestimentos.controladorInvestimentos.Banco;

import org.controladorinvestimentos.controladorInvestimentos.beans.Relatorio;

import java.util.ArrayList;

public interface IrepositorioRelatorio {
    public void addRelatorio (Relatorio relatorio);
    public Double getQuantidadeAtivo(String codeAtv);
    public ArrayList<Relatorio> getRelatorios();
    public Double retornaValorMedioDeCompra(String codigo);
    public Double calcularValorAtual();
    public void removerRelatorio(Relatorio relatorio);
}
