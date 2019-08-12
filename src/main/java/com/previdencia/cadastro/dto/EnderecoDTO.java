package com.previdencia.cadastro.dto;

import com.previdencia.cadastro.model.entity.UsuarioEndereco;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EnderecoDTO {

    private Long id;
    private String logradouro;
    private String bairro;
    private String cep;
    private String complemento;
    private String uf;

    public EnderecoDTO(UsuarioEndereco endereco){
        this.id = endereco.getId();
        this.logradouro = endereco.getLogradouro();
        this.bairro = endereco.getBairro();
        this.cep = endereco.getCep();
        this.complemento = endereco.getComplemento();
        this.uf = endereco.getUf();
    }
}
