package com.example.cs.dao;

public enum Errors {

    TECH_ERROR("500", "TECH", "TechnicalProblem", "The server encountered an unexpected condition which prevented it from fulfilling the request"), UNAUTHORIZED_ERROR("403", "NAUT", "NotAuthorised", "Permission to be processed is not granted");

    private String httpStatusCode;
    private String reasonCode;
    private String message;
    private String narrative;

    Errors(String httpStatusCode, String reasonCode, String message, String narrative) {
        this.httpStatusCode = httpStatusCode;
        this.reasonCode = reasonCode;
        this.message = message;
        this.narrative = narrative;
    }

    public String getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public String getMessage() {
        return message;
    }

    public String getNarrative() {
        return narrative;
    }

}