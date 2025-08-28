package com.restfulapi.desafio.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.restfulapi.desafio.enums.Status;
import com.restfulapi.desafio.model.Beneficiario;

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, UUID> {
    boolean existsByCpf(String cpf);
    boolean existsByPlanoId(UUID planoId);
    List<Beneficiario> findByStatusAndPlanoId(Status status, UUID id);
}
