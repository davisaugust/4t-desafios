package com.restfulapi.desafio.exceptions;

public class CpfIsntNumber extends RuntimeException {
    public CpfIsntNumber(){super("O CPF não pode conter letras");}
    public CpfIsntNumber(String message){super(message);}
}
