package com.restfulapi.desafio.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;
import com.restfulapi.desafio.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;


@Entity
@Table(name = "beneficiarios")
public class Beneficiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.hibernate.annotations.UuidGenerator
    private UUID id;

    private String nome_completo;

    @Column(unique = true)
    private String cpf;

    private Date data_nascimento;

    private Status status;

    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = Status.ATIVO;
        }
        if (data_cadastro == null) {
            data_cadastro = LocalDateTime.now();
        }
    }
    
    @OneToOne
    @JoinColumn(name = "Plano_id", unique=true,nullable = false)
    private Plano plano;
    
    @Column(updatable = false)
    private LocalDateTime data_cadastro = LocalDateTime.now();

    public Beneficiario(){

    }

    public Beneficiario(UUID id, 
        String nome_completo, 
        String cpf, 
        Date data_nascimento,
        Status status, 
        Plano plano, 
        LocalDateTime data_cadastro){

        this.id = id;
        this.nome_completo = nome_completo;
        this.data_nascimento = data_nascimento;
        this.status = status;
        this.cpf = cpf;
        this.plano = plano;
        this.data_cadastro = data_cadastro;
    }

    public UUID getId() {
    return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome_completo() {
        return nome_completo;
    }

    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public LocalDateTime getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(LocalDateTime data_cadastro) {
        this.data_cadastro = data_cadastro;
    }
    
}
