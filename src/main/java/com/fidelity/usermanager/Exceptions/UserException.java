package com.fidelity.usermanager.Exceptions;


public class UserException extends RuntimeException{


    private String ErrorField;
    private Object ErrorValue;

    public UserException( String ErrorField, Object ErrorValue) {
            super(String.format("Error Field: '%s'. Reason: '%s'", ErrorField, ErrorValue));

        this.ErrorField = ErrorField;
        this.ErrorValue = ErrorValue;

    }

    public String getErrorField() {
        return ErrorField;
    }

    public Object getErrorValue() {
        return ErrorValue;
    }
}
