package org.controladorinvestimentos.controlador_investimentos.beans;


import org.controladorinvestimentos.controlador_investimentos.Banco.iRepositorioAtivos;
import org.controladorinvestimentos.controlador_investimentos.Banco.iRepositorioCarteira;
import org.controladorinvestimentos.controlador_investimentos.Banco.irepositorioRelatorio;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;

import java.time.LocalDate;

import static org.controladorinvestimentos.controlador_investimentos.beans.ControladorRelatorio.criarRelatorio;

public class ControladorCarteira {

    private irepositorioRelatorio IrepositorioRelatorio;
    private static iRepositorioCarteira _repositorioCarteira;
    private iRepositorioAtivos _repositorioAtivos;
    public ControladorRelatorio controladorRelatorio;
    public irepositorioRelatorio repositorioRelatorio;

    public void NovaCarteira(Carteira carteira,Conta conta) {
        try {
            Carteira _Carteira = _repositorioCarteira.buscarCarteira(carteira);
            if (_Carteira != null) {
                throw new Exist("Essa carteira já existe no sistema.");
            }
        } catch (Exception e) {
            _repositorioCarteira.adicionarCarteira(carteira);
            conta.getCarteiras().add(carteira);
        }
    }

    public void RemoverCarteira(Carteira carteira) {

        try {
            Carteira _carteira = _repositorioCarteira.buscarCarteira(carteira);
            if (_carteira != null) {
                _repositorioCarteira.removerCarteira(carteira);
            }
        }catch (Exception e) {
            throw new Exist("Essa carteira não existe.");
        }
    }

    void comprarAtivo(int ID, String nomeAtivo, double qtd, Carteira carteira, Conta conta) throws Exist{
        try {

            double saldo = conta.getSaldo();
            Ativo ativo = _repositorioAtivos.buscarAtivo(nomeAtivo);
            if (ativo == null) {
                //adicionar chamada de popUP na gui

            }
            double precoAtivo = ativo.getPreco();

            if (saldo >= (precoAtivo * qtd)) {

                conta.debitar(precoAtivo * qtd);
                // Adiciona o ativo e sua quantidade à carteira.

                carteira.adicionarAtivoNaCarteira(ativo, qtd);
                relatorio newRelatorio = criarRelatorio(nomeAtivo, precoAtivo, LocalDate.now(), qtd);
                repositorioRelatorio.addRelatorio(newRelatorio);

            } else {
                throw new Exist("Saldo insuficiente para a compra.");
            }
        } catch (Exception e) {
            throw new Exist("Não foi possível comprar o ativo. Verifique os dados e o saldo.");
        }
    }



}