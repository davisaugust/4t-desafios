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
import com.restfulapi.desafio.exceptions.BeneficiarioNotFound;
import com.restfulapi.desafio.exceptions.CpfConflict;
import com.restfulapi.desafio.exceptions.CpfInvalid;
import com.restfulapi.desafio.exceptions.PlanoConflict;
import com.restfulapi.desafio.exceptions.PlanoInvalid;
import com.restfulapi.desafio.exceptions.PlanoNotFound;

@Service
public class BeneficiarioService {
    
    @Autowired
    BeneficiarioRepository beneficiarioRepository;

    @Autowired
    PlanoRepository planoRepository;

    public List<Beneficiario> getAll(){
        return beneficiarioRepository.findAll();
    }

    public Beneficiario getById(UUID id){
        return beneficiarioRepository.findById(id).orElseThrow(()-> new BeneficiarioNotFound());
    }

    public Beneficiario save(BeneficiarioDto dto) {
        var beneficiario = new Beneficiario();
        String cpf = dto.cpf();

        BeanUtils.copyProperties(dto, beneficiario, "plano");

        if (dto.status() != null) {
            beneficiario.setStatus(dto.status());
        }

        if (dto.data_cadastro() != null) {
            beneficiario.setData_cadastro(dto.data_cadastro());
        }
    
        if(beneficiarioRepository.existsByCpf(dto.cpf())){
            throw new CpfConflict();
        }

        if(cpf.length() != 11 ){
            throw new CpfInvalid();
        }

        if(beneficiarioRepository.existsByPlanoId(dto.plano_id())){
            throw new PlanoConflict();
        }

        Plano plano = planoRepository.findById(dto.plano_id())
                .orElseThrow(() -> new PlanoNotFound());

        beneficiario.setPlano(plano);
        

        return beneficiarioRepository.save(beneficiario);
    }


    public void delete(UUID id){
        
        Beneficiario beneficiario = beneficiarioRepository.findById(id).orElseThrow(() -> new BeneficiarioNotFound());

        beneficiarioRepository.delete(beneficiario);
        
    }

    public Beneficiario update(UUID id, BeneficiarioDto dto) {
        Beneficiario beneficiario = beneficiarioRepository.findById(id).orElseThrow(() -> new BeneficiarioNotFound());
        BeanUtils.copyProperties(dto, beneficiario, "id", "data_cadastro", "plano", "status");
        

        if (dto.status() != null) {
            beneficiario.setStatus(dto.status());
        }

        if (dto.data_cadastro() != null) {
            beneficiario.setData_cadastro(dto.data_cadastro());
        }

        if(beneficiarioRepository.existsByPlanoId(dto.plano_id())){
            throw new PlanoConflict();
        }

        Plano plano = planoRepository.findById(dto.plano_id())
                .orElseThrow(() -> new PlanoNotFound());

        return beneficiarioRepository.save(beneficiario);
        
    }


}
