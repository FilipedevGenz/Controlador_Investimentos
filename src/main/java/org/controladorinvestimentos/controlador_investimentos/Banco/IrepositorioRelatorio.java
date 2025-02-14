package org.controladorinvestimentos.controlador_investimentos.Banco;
import org.controladorinvestimentos.controlador_investimentos.beans.relatorio;

public interface IrepositorioRelatorio {
    public void addRelatorio (relatorio relatorio);
    public Double getQuantidadeAtivo(String nameAtv);
    public Double calcularValorAtual();

}
