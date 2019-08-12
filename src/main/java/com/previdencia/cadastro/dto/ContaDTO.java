package com.previdencia.cadastro.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContaDTO {

    private ContaInfoDTO contaComum;
    private ContaInfoDTO contaEventual;

    public ContaDTO(ContaInfoDTO contaComum, ContaInfoDTO contaEventual){
        this.contaComum = contaComum;
        this.contaEventual = contaEventual;
    }
}
