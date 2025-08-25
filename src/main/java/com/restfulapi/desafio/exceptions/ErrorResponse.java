package com.restfulapi.desafio.exceptions;

import java.util.List;

public class ErrorResponse {
    private String error;
    private String message;
    private List<ErrorDetail> details;

    public ErrorResponse(String error, String message, List<ErrorDetail> details) {
        this.error = error;
        this.message = message;
        this.details = details;
    }

    public String getError() { return error; }
    public String getMessage() { return message; }
    public List<ErrorDetail> getDetails() { return details; }

    // classe interna para os detalhes
    public static class ErrorDetail {
        private String field;
        private String rule;

        public ErrorDetail(String field, String rule) {
            this.field = field;
            this.rule = rule;
        }

        public String getField() { return field; }
        public String getRule() { return rule; }
    }
}
