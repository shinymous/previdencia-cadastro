package com.previdencia.cadastro.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    private Usuario usuario;
    private String contaTipo;

    public Conta(Usuario usuario, String contaTipo){
        this.usuario = usuario;
        this.contaTipo = contaTipo;
    }

}
