
package org.controladorinvestimentos.controladorInvestimentos.beans;

import org.controladorinvestimentos.controladorInvestimentos.Banco.Interfaces.IrepositorioUsuario;
import org.controladorinvestimentos.controladorInvestimentos.Banco.RepositorioUsuario;
import org.controladorinvestimentos.controladorInvestimentos.Exceptions.Exist;
import org.controladorinvestimentos.controladorInvestimentos.beans.ClassesConstrutoras.Usuario;
import org.controladorinvestimentos.controladorInvestimentos.beans.Strategy.EmailStrategy;
import org.controladorinvestimentos.controladorInvestimentos.beans.Strategy.NomeStrategy;
import org.controladorinvestimentos.controladorInvestimentos.beans.Strategy.SenhaStrategy;
import org.controladorinvestimentos.controladorInvestimentos.beans.Strategy.updateStrategy;
import java.util.Map;
import static org.controladorinvestimentos.controladorInvestimentos.beans.UpdateOptions.Email;
import static org.controladorinvestimentos.controladorInvestimentos.beans.UpdateOptions.Nome;

public class ControladorUsuario {


    private static IrepositorioUsuario IrepositorioUsuario;

    public ControladorUsuario() {
        this.IrepositorioUsuario = RepositorioUsuario.getInstance(); // Certifique-se de inicializar corretamente
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
                Nome, new NomeStrategy(),
                UpdateOptions.Senha, new SenhaStrategy(),
                Email, new EmailStrategy()
        );
        try {
            Usuario usuarioAtual = IrepositorioUsuario.buscarUsuario(usuario);
            mapStrategy.get(option).updateInfo(usuarioAtual);
        } catch (Exception e) {
            throw new Exist("Usuario não existe no sistema");
        }
    }
}
