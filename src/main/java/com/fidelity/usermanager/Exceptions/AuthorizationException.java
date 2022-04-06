package com.fidelity.usermanager.Exceptions;

import lombok.Getter;

@Getter
public class AuthorizationException extends RuntimeException {

    private String field;
    private String reason;

    public AuthorizationException(String field, String reason) {

        this.field = field;
        this.reason = reason;
    }
}

