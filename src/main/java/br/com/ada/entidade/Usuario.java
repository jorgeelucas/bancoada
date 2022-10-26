package br.com.ada.entidade;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOME_DO_USUARIO", nullable = false)
    private String nome;

    private int idade;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String cpf;

    // Fetch= EAGER/LAZY
    // LazyInitializationException

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.PERSIST)
    private ContaCorrente contaCorrente;

    private LocalDateTime dataCadastro;
    private LocalDateTime dataExpiracaoDaConta;
    private Long dataAtualizacao;

    public Usuario() {
    }

    public Usuario(Integer id, String nome, int idade, String email, String senha, String cpf, ContaCorrente contaCorrente,
                   LocalDateTime dataCadastro, long dataAtualizacao, LocalDateTime dataExpiracaoDaConta) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.contaCorrente = contaCorrente;
        this.dataCadastro = dataCadastro;
        this.dataAtualizacao = dataAtualizacao;
        this.dataExpiracaoDaConta = dataExpiracaoDaConta;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Long getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Long dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public LocalDateTime getDataExpiracaoDaConta() {
        return dataExpiracaoDaConta;
    }

    public void setDataExpiracaoDaConta(LocalDateTime dataExpiracaoDaConta) {
        this.dataExpiracaoDaConta = dataExpiracaoDaConta;
    }

    public ContaCorrente getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(ContaCorrente contaCorrente) {
        this.contaCorrente = contaCorrente;
    }
}
