package com.previdencia.cadastro.dto;

import com.previdencia.cadastro.model.entity.Deposito;
import com.previdencia.cadastro.model.entity.Saque;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ContaAcaoDTO {

    private BigDecimal valor;
    private LocalDateTime data;
    private BigDecimal saldoAtual;

    public ContaAcaoDTO(Saque saque, BigDecimal saldoAtual){
        this.valor = saque.getValor();
        this.data = saque.getData();
        this.saldoAtual = saldoAtual;
    }

    public ContaAcaoDTO(Deposito deposito, BigDecimal saldoAtual){
        this.valor = deposito.getValor();
        this.data = deposito.getData();
        this.saldoAtual = saldoAtual;
    }
}
