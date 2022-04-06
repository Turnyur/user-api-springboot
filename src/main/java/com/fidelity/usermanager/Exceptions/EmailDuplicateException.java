package com.fidelity.usermanager.Exceptions;

import lombok.Getter;


@Getter
public class EmailDuplicateException extends RuntimeException{
    private String resource;
    private String field;
    private String reason;

    public EmailDuplicateException(String resource, String field, String reason) {
        this.resource = resource;
        this.field = field;
        this.reason = reason;
    }
}
