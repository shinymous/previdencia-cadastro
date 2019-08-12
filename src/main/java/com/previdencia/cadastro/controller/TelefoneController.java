package com.previdencia.cadastro.controller;

import com.previdencia.cadastro.service.TelefoneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController()
@Api(tags = "Telefone")
@RequestMapping("/telefone")
public class TelefoneController implements BaseAPI {

    @Autowired
    private TelefoneService telefoneService;

    @ApiOperation(value = "Alterar telefone")
    @PutMapping("{id}")
    public ResponseEntity<?> obterTelefone(@PathVariable Long id, @RequestBody String telefone){
        return new ResponseEntity<>(telefoneService.alterarTelefone(id, telefone), HttpStatus.ACCEPTED);
    }
}
