package com.restfulapi.desafio.controllers;

import java.util.UUID;

import org.apache.catalina.connector.Response;
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
import com.restfulapi.desafio.dtos.BeneficiarioDto;

@RestController
@RequestMapping("/beneficiarios")
public class BeneficiarioController {
    
    @Autowired
    BeneficiarioService beneficiarioService;

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(beneficiarioService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable UUID id){
        Beneficiario beneficiario = beneficiarioService.getById(id);
        return ResponseEntity.ok(beneficiario);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody BeneficiarioDto dto){
        Beneficiario saved = beneficiarioService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        beneficiarioService.delete(id);
        return ResponseEntity.ok("Beneficiário deletado.");
        
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable UUID id, @RequestBody BeneficiarioDto dto) {
        try {
            Beneficiario updated = beneficiarioService.update(id, dto);   
            return ResponseEntity.status(HttpStatus.OK).body(updated);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O ID do beneficiário específicado não existe no banco de dados");
        }
    }    

}
