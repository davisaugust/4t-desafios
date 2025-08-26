package com.restfulapi.desafio.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.restfulapi.desafio.model.Beneficiario;

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, UUID> {
    boolean existsByCpf(String cpf);
}
