package com.previdencia.cadastro.controller;


import com.previdencia.cadastro.dto.NovoEnderecoDTO;
import com.previdencia.cadastro.service.EnderecoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController()
@Api(tags = "Endereco")
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @ApiOperation(value = "Alterar endereco")
    @PutMapping("{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @Valid @RequestBody NovoEnderecoDTO novoEnderecoDTO, BindingResult bindingResult){
        return new ResponseEntity<>(enderecoService.alterarEnderecoPorId(id, novoEnderecoDTO, bindingResult), HttpStatus.ACCEPTED);
    }
}
