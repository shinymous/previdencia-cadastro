package com.previdencia.cadastro.controller;

import com.previdencia.cadastro.dto.ContaAcaoDTO;
import com.previdencia.cadastro.service.ContaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@CrossOrigin
@RestController()
@Api(tags = "Conta")
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @ApiOperation("Realizar depósito")
    @PostMapping("{id}/deposito")
    public ContaAcaoDTO depositar(@PathVariable Long id, @RequestBody BigDecimal valor){
        return contaService.depositar(id, valor);
    }

    @ApiOperation("Realizar saque")
    @PostMapping("{id}/saque")
    public ContaAcaoDTO ContaAcaoDTOsaque(@PathVariable Long id, @RequestBody BigDecimal valor){
        return contaService.sacar(id, valor);
    }

}
