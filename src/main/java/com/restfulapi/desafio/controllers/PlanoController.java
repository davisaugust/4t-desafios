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
import com.restfulapi.desafio.model.Plano;
import com.restfulapi.desafio.repositories.PlanoRepository;
import com.restfulapi.desafio.services.PlanoService;
import com.restfulapi.desafio.dtos.PlanoDto;
import com.restfulapi.desafio.exceptions.PlanoNotFound;

@RestController
@RequestMapping("/planos")
public class PlanoController {

    @Autowired
    PlanoRepository planoRepository;

    @Autowired
    PlanoService planoService;

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(planoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plano> getById(@PathVariable UUID id) {
        Plano plano = planoService.getById(id);
        return ResponseEntity.ok(plano);
    }  

    @PostMapping
    public ResponseEntity save(@RequestBody PlanoDto dto){
        try{
            Plano saved = planoService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Post não funcionou.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        try{
            planoService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Beneficiario deletado");
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Beneficiario não encontrado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable UUID id, @RequestBody PlanoDto dto) {
        try {
            Plano updated = planoService.update(id, dto);   
            return ResponseEntity.status(HttpStatus.OK).body(updated);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não deu pra atualizar");
        }
    }
}
