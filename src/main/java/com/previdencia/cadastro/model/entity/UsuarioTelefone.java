package com.previdencia.cadastro.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
//NÃO TIVE TEMPO DE IMPLEMENTAR A OPÇÃO DE TER VÁRIOS TELEFONES, PORÉM DEIXEI MAPEADO COMO UMA POSSIBILIDADE FUTURA
public class UsuarioTelefone {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @JoinColumn(name = "usuario", referencedColumnName = "id", updatable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private Usuario usuario;
    private String telefone;
}
