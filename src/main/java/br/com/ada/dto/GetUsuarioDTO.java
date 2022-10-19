package br.com.ada.dto;

import br.com.ada.entidade.Usuario;

import java.time.LocalDate;

public class GetUsuarioDTO {
    private String nome;
    private String email;
    private LocalDate dataCadastro;

    public GetUsuarioDTO(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.dataCadastro = usuario.getDataCadastro();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }
}
