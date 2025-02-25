package org.controladorinvestimentos.controladorInvestimentos.Banco;

import org.controladorinvestimentos.controladorInvestimentos.Exceptions.Exist;
import org.controladorinvestimentos.controladorInvestimentos.beans.Carteira;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RepositorioCarteira implements IrepositorioCarteira {

    private final Map<Integer, Carteira> carteiras = new HashMap<>();

    @Override
    public void adicionarCarteira(Carteira carteira) {
        carteiras.put(Integer.valueOf(carteira.ID), carteira);
    }

    @Override
    public void removerCarteira(int carteiraID) throws Exist {
        if (!carteiras.containsKey(carteiraID)) {
            throw new Exist("Carteira não encontrada para remoção.");
        }
        carteiras.remove(carteiraID);
    }

    @Override
    public Carteira buscarCarteira(int carteiraID) throws Exist {
        if (!carteiras.containsKey(carteiraID)) {
            throw new Exist("Carteira não encontrada.");
        }
        return carteiras.get(carteiraID);
    }

    @Override
    public List<Carteira> listarCarteiras() {
        return carteiras.values().stream().collect(Collectors.toList());
    }
}
