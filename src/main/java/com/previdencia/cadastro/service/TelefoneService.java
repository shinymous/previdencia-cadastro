package com.previdencia.cadastro.service;

import com.previdencia.cadastro.dto.TelefoneDTO;
import com.previdencia.cadastro.exception.EntidadeNaoEcontradaException;
import com.previdencia.cadastro.exception.UsuarioValidacaoException;
import com.previdencia.cadastro.model.entity.UsuarioTelefone;
import com.previdencia.cadastro.model.repository.UsuarioTelefoneRepository;
import com.previdencia.cadastro.util.UtilTelefone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
public class TelefoneService {

    @Autowired
    UsuarioTelefoneRepository usuarioTelefoneRepository;

    public TelefoneDTO obterTelefonePorUsuario(Long id){
        Optional<UsuarioTelefone> usuarioTelefoneOpt = usuarioTelefoneRepository.findByUsuarioId(id);
        if(usuarioTelefoneOpt.isPresent()){
            return new TelefoneDTO(usuarioTelefoneOpt.get());
        }
        return null;
    }


    public TelefoneDTO alterarTelefone(Long id, String telefone){
        if(!UtilTelefone.isTelefone(telefone)){
            throw new UsuarioValidacaoException("Telefone inválido");
        }
        Optional<UsuarioTelefone> usuarioTelefoneOpt = usuarioTelefoneRepository.findById(id);
        if(!usuarioTelefoneOpt.isPresent()){
            throw new EntidadeNaoEcontradaException("Telefone", id);
        }
        UsuarioTelefone usuarioTelefone = usuarioTelefoneOpt.get();
        usuarioTelefone.setTelefone(telefone);
        return new TelefoneDTO(usuarioTelefoneRepository.save(usuarioTelefone));
    }
}
