package com.restfulapi.desafio.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.restfulapi.desafio.model.Plano;

public interface PlanoRepository extends JpaRepository<Plano, UUID>{
    boolean existsById (UUID id);
}
