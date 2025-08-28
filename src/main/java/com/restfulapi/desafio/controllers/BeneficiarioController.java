package com.restfulapi.desafio.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restfulapi.desafio.model.Beneficiario;
import com.restfulapi.desafio.services.BeneficiarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.restfulapi.desafio.dtos.BeneficiarioDto;

@RestController
@RequestMapping("/beneficiarios")
@Tag(name = "02 - Beneficiarios", description = "Gerenciador de beneficiários")
public class BeneficiarioController {
    
    @Autowired
    BeneficiarioService beneficiarioService;

    @GetMapping
    @Operation(summary = "Recebe todos os beneficiários.", description = "Puxa todos os beneficiários do banco de dados.") 
    public ResponseEntity<List<Beneficiario>> getAll(){
        return ResponseEntity.ok(beneficiarioService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recebe um beneficiário por id.", description = "Puxa um beneficiário do banco de dados especificando pelo ID do beneficiário.") 
    public ResponseEntity<Beneficiario> getById(@PathVariable UUID id){
        Beneficiario beneficiario = beneficiarioService.getById(id);
        return ResponseEntity.ok(beneficiario);
    }

    @PostMapping
    @Operation(summary = "Cadastra um beneficiário.", description = "Cadastra um novo beneficiário que já não esteja cadastrado no banco de dados.") 
    public ResponseEntity<Beneficiario> save(@RequestBody BeneficiarioDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(beneficiarioService.save(dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um beneficiário por id.", description = "Deleta um beneficiário do banco de dados. Precisa ser especificado pelo id do beneficiário." ) 
    public ResponseEntity<String> delete(@PathVariable UUID id){
        beneficiarioService.delete(id);
        return ResponseEntity.ok("Beneficiário deletado.");
        
    }

    @PutMapping("/{id}")
    @Operation(summary = "Altera as informações de um beneficiário por id.", description = "Te possibilita alterar todas as características do beneficiário. Precisa ser especificiado pelo ID do beneficiário." ) 
    public ResponseEntity<Beneficiario> update(@PathVariable UUID id, @RequestBody BeneficiarioDto dto) {
        Beneficiario updated = beneficiarioService.update(id, dto);   
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }    

}
