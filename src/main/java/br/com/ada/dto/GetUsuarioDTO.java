package br.com.ada.dto;

import br.com.ada.entidade.Usuario;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class GetUsuarioDTO {
    private String nome;
    private String email;
    private ContaCorrenteDTO contaCorrente;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
    private LocalDateTime dataExpiracao;

    public GetUsuarioDTO(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.contaCorrente = new ContaCorrenteDTO(usuario.getContaCorrente());
        this.dataCadastro = usuario.getDataCadastro();
        this.dataAtualizacao = getDataFromLong(usuario.getDataAtualizacao());
        this.dataExpiracao = usuario.getDataExpiracaoDaConta();
    }

    private LocalDateTime getDataFromLong(Long dataAtualizacao) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(dataAtualizacao),
                        TimeZone.getDefault().toZoneId());
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public LocalDateTime getDataExpiracao() {
        return dataExpiracao;
    }

    public ContaCorrenteDTO getContaCorrente() {
        return contaCorrente;
    }
}
