package com.restfulapi.desafio.exceptions;

public class PlanoAtrelado extends RuntimeException {
    public PlanoAtrelado(){super("Não foi possível excluir o plano, pois existe um beneficiário atrelado a ele.");}


    public PlanoAtrelado(String message){super(message);}
}
