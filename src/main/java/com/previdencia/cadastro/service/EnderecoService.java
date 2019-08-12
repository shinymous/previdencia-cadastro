package com.previdencia.cadastro.service;

import com.previdencia.cadastro.dto.EnderecoDTO;
import com.previdencia.cadastro.dto.NovoEnderecoDTO;
import com.previdencia.cadastro.exception.EntidadeNaoEcontradaException;
import com.previdencia.cadastro.exception.UsuarioValidacaoException;
import com.previdencia.cadastro.model.entity.Uf;
import com.previdencia.cadastro.model.entity.UsuarioEndereco;
import com.previdencia.cadastro.model.repository.UfRepository;
import com.previdencia.cadastro.model.repository.UsuarioEnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Iterator;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private UfRepository ufRepository;
    @Autowired
    private UsuarioEnderecoRepository usuarioEnderecoRepository;
    @Autowired
    private UsuarioService usuarioService;

    public void validaEndereco(NovoEnderecoDTO novoEnderecoDTO){
        //VALIDA ESTADO
        ufRepository.findByDescricaoOuIniciais(novoEnderecoDTO.getUf()).orElseThrow(() -> new UsuarioValidacaoException("Uf inválido"));
    }

    public EnderecoDTO obterEnderecoPorUsuario(Long id){
        Optional<UsuarioEndereco> usuarioEndereco = usuarioEnderecoRepository.findByUsuarioId(id);
        if(usuarioEndereco.isPresent()){
            return new EnderecoDTO(usuarioEndereco.get());
        }
        return null;
    }

    public Long alterarEnderecoPorId(Long id, NovoEnderecoDTO novoEnderecoDTO, BindingResult bindingResult){
        usuarioService.montaBindingResultErros(bindingResult);
        validaEndereco(novoEnderecoDTO);
        UsuarioEndereco usuarioEnderecoOpt = usuarioEnderecoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEcontradaException("endereço", id));
        UsuarioEndereco usuarioEndereco = usuarioEnderecoOpt;
        usuarioEndereco.setBairro(novoEnderecoDTO.getBairro());
        usuarioEndereco.setCep(novoEnderecoDTO.getCep());
        usuarioEndereco.setComplemento(novoEnderecoDTO.getComplemento());
        usuarioEndereco.setLogradouro(novoEnderecoDTO.getLogradouro());
        usuarioEndereco.setUf(novoEnderecoDTO.getUf());
        usuarioEnderecoRepository.save(usuarioEndereco);
        return usuarioEndereco.getId();
    }
}
