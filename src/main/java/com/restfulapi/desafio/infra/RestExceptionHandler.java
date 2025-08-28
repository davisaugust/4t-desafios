package com.restfulapi.desafio.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.restfulapi.desafio.exceptions.BeneficiarioInvalid;
import com.restfulapi.desafio.exceptions.BeneficiarioNotFound;
import com.restfulapi.desafio.exceptions.CpfConflict;
import com.restfulapi.desafio.exceptions.CpfInvalid;
import com.restfulapi.desafio.exceptions.CpfIsntNumber;
import com.restfulapi.desafio.exceptions.ErrorResponse;
import com.restfulapi.desafio.exceptions.PlanoAtrelado;
import com.restfulapi.desafio.exceptions.PlanoConflict;
import com.restfulapi.desafio.exceptions.PlanoInvalid;
import com.restfulapi.desafio.exceptions.PlanoNotFound;

import java.util.Collections;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(PlanoNotFound.class)
    public ResponseEntity<ErrorResponse> planoNotFound(PlanoNotFound exception) {
        ErrorResponse error = new ErrorResponse(
                "Not Found",
                exception.getMessage(),
                Collections.singletonList(new ErrorResponse.ErrorDetail("plano_id", "invalid"))
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(CpfConflict.class)
    public ResponseEntity<ErrorResponse> cpfConflict(CpfConflict exception){
        ErrorResponse error = new ErrorResponse(
            "Conflict",
            exception.getMessage(),
            Collections.singletonList(new ErrorResponse.ErrorDetail("cpf", "invalid"))
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(CpfInvalid.class)
    public ResponseEntity<ErrorResponse> cpfInvalid(CpfInvalid exception){
        ErrorResponse error = new ErrorResponse(
            "Internal Server Error",
            exception.getMessage(),
            Collections.singletonList(new ErrorResponse.ErrorDetail("cpf", "invalid"))
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(PlanoInvalid.class)
    public ResponseEntity<ErrorResponse> PlanoInvalid(PlanoInvalid exception){
        ErrorResponse error = new ErrorResponse(
            "Internal Server Error",
            exception.getMessage(),
            Collections.singletonList(new ErrorResponse.ErrorDetail("plano_id", "invalid"))
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(PlanoConflict.class)
    public ResponseEntity<ErrorResponse> PlanoConflict(PlanoConflict exception){
        ErrorResponse error = new ErrorResponse(
            "Conflict",
            exception.getMessage(),
            Collections.singletonList(new ErrorResponse.ErrorDetail("plano_id", "invalid"))
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(PlanoAtrelado.class)
    public ResponseEntity<ErrorResponse> PlanoAtrelado(PlanoAtrelado exception){
        ErrorResponse error = new ErrorResponse(
            "Conflict",
            exception.getMessage(),
            Collections.singletonList(new ErrorResponse.ErrorDetail("plano_id", "invalid"))
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(BeneficiarioNotFound.class)
    public ResponseEntity<ErrorResponse> BeneficiarioNotFound(BeneficiarioNotFound exception){
        ErrorResponse error = new ErrorResponse(
            "Not found",
            exception.getMessage(),
            Collections.singletonList(new ErrorResponse.ErrorDetail("codigo_registro_ans", "invalid"))
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(CpfIsntNumber.class)
    public ResponseEntity<ErrorResponse> CpfIsntNumber(CpfIsntNumber exception){
        ErrorResponse error = new ErrorResponse(
            "Internal Server Error",
            exception.getMessage(),
            Collections.singletonList(new ErrorResponse.ErrorDetail("codigo_registro_ans", "invalid"))
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(BeneficiarioInvalid.class)
    public ResponseEntity<ErrorResponse> BeneficiarioInvalid(BeneficiarioInvalid exception){
        ErrorResponse error = new ErrorResponse(
            "Internal Server Error",
            exception.getMessage(),
            Collections.singletonList(new ErrorResponse.ErrorDetail("codigo_registro_ans", "invalid"))
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
