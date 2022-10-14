package br.com.ada.dto;

import br.com.ada.entidade.Usuario;

public class GetUsuarioDTO {
    private String nome;
    private String email;

    public GetUsuarioDTO(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
