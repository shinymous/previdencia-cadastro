package com.previdencia.cadastro.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class NovoEnderecoDTO {

    @Size(max = 45, message = "Logradouro é muito grande")
    private String logradouro;
    @Size(max = 45, message = "Bairro é muito grande")
    private String bairro;
    @Size(min = 8, max = 9, message = "Cep inválido")
    private String cep;
    @Size(max = 45, message = "Complemento é muito grande")
    private String complemento;
    @NotNull(message = "Uf é obrigatório")
    @Size(max = 45, message = "Uf é muito grande")
    private String uf;
}
