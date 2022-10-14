package br.com.ada.entidade;

public class Usuario {

    private Integer id;
    private String nome;
    private int idade;
    private String email;
    private String senha;
    private String cpf;

    public Usuario(Integer id, String nome, int idade, String email, String senha, String cpf) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getCpf() {
        return cpf;
    }

}
