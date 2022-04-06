package com.fidelity.usermanager.Exceptions;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;

@Getter
public class BirthDateException extends RuntimeException{

   private String resource;
   private String field;
   private String reason;

    public BirthDateException(String resource, String field, String reason) {
        this.resource = resource;
        this.field = field;
        this.reason = reason;
    }
}
