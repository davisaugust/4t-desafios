package com.restfulapi.desafio.exceptions;

public class PlanoInvalid extends RuntimeException{
    public PlanoInvalid(){super("O plano que você inseriu não possúi um id válido");}

    public PlanoInvalid(String message){super(message);}
}
