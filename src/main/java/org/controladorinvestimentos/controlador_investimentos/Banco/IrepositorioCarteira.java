package org.controladorinvestimentos.controlador_investimentos.Banco;
import org.controladorinvestimentos.controlador_investimentos.beans.ativo;
import org.controladorinvestimentos.controlador_investimentos.beans.carteira;

public interface IrepositorioCarteira {
    public void AddToRepository(carteira carteira);
    public void RemoveFromRepository(carteira carteira);
    public carteira GetFromRepository(carteira carteira);
}
