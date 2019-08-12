package com.previdencia.cadastro.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ContaInfoDTO {

    private List<AcaoDTO> historico;
    private BigDecimal saldo;

}
