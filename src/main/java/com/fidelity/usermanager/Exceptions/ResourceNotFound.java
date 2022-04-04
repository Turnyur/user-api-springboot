package com.fidelity.usermanager.Exceptions;


public class ResourceNotFound extends RuntimeException{

    private String errorResource;
    private String ErrorField;
    private Object ErrorValue;

    public ResourceNotFound(String errorResource, String ErrorField, Object ErrorValue) {
            super(String.format("'%s' not found", errorResource, ErrorField, ErrorValue));
        this.errorResource = errorResource;
        this.ErrorField = ErrorField;
        this.ErrorValue = ErrorValue;

    }

    public String getErrorResource() {
        return errorResource;
    }

    public String getErrorField() {
        return ErrorField;
    }

    public Object getErrorValue() {
        return ErrorValue;
    }
}
