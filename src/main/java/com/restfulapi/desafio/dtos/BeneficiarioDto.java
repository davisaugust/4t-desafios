package com.restfulapi.desafio.dtos;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;
import com.restfulapi.desafio.enums.Status;

import jakarta.validation.constraints.Pattern;

public record BeneficiarioDto(
    UUID id,
    String nome_completo,
    @Pattern(regexp = "\\d{11}")
    String cpf,
    Date data_nascimento,
    Status status,
    UUID plano_id,          
    LocalDateTime data_cadastro
) {}