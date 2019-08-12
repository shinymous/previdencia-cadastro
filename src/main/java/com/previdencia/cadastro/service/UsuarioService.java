package com.previdencia.cadastro.service;

import com.previdencia.cadastro.dto.IdDTO;
import com.previdencia.cadastro.dto.NovoUsuarioDTO;
import com.previdencia.cadastro.dto.UsuarioDTO;
import com.previdencia.cadastro.exception.UsuarioNaoEncontradoException;
import com.previdencia.cadastro.exception.UsuarioValidacaoException;
import com.previdencia.cadastro.model.entity.Conta;
import com.previdencia.cadastro.model.entity.Usuario;
import com.previdencia.cadastro.model.entity.UsuarioEndereco;
import com.previdencia.cadastro.model.entity.UsuarioTelefone;
import com.previdencia.cadastro.model.repository.*;
import com.previdencia.cadastro.util.Constantes;
import com.previdencia.cadastro.util.UtilCpf;
import com.previdencia.cadastro.util.UtilTelefone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;

import static com.mysql.cj.core.util.StringUtils.safeTrim;
import static com.previdencia.cadastro.util.UtilEmail.isEmailValido;

@Service
public class UsuarioService {

    @Autowired
    private UfRepository ufRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioEnderecoRepository usuarioEnderecoRepository;
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private UsuarioTelefoneRepository usuarioTelefoneRepository;
    @Autowired
    private ContaRepository contaRepository;

    @Transactional
    public IdDTO cadastrar(NovoUsuarioDTO novoUsuarioDTO, BindingResult bindingResult){
        validarGeral(novoUsuarioDTO, bindingResult);
        validarCpf(novoUsuarioDTO.getCpf());
        validarTelefone(novoUsuarioDTO.getTelefone());
        ajustaDados(novoUsuarioDTO);

        Usuario usuario = usuarioRepository.save(novoUsuarioDTO.toUsuario());
        UsuarioEndereco usuarioEndereco = new UsuarioEndereco(novoUsuarioDTO.getEndereco());
        usuarioEndereco.setUsuario(usuario);
        usuarioEnderecoRepository.save(usuarioEndereco);
        UsuarioTelefone usuarioTelefone = new UsuarioTelefone();
        usuarioTelefone.setTelefone(novoUsuarioDTO.getTelefone());
        usuarioTelefone.setUsuario(usuario);
        usuarioTelefoneRepository.save(usuarioTelefone);
        contaRepository.saveAll(Arrays.asList(new Conta(usuario, Constantes.CONTA_COMUM), new Conta(usuario, Constantes.CONTA_EVENTUAL)));
        return new IdDTO(usuario.getId());
    }

    public UsuarioDTO obterUsuario(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
        UsuarioDTO usuarioDTO = usuario.toUsuarioDTO();
        usuarioDTO.setContaComum(contaRepository.findContaComumByUsuarioId(usuarioDTO.getId()).orElse(null));
        usuarioDTO.setContaEventual(contaRepository.findContaEventualByUsuarioId(usuarioDTO.getId()).orElse(null));
        return usuarioDTO;
    }

    public Page<Usuario> obterTodosOsUsuarios(PageRequest pageRequest){
        return usuarioRepository.findAll(pageRequest);
    }


    private void validarCpf(String cpf){
        if(!UtilCpf.validaCpf(cpf)){
         throw new UsuarioValidacaoException("Cpf inválido");
        }
    }

    private void validarTelefone(String telefone){
        if(!UtilTelefone.isTelefone(telefone)){
            throw new UsuarioValidacaoException("Telefone inválido");
        }
    }

    public void montaBindingResultErros(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder message = new StringBuilder();
            //MONTA LISTA DE ERROS
            for(Iterator<ObjectError> i = bindingResult.getAllErrors().iterator(); i.hasNext();){
                message.append(i.next().getDefaultMessage());
                if(i.hasNext()){
                    message.append(" | ");
                }
            }
            throw new UsuarioValidacaoException(message.toString());
        }
    }

    private void validarGeral(NovoUsuarioDTO novoUsuarioDTO, BindingResult bindingResult){
        //VALIDA SE A ENTRADA DE DADOS DEFINIDA NOS DTOS
        montaBindingResultErros(bindingResult);
        //VALIDA O VALOR DIGITADO NO CAMPO DE SEXO
        novoUsuarioDTO.setSexo(safeTrim(novoUsuarioDTO.getSexo()));
        if(!novoUsuarioDTO.getSexo().equalsIgnoreCase(Constantes.SEXO_FEMININO.toString())
                && !novoUsuarioDTO.getSexo().equalsIgnoreCase(Constantes.SEXO_FEMININO_DESCRICAO)
                && !novoUsuarioDTO.getSexo().equalsIgnoreCase(Constantes.SEXO_MASCULINO_DESCRICAO)
                && !novoUsuarioDTO.getSexo().equalsIgnoreCase(Constantes.SEXO_MASCULINO.toString())){
            throw new UsuarioValidacaoException("Sexo inválido");
        }
        enderecoService.validaEndereco(novoUsuarioDTO.getEndereco());
        //VALIDA EMAIL
        if(!isEmailValido(novoUsuarioDTO.getEmail())){
            throw new UsuarioValidacaoException("Email inválido");
        }

        //VALIDA SE DATA DE NASCIMENTO É MENOR QUE DATA ATUAL
        if(novoUsuarioDTO.getDataNascimento().compareTo(LocalDate.now()) >= 0){
            throw new UsuarioValidacaoException("Data de nascimento inválida");
        }
    }

    private void ajustaDados(NovoUsuarioDTO novoUsuarioDTO){
        switch (safeTrim(novoUsuarioDTO.getSexo().toUpperCase())){
            case "M": novoUsuarioDTO.setSexo(Constantes.SEXO_MASCULINO_DESCRICAO);
                break;
            case "F": novoUsuarioDTO.setSexo(Constantes.SEXO_FEMININO_DESCRICAO);
                break;
        }
        novoUsuarioDTO.setCpf(UtilCpf.ajustaCpf(novoUsuarioDTO.getCpf()));

        novoUsuarioDTO.getEndereco().setUf(ufRepository.findByDescricaoOuIniciais(novoUsuarioDTO.getEndereco().getUf()).get().getIniciais());
    }
}
