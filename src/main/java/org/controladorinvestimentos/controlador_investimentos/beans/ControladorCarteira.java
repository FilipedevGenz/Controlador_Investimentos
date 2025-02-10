package org.controladorinvestimentos.controlador_investimentos.beans;


import org.controladorinvestimentos.controlador_investimentos.Banco.iRepositorioAtivos;
import org.controladorinvestimentos.controlador_investimentos.Banco.iRepositorioCarteira;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;

public class ControladorCarteira {

    private static iRepositorioCarteira _repositorioCarteira;
    private iRepositorioAtivos _repositorioAtivos;

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

    void comprarAtivo(int ID, String nomeAtivo, double qtd, Carteira carteira, Conta conta) {
        try {
            double saldo = conta.getSaldo();
            Ativo ativo = _repositorioAtivos.buscarAtivo(nomeAtivo);
            double precoAtivo = ativo.getPreco();

            if (saldo >= (precoAtivo * qtd)) {
                // Atualiza o saldo da conta (se necessário).
                // conta.debitar(precoAtivo * qtd);

                // Adiciona o ativo e sua quantidade à carteira.
                carteira.adicionarAtivo(ativo, qtd,_repositorioAtivos);

                // Atualiza a quantidade global do ativo (se aplicável ao sistema).
                ativo.setQuantidade(qtd);
            } else {
                throw new Exist("Saldo insuficiente para a compra.");
            }
        } catch (Exception e) {
            throw new Exist("Não foi possível comprar o ativo. Verifique os dados e o saldo.");
        }
    }



}