package com.restfulapi.desafio.exceptions;

public class CodigoCadastroAnsInvalid extends RuntimeException{
    public CodigoCadastroAnsInvalid(){super("O código inserido já está presente no banco de dados");}

    public CodigoCadastroAnsInvalid(String message){super(message);}
}
