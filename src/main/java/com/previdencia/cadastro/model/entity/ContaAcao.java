package com.previdencia.cadastro.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class ContaAcao {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    private LocalDateTime data;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta", referencedColumnName = "id", insertable = false, updatable = false)
    private Conta conta;
    @Column(name = "conta")
    private Long contaId;
}
