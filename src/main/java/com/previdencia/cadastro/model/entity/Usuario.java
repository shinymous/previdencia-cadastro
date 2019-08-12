package com.previdencia.cadastro.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.previdencia.cadastro.dto.UsuarioDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nomeCompleto;
    private String cpf;
    private String sexo;
    private Integer idade;
    private String email;
    private LocalDate dataNascimento;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    List<UsuarioTelefone> usuarioTelefone;

    @Transient
    private Long contaComum;
    @Transient
    private Long contaEventual;

    public UsuarioDTO toUsuarioDTO(){
        return UsuarioDTO.builder()
                .cpf(this.cpf)
                .nomeCompleto(this.nomeCompleto)
                .sexo(this.sexo)
                .idade(this.idade)
                .email(this.email)
                .dataNascimento(this.dataNascimento)
                .contaComum(this.contaComum)
                .contaEventual(this.contaEventual)
                .build();
    }
}
