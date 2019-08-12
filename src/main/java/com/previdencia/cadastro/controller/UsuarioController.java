package com.previdencia.cadastro.controller;


import com.previdencia.cadastro.dto.*;
import com.previdencia.cadastro.model.entity.Usuario;
import com.previdencia.cadastro.service.ContaService;
import com.previdencia.cadastro.service.EnderecoService;
import com.previdencia.cadastro.service.TelefoneService;
import com.previdencia.cadastro.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController()
@Api(tags = "Usuario")
@RequestMapping("/usuario")
public class UsuarioController implements BaseAPI {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private TelefoneService telefoneService;
    @Autowired
    private ContaService contaService;

    @ApiOperation(value = "Cadastrar um usuário")
    @PostMapping
    public IdDTO cadastrar(@RequestBody @Valid NovoUsuarioDTO novoUsuarioDTO, BindingResult bindingResult) {
        return usuarioService.cadastrar(novoUsuarioDTO, bindingResult);
    }

    @ApiOperation(value = "Obter usuário")
    @GetMapping("{id}")
    public UsuarioDTO obter(@PathVariable Long id){
        return usuarioService.obterUsuario(id);
    }

    @ApiOperation(value = "Obter todos os usuários")
    @GetMapping
    public PaginadoDTO<UsuarioDTO> obterTodos(@RequestParam(value = "pagina", defaultValue = "0") int pagina,
                                       @RequestParam(value = "maximo", defaultValue = "30") int maximo,
                                       @RequestParam(value = "ordenacao", defaultValue = "id") String ordenacao,
                                       @RequestParam(value = "direcao", defaultValue = "ASC") Sort.Direction direcao){
        PageRequest pageRequest = PageRequest.of(pagina, maximo, new Sort(direcao, ordenacao));
        return mapToDTO(usuarioService.obterTodosOsUsuarios(pageRequest));
    }

    @ApiOperation(value = "Obter endereços")
    @GetMapping("{id}/endereco")
    public EnderecoDTO obterEndereco(@PathVariable Long id){
        return enderecoService.obterEnderecoPorUsuario(id);
    }

    @ApiOperation(value = "Obter telefones")
    @GetMapping("{id}/telefone")
    public TelefoneDTO obterTelefone(@PathVariable Long id){
        return telefoneService.obterTelefonePorUsuario(id);
    }

    @ApiOperation(value = "Obter contas")
    @GetMapping("{id}/contas")
    public ContaDTO obterContas(@PathVariable Long id){
        return contaService.informacoesDasContasPorUsuario(id);
    }

    public PaginadoDTO<UsuarioDTO> mapToDTO(Page<Usuario> paginas) {
        List<UsuarioDTO> usuarioDtos = paginas
                .stream()
                .map(UsuarioDTO::new)
                .collect(Collectors.toList());
        return new PaginadoDTO<>(usuarioDtos, paginas.getTotalElements(), paginas.getNumber(), paginas.getNumberOfElements());
    }


}
