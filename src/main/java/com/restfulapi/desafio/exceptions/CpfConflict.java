package com.restfulapi.desafio.exceptions;

public class CpfConflict extends RuntimeException{
    public CpfConflict(){super("O CPF que você forneceu já se encontra no nosso banco de dados");}

    public CpfConflict(String message){super(message);}
}
