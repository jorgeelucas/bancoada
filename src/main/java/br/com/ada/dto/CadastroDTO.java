package br.com.ada.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class CadastroDTO {
    // DATA TRANSFER OBJECT

    @JsonProperty("nome_do_usuario")
    private String nome;
    private int idade;
    private String email;
    private String senha;
    private String cpf;
    private ContaCorrenteDTO contaCorrente;

    public CadastroDTO(String nome, int idade, String email, String senha, String cpf, ContaCorrenteDTO contaCorrente) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.contaCorrente = contaCorrente;
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

    public ContaCorrenteDTO getContaCorrente() {
        return contaCorrente;
    }
}
