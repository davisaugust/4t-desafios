package com.restfulapi.desafio.exceptions;

public class PlanoConflict extends RuntimeException{
    public PlanoConflict(){super("O id do plano que você forneceu já está presente no banco de dados.");}
    public PlanoConflict(String message){super(message);}
}
