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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.restfulapi.desafio.dtos.PlanoDto;

@RestController
@RequestMapping("/planos")
@Tag(name = "01 - Planos", description = "Gerenciador de planos")
public class PlanoController {

    @Autowired
    PlanoRepository planoRepository;

    @Autowired
    PlanoService planoService;

    @GetMapping
    @Operation(summary = "Recebe todos os planos.", description = "Puxa todos planos armazenados no banco de dados." ) 
    public ResponseEntity getAll(){
        return ResponseEntity.ok(planoService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recebe um plano por id.", description = "Puxa um plano do banco de dados especificando pelo ID do plano.") 
    public ResponseEntity<Plano> getById(@PathVariable UUID id) {
        Plano plano = planoService.getById(id);
        return ResponseEntity.ok(plano);
    }  

    @PostMapping
    @Operation(summary = "Cria um plano.", description = "Cria um plano novo no banco de dados.") 
    public ResponseEntity save(@RequestBody PlanoDto dto){
        Plano planoSalvo = planoService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(planoSalvo);
        
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um plano por id.", description = "Deleta um plano do banco de dados especificando pelo id do plano.") 
    public ResponseEntity delete(@PathVariable UUID id){
        planoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Plano deletado");
        
    }

    @PutMapping("/{id}")
    @Operation(summary = "Altera um plano por id.", description = "Altera as características de um plano especificando pelo id do plano.") 
    public ResponseEntity update(@PathVariable UUID id, @RequestBody PlanoDto dto) {
        Plano updated = planoService.update(id, dto);   
        return ResponseEntity.status(HttpStatus.OK).body(updated);
        
    }
}
