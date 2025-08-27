package com.restfulapi.desafio.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.restfulapi.desafio.exceptions.BeneficiarioNotFound;
import com.restfulapi.desafio.exceptions.CodigoCadastroAnsInvalid;
import com.restfulapi.desafio.exceptions.CpfConflict;
import com.restfulapi.desafio.exceptions.CpfInvalid;
import com.restfulapi.desafio.exceptions.ErrorResponse;
import com.restfulapi.desafio.exceptions.PlanoAtrelado;
import com.restfulapi.desafio.exceptions.PlanoConflict;
import com.restfulapi.desafio.exceptions.PlanoInvalid;
import com.restfulapi.desafio.exceptions.PlanoNotFound;

import java.util.Collections;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(PlanoNotFound.class)
    public ResponseEntity planoNotFound(PlanoNotFound exception) {
        ErrorResponse error = new ErrorResponse(
                "Not Found",
                exception.getMessage(),
                Collections.singletonList(new ErrorResponse.ErrorDetail("plano_id", "invalid"))
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(CpfConflict.class)
    public ResponseEntity cpfConflict(CpfConflict exception){
        ErrorResponse error = new ErrorResponse(
            "Conflict",
            exception.getMessage(),
            Collections.singletonList(new ErrorResponse.ErrorDetail("cpf", "invalid"))
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(CpfInvalid.class)
    public ResponseEntity cpfInvalid(CpfInvalid exception){
        ErrorResponse error = new ErrorResponse(
            "Internal Server Error",
            exception.getMessage(),
            Collections.singletonList(new ErrorResponse.ErrorDetail("cpf", "invalid"))
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(PlanoInvalid.class)
    public ResponseEntity PlanoInvalid(PlanoInvalid exception){
        ErrorResponse error = new ErrorResponse(
            "Internal Server Error",
            exception.getMessage(),
            Collections.singletonList(new ErrorResponse.ErrorDetail("plano_id", "invalid"))
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(PlanoConflict.class)
    public ResponseEntity PlanoConflict(PlanoConflict exception){
        ErrorResponse error = new ErrorResponse(
            "Conflict",
            exception.getMessage(),
            Collections.singletonList(new ErrorResponse.ErrorDetail("plano_id", "invalid"))
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(PlanoAtrelado.class)
    public ResponseEntity PlanoAtrelado(PlanoAtrelado exception){
        ErrorResponse error = new ErrorResponse(
            "Internal Server Error",
            exception.getMessage(),
            Collections.singletonList(new ErrorResponse.ErrorDetail("plano_id", "invalid"))
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(CodigoCadastroAnsInvalid.class)
    public ResponseEntity<ErrorResponse> CodigoCadastroAnsInvalid(CodigoCadastroAnsInvalid exception){
        ErrorResponse error = new ErrorResponse(
            "Conflict",
            exception.getMessage(),
            Collections.singletonList(new ErrorResponse.ErrorDetail("codigo_registro_ans", "invalid"))
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(BeneficiarioNotFound.class)
    public ResponseEntity BeneficiarioNotFound(BeneficiarioNotFound exception){
        ErrorResponse error = new ErrorResponse(
            "Internal Server Error",
            exception.getMessage(),
            Collections.singletonList(new ErrorResponse.ErrorDetail("codigo_registro_ans", "invalid"))
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
