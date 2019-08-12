package com.previdencia.cadastro.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.previdencia.cadastro.dto.NovoEnderecoDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static com.mysql.cj.core.util.StringUtils.safeTrim;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UsuarioEndereco {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private String bairro;
    private String cep;
    private String complemento;
    private String uf;

    @JsonIgnore
    @JoinColumn(name = "usuario", referencedColumnName = "id", updatable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private Usuario usuario;

    public UsuarioEndereco(NovoEnderecoDTO novoEnderecoDTO){
        this.logradouro = safeTrim(novoEnderecoDTO.getLogradouro());
        this.bairro = safeTrim(novoEnderecoDTO.getBairro());
        this.cep = safeTrim(novoEnderecoDTO.getCep());
        this.complemento = safeTrim(novoEnderecoDTO.getComplemento());
        this.uf = safeTrim(novoEnderecoDTO.getUf());
    }

}
