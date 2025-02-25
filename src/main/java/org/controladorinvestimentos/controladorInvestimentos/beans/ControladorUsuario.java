package org.controladorinvestimentos.controladorInvestimentos.beans;

import org.controladorinvestimentos.controladorInvestimentos.Banco.IrepositorioUsuario;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioUsuario;
import org.controladorinvestimentos.controladorInvestimentos.Exceptions.Exist;
import org.controladorinvestimentos.controladorInvestimentos.beans.Strategy.emailStrategy;
import org.controladorinvestimentos.controladorInvestimentos.beans.Strategy.nomeStrategy;
import org.controladorinvestimentos.controladorInvestimentos.beans.Strategy.senhaStrategy;
import org.controladorinvestimentos.controladorInvestimentos.beans.Strategy.updateStrategy;

import java.util.Map;

import static org.controladorinvestimentos.controladorInvestimentos.beans.UpdateOptions.Email;
import static org.controladorinvestimentos.controladorInvestimentos.beans.UpdateOptions.Nome;

public class ControladorUsuario {

    private static IrepositorioUsuario IrepositorioUsuario;

    public ControladorUsuario() {
        RepositorioUsuario.getInstance();
    }

    public void cadastrarNovoUsuario(int cpf, String nome, String email, String senha) throws Exist {
        if (IrepositorioUsuario.buscarCPF(cpf)) {
            throw new Exist("Usuário já existe no sistema.");
        }
        Usuario novoUsuario = new Usuario(cpf, nome, senha, email);
        IrepositorioUsuario.adicionarUsuario(novoUsuario);
    }

    public void removerUsuario(Usuario usuario) {
        try {
            Usuario usuarioEncontrado = IrepositorioUsuario.buscarUsuario(usuario);
            if (usuarioEncontrado != null) {
                IrepositorioUsuario.removerUsuario(usuario);
            }
        } catch (Exception e) {
            throw new Exist("Usuario não existe no sistema.");
        }
    }

    public void alterarUsuario(Usuario usuario, UpdateOptions option) throws Exist {
        final Map<UpdateOptions, updateStrategy> mapStrategy = Map.of(
                Nome, new nomeStrategy(),
                UpdateOptions.Senha, new senhaStrategy(),
                Email, new emailStrategy()
        );
        try {
            Usuario usuarioAtual = IrepositorioUsuario.buscarUsuario(usuario);
            mapStrategy.get(option).updateInfo(usuarioAtual);
        } catch (Exception e) {
            throw new Exist("Usuario não existe no sistema");
        }
    }

    public Usuario buscarUsuario(int cpf) {
        return IrepositorioUsuario.buscarCPFreturnConta(cpf);
    }
}