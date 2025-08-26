package com.restfulapi.desafio.exceptions;

public class BeneficiarioNotFound extends RuntimeException{
    
    public BeneficiarioNotFound(){super("O beneficiario não se encontra no banco de dados");}

    public BeneficiarioNotFound(String message){super(message);}

}
