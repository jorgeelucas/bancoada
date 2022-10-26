package br.com.ada.dto;

import br.com.ada.entidade.ContaCorrente;

import java.math.BigDecimal;

public class ContaCorrenteDTO {

    private BigDecimal saldo;

    public ContaCorrenteDTO() {
    }

    public ContaCorrenteDTO(ContaCorrente cc) {
        this.saldo = cc.getSaldo();
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
