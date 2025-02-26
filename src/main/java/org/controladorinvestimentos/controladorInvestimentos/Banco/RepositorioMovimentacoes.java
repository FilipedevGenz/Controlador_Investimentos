package org.controladorinvestimentos.controladorInvestimentos.Banco;

import lombok.Getter;
import lombok.Setter;
import org.controladorinvestimentos.controladorInvestimentos.beans.Relatorio;
import org.controladorinvestimentos.controladorInvestimentos.beans.Carteira;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class RepositorioMovimentacoes {

    // Criar um IrepositorioMovimentacoes

    private static final ArrayList<Map<Relatorio, Carteira>> listaAtivos = new ArrayList<>();

    // Aqui em repositórioMovimentações, optou-se por criar um map que associa Relatorio de movimentações a cada carteira.
    private static volatile RepositorioMovimentacoes instance;

    private RepositorioMovimentacoes() {}

    public static RepositorioMovimentacoes getInstance() {
        if (instance == null) {
            synchronized(RepositorioMovimentacoes.class) {
                if (instance == null) {
                    instance = new RepositorioMovimentacoes();
                }
            }
        }
        return instance;
    }

    // Adiciona um relatório a uma carteira
    public void addRelatorio(Relatorio relatorio, Carteira carteira) {
            getInstance(); //inicializa o repositorio
            Map<Relatorio, Carteira> relatorioCarteira = new HashMap<>();
            relatorioCarteira.put(relatorio, carteira);
            listaAtivos.add(relatorioCarteira);
    }

    // Remove um relatório de uma carteira
    public static boolean removeRelatorio(Relatorio relatorio, Carteira carteira) {
        Map<Relatorio, Carteira> relatorioCarteira = new HashMap<>();
        relatorioCarteira.put(relatorio, carteira);
        getInstance();
        return listaAtivos.remove(relatorioCarteira);
    }


    public static ArrayList<Relatorio> getRelatorio(String ativo, Carteira carteira){

        ArrayList<Relatorio> toReturn = new ArrayList<>();

        for (Map<Relatorio, Carteira> map : listaAtivos) {
            for (Map.Entry<Relatorio, Carteira> entry : map.entrySet()) {
                Relatorio relatorio2 = entry.getKey();
                Carteira carteira2 = entry.getValue();

                // Verifica se o nomeAtivo do Relatorio é igual e a carteira também é a mesma
                if (relatorio2.getCodigo().equals(ativo) && carteira2.equals(carteira)) {

                    toReturn.add(relatorio2);
                }
            }
        }
        return toReturn;
    }

    public static Relatorio searchRelatorio(String code, Double qnt, Carteira carteira ){

        Relatorio relatorio = null;

        for (Map<Relatorio, Carteira> map : listaAtivos) {
            for (Map.Entry<Relatorio, Carteira> entry : map.entrySet()) {
                Relatorio relatorio2 = entry.getKey();
                Carteira carteira2 = entry.getValue();

                // Verifica se o nomeAtivo do Relatorio é igual e a carteira também é a mesma
                if (relatorio2.getCodigo().equals(code) && carteira2.equals(carteira) && relatorio2.getQuantidade() == qnt) {

                    relatorio = relatorio2;
                }
            }
        }
                return relatorio;
    }


            public static ArrayList<Relatorio> relatoriosCarteira(Carteira carteira){
                ArrayList<Relatorio> toReturn = new ArrayList<>();
                for (Map<Relatorio, Carteira> map : listaAtivos) {
                    for (Map.Entry<Relatorio, Carteira> entry : map.entrySet()) {
                        Relatorio relatorio2 = entry.getKey();

                        if (entry.getValue().equals(carteira)) {
                            toReturn.add(entry.getKey());
                        }
                    }
                }
                return toReturn;
            }

            public static boolean containsRelatorio (String ativo, Carteira carteira){
                ArrayList<Relatorio> toCompare = getRelatorio(ativo, carteira);
                return !toCompare.isEmpty();
            }
        }

