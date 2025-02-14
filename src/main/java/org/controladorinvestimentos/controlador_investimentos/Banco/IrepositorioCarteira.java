package org.controladorinvestimentos.controlador_investimentos.Banco;
import org.controladorinvestimentos.controlador_investimentos.beans.ativo;
import org.controladorinvestimentos.controlador_investimentos.beans.Carteira;

public interface IrepositorioCarteira {
    public void AddToRepository(Carteira carteira);
    public void RemoveFromRepository(Carteira carteira);
    public Carteira GetFromRepository(Carteira carteira);
}
