package org.controladorinvestimentos.controladorInvestimentos.Banco;

import org.controladorinvestimentos.controladorInvestimentos.Exceptions.Exist;
import org.controladorinvestimentos.controladorInvestimentos.beans.Carteira;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class RepositorioCarteira implements IrepositorioCarteira {

    private static RepositorioCarteira instance;
    private final Map<Integer, Carteira> carteiras = new HashMap<>();

    private RepositorioCarteira() {}

    // Carteira é uma classe "dependente". O singleton aqui armazena as carteiras presentes no sistema
    public static synchronized RepositorioCarteira getInstance() {
        if (instance == null) {
            instance = new RepositorioCarteira();
        }
        return instance;
    }

    // CRUD de carteiras

    @Override
    public void adicionarCarteira(Carteira carteira) {
        carteiras.put(Integer.valueOf(carteira.getIDcarteira()), carteira);
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
        return new ArrayList<>(carteiras.values());
    }
}

