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
public class AcaoDTO {

    private BigDecimal valor;
    private String tipo;
    private LocalDateTime data;


    public AcaoDTO(Saque saque){
        this.valor = saque.getValor();
        this.tipo = saque.getConta().getContaTipo();
        this.data = saque.getData();
    }

    public AcaoDTO(Deposito deposito){
        this.valor = deposito.getValor();
        this.tipo = deposito.getConta().getContaTipo();
        this.data = deposito.getData();
    }

}
