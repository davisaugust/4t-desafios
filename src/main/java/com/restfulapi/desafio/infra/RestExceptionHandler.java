package com.restfulapi.desafio.infra;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.restfulapi.desafio.exceptions.CpfConflict;
import com.restfulapi.desafio.exceptions.CpfInvalid;
import com.restfulapi.desafio.exceptions.ErrorResponse;
import com.restfulapi.desafio.exceptions.PlanoInvalid;
import com.restfulapi.desafio.exceptions.PlanoNotFound;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
            "Conflict",
            exception.getMessage(),
            Collections.singletonList(new ErrorResponse.ErrorDetail("cpf", "invalid"))
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(PlanoInvalid.class)
    public ResponseEntity<ErrorResponse> PlanoInvalid(PlanoInvalid exception){
        ErrorResponse error = new ErrorResponse(
            "Internal Server Error",
            exception.getMessage(),
            Collections.singletonList(new ErrorResponse.ErrorDetail("cpf", "invalid"))
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        ErrorResponse error = new ErrorResponse(
                "Bad Request",
                "Parâmetro inválido: " + ex.getName() + " = " + ex.getValue(),
                Collections.singletonList(new ErrorResponse.ErrorDetail(ex.getName(), "type_mismatch"))
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                "Internal Server Error",
                ex.getMessage(),
                Collections.singletonList(new ErrorResponse.ErrorDetail("server", "unexpected"))
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
