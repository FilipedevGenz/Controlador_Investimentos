package org.controladorinvestimentos.controlador_investimentos.beans;

import org.controladorinvestimentos.controlador_investimentos.Banco.IrepositorioUsuario;
import org.controladorinvestimentos.controlador_investimentos.Banco.repositorioUsuario;
import org.controladorinvestimentos.controlador_investimentos.Exceptions.Exist;
import org.controladorinvestimentos.controlador_investimentos.beans.Strategy.emailStrategy;
import org.controladorinvestimentos.controlador_investimentos.beans.Strategy.nomeStrategy;
import org.controladorinvestimentos.controlador_investimentos.beans.Strategy.senhaStrategy;
import org.controladorinvestimentos.controlador_investimentos.beans.Strategy.updateStrategy;

import java.util.Map;

import static org.controladorinvestimentos.controlador_investimentos.beans.updateOptions.Email;
import static org.controladorinvestimentos.controlador_investimentos.beans.updateOptions.Nome;

public class controladorUsuario {

    private static IrepositorioUsuario IrepositorioUsuario;

    public controladorUsuario() {
        repositorioUsuario.getInstance();
    }

    public void cadastrarNovoUsuario(int cpf, String nome, String email, String senha) throws Exist {
        if (IrepositorioUsuario.buscarCPF(cpf)) {
            throw new Exist("Usuário já existe no sistema.");
        }
        usuario novoUsuario = new usuario(cpf, nome, senha, email);
        IrepositorioUsuario.adicionarUsuario(novoUsuario);
    }

    public void RemoverUsuario(usuario usuario) {
        try {
            usuario usuarioEncontrado = IrepositorioUsuario.buscarUsuario(usuario);
            if (usuarioEncontrado != null) {
                IrepositorioUsuario.removerUsuario(usuario);
            }
        } catch (Exception e) {
            throw new Exist("Usuario não existe no sistema.");
        }
    }

    public void AlterarUsuario(usuario usuario, updateOptions option) throws Exist {
        final Map<updateOptions, updateStrategy> mapStrategy = Map.of(
                Nome, new nomeStrategy(),
                updateOptions.Senha, new senhaStrategy(),
                Email, new emailStrategy()
        );
        try {
            usuario usuarioAtual = IrepositorioUsuario.buscarUsuario(usuario);
            mapStrategy.get(option).updateInfo(usuarioAtual);
        } catch (Exception e) {
            throw new Exist("Usuario não existe no sistema");
        }
    }

    public usuario buscarUsuario(int cpf) {
        return IrepositorioUsuario.buscarCPFreturnConta(cpf);
    }
}