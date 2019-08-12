package com.previdencia.cadastro.dto;

import com.previdencia.cadastro.model.entity.Usuario;
import lombok.*;

import java.time.LocalDate;

import static java.util.Objects.nonNull;
import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {

    private Long id;
    private String nomeCompleto;
    private String cpf;
    private String sexo;
    private Integer idade;
    private String email;
    private LocalDate dataNascimento;
    private Long contaComum;
    private Long contaEventual;
    private String telefone;

    public UsuarioDTO(Usuario usuario){
        this.id = usuario.getId();
        this.nomeCompleto = usuario.getNomeCompleto();
        this.cpf = usuario.getCpf();
        this.sexo = usuario.getSexo();
        this.idade = usuario.getIdade();
        this.email = usuario.getEmail();
        this.dataNascimento = usuario.getDataNascimento();
        //USUÁRIO SÓ PODE TER UM TELEFONE (AINDA NÃO IMPLEMENTADO)
        this.telefone = isNotEmpty(usuario.getUsuarioTelefone()) ? usuario.getUsuarioTelefone().get(0).getTelefone() : "";
    }
}
