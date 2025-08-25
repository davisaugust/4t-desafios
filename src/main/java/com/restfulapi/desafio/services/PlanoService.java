package com.restfulapi.desafio.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.restfulapi.desafio.dtos.PlanoDto;
import com.restfulapi.desafio.exceptions.PlanoNotFound;
import com.restfulapi.desafio.model.Plano;
import com.restfulapi.desafio.repositories.PlanoRepository;

@Service
public class PlanoService {
    
    @Autowired
    PlanoRepository planoRepository;

    public List<Plano> getAll(){
        return planoRepository.findAll();
    }

    public Plano getById(UUID id) {
    return planoRepository.findById(id)
            .orElseThrow(() -> new PlanoNotFound("Plano com ID " + id + " não encontrado."));
    }


    public Plano save(PlanoDto dto){
        var plano = new Plano();
        BeanUtils.copyProperties(dto, plano);

                
        return planoRepository.save(plano);    
        
    }

    public void delete(UUID id){
        Plano plano = planoRepository.findById(id).orElseThrow(() -> new RuntimeException("Plano não encontrado"));
        planoRepository.delete(plano);
    }

    public Plano update(UUID id, PlanoDto dto){

        Plano plano = planoRepository.findById(id).orElseThrow(() -> new RuntimeException("Fudeu"));

        BeanUtils.copyProperties(dto, plano, "id", "data_cadastro");

        return planoRepository.save(plano);
    }
}
