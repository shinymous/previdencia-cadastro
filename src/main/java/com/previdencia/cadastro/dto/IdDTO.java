package com.previdencia.cadastro.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IdDTO {

    private Long idGerado;

    public IdDTO(Long idGerado){
        this.idGerado = idGerado;
    }
}
