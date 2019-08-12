package com.previdencia.cadastro.dto;

import com.previdencia.cadastro.model.entity.UsuarioTelefone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TelefoneDTO {

    private Long id;
    private String telefone;

    public TelefoneDTO(UsuarioTelefone usuarioTelefone){
        this.id = usuarioTelefone.getId();
        this.telefone = usuarioTelefone.getTelefone();
    }
}
