package org.controladorinvestimentos.controlador_investimentos.Banco;

import org.controladorinvestimentos.controlador_investimentos.beans.Relatorio;

public interface IrepositorioRelatorio {
    public void addRelatorio (Relatorio relatorio);
    public Double getQuantidadeAtivo(String nameAtv);
    public Double valorDeCompraCarteira();

}
