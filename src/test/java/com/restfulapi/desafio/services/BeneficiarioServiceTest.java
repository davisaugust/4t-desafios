package com.restfulapi.desafio.services;

import com.restfulapi.desafio.dtos.BeneficiarioDto;
import com.restfulapi.desafio.enums.Status;
import com.restfulapi.desafio.exceptions.CpfConflict;
import com.restfulapi.desafio.exceptions.CpfInvalid;
import com.restfulapi.desafio.exceptions.PlanoNotFound;
import com.restfulapi.desafio.model.Beneficiario;
import com.restfulapi.desafio.model.Plano;
import com.restfulapi.desafio.repositories.BeneficiarioRepository;
import com.restfulapi.desafio.repositories.PlanoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BeneficiarioServiceTest {

    @Mock
    private BeneficiarioRepository beneficiarioRepository;

    @Mock
    private PlanoRepository planoRepository;

    @InjectMocks
    private BeneficiarioService beneficiarioService;

    private Plano plano;
    private UUID idPlano;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        idPlano = UUID.randomUUID();
        plano = new Plano();
        plano.setId(idPlano);
        plano.setNome("Plano Básico");
    }

    private BeneficiarioDto criarDto(String cpf) {
        return new BeneficiarioDto(
                UUID.randomUUID(),
                "Maria Souza",
                cpf,
                new Date(System.currentTimeMillis()),
                Status.ATIVO,
                plano.getId(),
                LocalDateTime.now()
        );
    }

    @Test
    void CpfDuplicado() {
        BeneficiarioDto dto = criarDto("12345678901");

        when(beneficiarioRepository.existsByCpf(dto.cpf())).thenReturn(true);

        assertThrows(CpfConflict.class, () -> beneficiarioService.save(dto));
    }

    @Test
    void CpfInvalido() {
        BeneficiarioDto dto = criarDto("123"); // CPF com menos de 11 dígitos

        assertThrows(CpfInvalid.class, () -> beneficiarioService.save(dto));
    }

    @Test
    void PlanonotFound() {
        BeneficiarioDto dto = criarDto("12345678901");

        when(beneficiarioRepository.existsByCpf(dto.cpf())).thenReturn(false);
        when(planoRepository.findById(dto.plano_id())).thenReturn(Optional.empty());

        assertThrows(PlanoNotFound.class, () -> beneficiarioService.save(dto));
    }

    @Test
    void StatusInativo() {
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setId(UUID.randomUUID());
        beneficiario.setStatus(Status.ATIVO);

        when(beneficiarioRepository.findById(beneficiario.getId())).thenReturn(Optional.of(beneficiario));
        when(beneficiarioRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        beneficiario.setStatus(Status.INATIVO);
        Beneficiario atualizado = beneficiarioRepository.save(beneficiario);

        assertEquals(Status.INATIVO, atualizado.getStatus());
    }

    @Test
    void StatusePlanosList() {
        when(beneficiarioRepository.findByStatusAndPlanoId(Status.ATIVO, idPlano))
                .thenReturn(List.of(new Beneficiario()));

        List<Beneficiario> lista = beneficiarioRepository.findByStatusAndPlanoId(Status.ATIVO, idPlano);

        assertFalse(lista.isEmpty());
    }
}

