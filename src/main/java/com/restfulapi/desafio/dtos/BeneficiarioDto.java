package com.restfulapi.desafio.dtos;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;
import com.restfulapi.desafio.enums.Status;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

public record BeneficiarioDto(
    UUID id,
    @Schema(example = "Charles Darwin")
    String nome_completo,
    @Schema(example = "12345678909")
    @Pattern(regexp = "\\d{11}")
    String cpf,
    @Schema(example = "Data de nascimento do beneficiário")
    Date data_nascimento,
    Status status,
    @Schema(example = "Id do plano a se anexar naquele beneficiário")
    UUID plano_id,          
    LocalDateTime data_cadastro
) {}