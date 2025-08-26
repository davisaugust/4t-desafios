package com.restfulapi.desafio.exceptions;

public class CpfInvalid extends RuntimeException{
    public CpfInvalid(){super("O CPF que você inseriu não possui 11 digitos");}

    public CpfInvalid(String message){super(message);}
}
