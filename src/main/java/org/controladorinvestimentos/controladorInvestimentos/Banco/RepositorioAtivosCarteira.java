package org.controladorinvestimentos.controladorInvestimentos.Banco;

import org.controladorinvestimentos.controladorInvestimentos.Banco.Interfaces.IrepositorioRelatorio;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Ativo;
import lombok.Getter;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Relatorio;
import org.controladorinvestimentos.controladorInvestimentos.beans.ControladorRelatorio;

import java.io.IOException;
import java.util.*;

public class RepositorioAtivosCarteira {
    @Getter
    private final List<Map<Double, Ativo>> ativosCarteira;

    public RepositorioAtivosCarteira() {
        this.ativosCarteira = new ArrayList<>();
    }

    public void adicionarAtivo(Ativo novoAtivo, Double quantidade) throws IOException {
        Map<Double, Ativo> novoRegistro = new HashMap<>();
        novoRegistro.put(quantidade, new Ativo(novoAtivo.getNome(), novoAtivo.getPreco()));
        ativosCarteira.add(novoRegistro);
    }

    public boolean removerAtivo(String nomeAtivo,Double quantidade){

        Optional<Map<Double, Ativo>> ativoParaRemover = ativosCarteira.stream()
                .filter(map -> map.containsKey(quantidade) && map.values()
                .stream().anyMatch(a -> a.getNome().equals(nomeAtivo)))
                .findFirst();

        if (ativoParaRemover.isPresent()) {
            ativosCarteira.remove(ativoParaRemover.get());
            return true;
        } else {
            System.out.println("Ativo não encontrado na carteira.");
            return false;
        }
    }

    public Map<Double, Ativo> buscarAtivo(String nomeAtivo) {
        return ativosCarteira.stream()
                .filter(map -> map.values().stream().anyMatch(a -> a.getNome().equals(nomeAtivo)))
                .findFirst()
                .orElse(null);
    }

    public List<Map<Double, Ativo>> listarAtivos() {
        return new ArrayList<>(ativosCarteira);
    }
}
