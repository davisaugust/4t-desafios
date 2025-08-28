package com.restfulapi.desafio.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "plano")

public class Plano {

    @Id
    @org.hibernate.annotations.UuidGenerator
    private UUID id;
    private String nome;

    private String codigo_registro_ans;
    
    public Plano(){

    }

    public Plano(UUID id, String nome, String codigo_registro_ans){
        this.id = id;
        this.nome = nome;
        this.codigo_registro_ans = codigo_registro_ans;
    }

    public UUID getId(){
        return id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCodigo_registro_ans(){
        return codigo_registro_ans;
    }

    public void setCodigo_registro_ans(String codigo_registro_ans){
        this.codigo_registro_ans = codigo_registro_ans; 
    }
}
