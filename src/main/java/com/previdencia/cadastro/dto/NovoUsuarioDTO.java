package com.previdencia.cadastro.dto;

import com.previdencia.cadastro.model.entity.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@NoArgsConstructor
public class NovoUsuarioDTO {

    @NotNull(message = "Nome completo é obrigatório")
    @Size(max = 100, message = "Nome muito grande")
    private String nomeCompleto;
    @NotNull(message = "Cpf é obrigatório")
    @Size(max = 14, message = "Cpf inválido")
    private String cpf;
    @NotNull(message = "Sexo é obrigatório")
    @Size(max = 20, message = "Sexo está inválido")
    private String sexo;
    @NotNull(message = "Data de nascimento é obrigatório")
    private LocalDate dataNascimento;
    @NotNull(message = "Email é obrigatório")
    @Size(max = 55, message = "Email está inválido")
    private String email;
    @NotNull(message = "Telefone é obrigatório")
    @Size(max = 20, message = "Telefone é muito grande")
    private String telefone;
    private NovoEnderecoDTO endereco;

    public Usuario toUsuario(){
        return Usuario.builder()
                .nomeCompleto(this.nomeCompleto)
                .cpf(this.cpf)
                .email(this.email)
                .idade(Period.between(this.dataNascimento, LocalDate.now()).getYears())
                .sexo(this.sexo)
                .dataNascimento(this.dataNascimento)
                .build();
    }
}
