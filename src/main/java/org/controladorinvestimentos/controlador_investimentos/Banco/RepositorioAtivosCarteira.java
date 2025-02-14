package org.controladorinvestimentos.controlador_investimentos.Banco;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.controladorinvestimentos.controlador_investimentos.beans.carteira;
import org.controladorinvestimentos.controlador_investimentos.beans.relatorio;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter

public class RepositorioAtivosCarteira {


    private static final ArrayList<Map<relatorio,carteira>> listaAtivos = new ArrayList<>();

    public static void AddRelatorio(relatorio relatorio,carteira carteira){
        Map<relatorio,carteira> relatorioCarteira = new HashMap<>();
        relatorioCarteira.put(relatorio, carteira);
        listaAtivos.add(relatorioCarteira);
    }

    public static void RemoveRelatorio(relatorio relatorio,carteira carteira){
        Map<relatorio,carteira> relatorioCarteira = new HashMap<>();
        relatorioCarteira.put(relatorio, carteira);
        listaAtivos.remove(relatorioCarteira);
    }

    public static ArrayList<relatorio> GetRelatorio(relatorio relatorio,carteira carteira){
        ArrayList<relatorio> toReturn = new ArrayList<>();

        for (Map<relatorio, carteira> map : listaAtivos) {
            for (Map.Entry<relatorio, carteira> entry : map.entrySet()) {
                relatorio rel = entry.getKey();
                carteira cart = entry.getValue();

                // Verifica se o nomeAtivo do relatorio é igual e a carteira também é a mesma
                if (rel.getNomeAtivo().equals(relatorio.getNomeAtivo()) && cart.equals(carteira)) {
                    toReturn.add(rel);
                }
            }
        }
        return toReturn;
    }
}
