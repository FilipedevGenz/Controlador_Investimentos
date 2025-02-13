package org.controladorinvestimentos.controlador_investimentos.beans;


import org.controladorinvestimentos.controlador_investimentos.Banco.IrepositorioAtivos;
import org.controladorinvestimentos.controlador_investimentos.Banco.IrepositorioCarteira;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;

import java.time.LocalDate;

import static org.controladorinvestimentos.controlador_investimentos.beans.controladorRelatorio.criarRelatorio;

public class controladorCarteira {

    private org.controladorinvestimentos.controlador_investimentos.Banco.IrepositorioRelatorio IrepositorioRelatorio;
    private static IrepositorioCarteira _repositorioCarteira;
    private IrepositorioAtivos IrepositorioAtivos;
    public org.controladorinvestimentos.controlador_investimentos.beans.controladorRelatorio controladorRelatorio;
    public org.controladorinvestimentos.controlador_investimentos.Banco.IrepositorioRelatorio repositorioRelatorio;

    public void NovaCarteira(carteira carteira, conta conta) {
        try {
            org.controladorinvestimentos.controlador_investimentos.beans.carteira _carteira = _repositorioCarteira.buscarCarteira(carteira);
            if (_carteira != null) {
                throw new Exist("Essa carteira já existe no sistema.");
            }
        } catch (Exception e) {
            _repositorioCarteira.adicionarCarteira(carteira);
            conta.repositorioCarteira.getCarteiras().add(carteira);
        }
    }

    public void RemoverCarteira(carteira carteira) {

        try {
            org.controladorinvestimentos.controlador_investimentos.beans.carteira _carteira = _repositorioCarteira.buscarCarteira(carteira);
            if (_carteira != null) {
                _repositorioCarteira.removerCarteira(carteira);
            }
        }catch (Exception e) {
            throw new Exist("Essa carteira não existe.");
        }
    }

    public void comprarAtivo(int ID, String nomeAtivo, double qtd, carteira carteira, conta conta) throws Exist{
        try {

            double saldo = conta.getSaldo();
            ativo ativo = IrepositorioAtivos.buscarAtivo(nomeAtivo);
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