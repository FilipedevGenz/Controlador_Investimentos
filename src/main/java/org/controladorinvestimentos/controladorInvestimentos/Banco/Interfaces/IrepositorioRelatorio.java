package org.controladorinvestimentos.controladorInvestimentos.Banco.Interfaces;

import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Relatorio;

import java.util.ArrayList;

public interface IrepositorioRelatorio {
    public void addRelatorio (Relatorio relatorio);
    public Double getQuantidadeAtivo(String codeAtv);
    public ArrayList<Relatorio> getRelatorios();
    public Double retornaValorMedioDeCompra(String codigo);
    public Double calcularValorAtual();
    public void removerRelatorio(String codigo, double quantidade);
    Relatorio buscarRelatorio(String codeAtv);
}
