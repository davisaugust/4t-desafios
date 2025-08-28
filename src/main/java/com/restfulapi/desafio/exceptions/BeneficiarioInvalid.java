package com.restfulapi.desafio.exceptions;

public class BeneficiarioInvalid extends RuntimeException {
    public BeneficiarioInvalid(){super("O id do beneficiário fornecido não está no formato UUID.");}  
    
    public BeneficiarioInvalid(String message){super(message);}
}
