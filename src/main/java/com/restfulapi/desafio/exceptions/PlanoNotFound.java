package com.restfulapi.desafio.exceptions;

public class PlanoNotFound extends RuntimeException{
    
    public PlanoNotFound(){super ("O Plano descrito não foi encontado no banco de dados.");}

    public PlanoNotFound(String message){super(message);}
}
