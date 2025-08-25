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

import com.restfulapi.desafio.exceptions.ErrorResponse;
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
