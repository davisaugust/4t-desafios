package com.restfulapi.desafio.controllers;

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
            return beneficiarioService.getById(id).map(ResponseEntity::ok).orElseThrow(()-> new RuntimeException("f"));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody BeneficiarioDto dto){
        try{
            Beneficiario saved = beneficiarioService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fudeu");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        try{
            beneficiarioService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Beneficiario deletado");
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Beneficiario não encontrado");
        }
        
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable UUID id, @RequestBody BeneficiarioDto dto) {
        try {
            Beneficiario updated = beneficiarioService.update(id, dto);   
            return ResponseEntity.status(HttpStatus.OK).body(updated);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não deu pra atualizar");
        }
    }    

}
