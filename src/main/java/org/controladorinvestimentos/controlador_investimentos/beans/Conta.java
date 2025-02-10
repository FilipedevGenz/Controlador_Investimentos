package beans;

public class Conta extends Usuario{

    Conta() {}

    Conta(int cpf, String nome, String senha, String email){
        setCpf(cpf);
        setNome(nome);
        setSenha(senha);
        setEmail(email);
    }

}
