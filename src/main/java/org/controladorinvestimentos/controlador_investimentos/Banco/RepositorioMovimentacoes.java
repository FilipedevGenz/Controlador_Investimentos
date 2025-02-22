package org.controladorinvestimentos.controlador_investimentos.Banco;
import lombok.Getter;
import lombok.Setter;
import org.controladorinvestimentos.controlador_investimentos.beans.Relatorio;
import org.controladorinvestimentos.controlador_investimentos.beans.Carteira;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter

public class RepositorioMovimentacoes {

    //criar um IrepositorioMovimentacoes

    private static final ArrayList<Map<Relatorio, Carteira>> listaAtivos = new ArrayList<>();

    public void AddRelatorio(Relatorio relatorio, Carteira carteira){
        Map<Relatorio, Carteira> relatorioCarteira = new HashMap<>();
        relatorioCarteira.put(relatorio, carteira);
        listaAtivos.add(relatorioCarteira);
    }

    public boolean RemoveRelatorio(Relatorio relatorio, Carteira carteira){
        Map<Relatorio, Carteira> relatorioCarteira = new HashMap<>();
        relatorioCarteira.put(relatorio, carteira);
        return listaAtivos.remove(relatorioCarteira);
    }

    public static ArrayList<Relatorio> getRelatorio(String ativo, Carteira carteira){
        ArrayList<Relatorio> toReturn = new ArrayList<>();

        for (Map<Relatorio, Carteira> map : listaAtivos) {
            for (Map.Entry<Relatorio, Carteira> entry : map.entrySet()) {
                Relatorio relatorio2 = entry.getKey();
                Carteira carteira2 = entry.getValue();

                // Verifica se o nomeAtivo do Relatorio é igual e a carteira também é a mesma
                if (relatorio2.getNomeAtivo().equals(ativo) && carteira2.equals(carteira)) {
                    toReturn.add(relatorio2);
                }
            }
        }
        return toReturn;
    }

    public static ArrayList<Relatorio> RelatoriosCarteira(Carteira carteira){
        ArrayList<Relatorio> toReturn = new ArrayList<>();
        for (Map<Relatorio, Carteira> map : listaAtivos) {
            for (Map.Entry<Relatorio, Carteira> entry : map.entrySet()) {
                Relatorio relatorio2 = entry.getKey();

            }
        }
    }
}
