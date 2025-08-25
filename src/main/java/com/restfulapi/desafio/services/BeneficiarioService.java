package com.restfulapi.desafio.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restfulapi.desafio.model.Beneficiario;
import com.restfulapi.desafio.model.Plano;
import com.restfulapi.desafio.repositories.BeneficiarioRepository;
import com.restfulapi.desafio.repositories.PlanoRepository;
import com.restfulapi.desafio.dtos.BeneficiarioDto;

@Service
public class BeneficiarioService {
    
    @Autowired
    BeneficiarioRepository beneficiarioRepository;

    @Autowired
    PlanoRepository planoRepository;

    public List<Beneficiario> getAll(){
        return beneficiarioRepository.findAll();
    }

    public Optional<Beneficiario> getById(UUID id){
        return beneficiarioRepository.findById(id);
    }

    public Beneficiario save(BeneficiarioDto dto) {
    var beneficiario = new Beneficiario();

    BeanUtils.copyProperties(dto, beneficiario, "plano");

    if (dto.status() != null) {
        beneficiario.setStatus(dto.status());
    }

    if (dto.data_cadastro() != null) {
        beneficiario.setData_cadastro(dto.data_cadastro());
    }

    // Beneficiario existente = beneficiarioRepository.findByCpf(dto.cpf());
    // if (existente != null) {
    //     throw new RuntimeException("CPF duplicado");
    // }

    Plano plano = planoRepository.findById(dto.plano_id())
            .orElseThrow(() -> new RuntimeException("Plano não encontrado"));

    beneficiario.setPlano(plano);

    return beneficiarioRepository.save(beneficiario);
}


    public void delete(UUID id){
        Beneficiario beneficiario = beneficiarioRepository.findById(id).orElseThrow(() -> new RuntimeException("f"));
        beneficiarioRepository.delete(beneficiario);
    }

    public Beneficiario update(UUID id, BeneficiarioDto dto) {
        Beneficiario beneficiario = beneficiarioRepository.findById(id).orElseThrow(() -> new RuntimeException("CPF repetido"));
        
        BeanUtils.copyProperties(dto, beneficiario, "id", "data_cadastro", "plano", "status");
        

        if (dto.status() != null) {
            beneficiario.setStatus(dto.status());
        }

        if (dto.data_cadastro() != null) {
            beneficiario.setData_cadastro(dto.data_cadastro());
        }

        if (dto.plano_id() != null) {
            Plano plano = planoRepository.findById(dto.plano_id())
                    .orElseThrow(() -> new RuntimeException("Plano informado não existe"));
            beneficiario.setPlano(plano);
        }

        return beneficiarioRepository.save(beneficiario);
        
    }


}
