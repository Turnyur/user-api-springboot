package com.fidelity.usermanager.Exceptions;

import com.fidelity.usermanager.DTOs.APIResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<?> handleResourceNotFoundError(ResourceNotFound ex, WebRequest req){

        APIResponseDTO responseObject = new APIResponseDTO();
        responseObject.message ="Resource not found";
        responseObject.timestamp = new Date();
        responseObject.status = "error";
        responseObject.code = HttpStatus.NOT_FOUND.value();
        responseObject.data = new ArrayList<>();
        responseObject.errors= new HashMap<>();
        responseObject.errors.put(ex.getErrorField(), ex.getErrorValue());
        responseObject.path = ex.getErrorResource();
        return new ResponseEntity<APIResponseDTO>(responseObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BirthDateException.class)
    public ResponseEntity<?> handleBirthDateException(BirthDateException ex, WebRequest req){

        APIResponseDTO responseObject = new APIResponseDTO();
        responseObject.message ="Invalid birth date";
        responseObject.timestamp = new Date();
        responseObject.status = "error";
        responseObject.code = HttpStatus.BAD_REQUEST.value();
        responseObject.data = new ArrayList<>();
        responseObject.errors= new HashMap<>();
        responseObject.errors.put(ex.getField(), ex.getReason());
        responseObject.path = ex.getResource();
        return new ResponseEntity<APIResponseDTO>(responseObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailDuplicateException.class)
    public ResponseEntity<?> handleEmailDuplicateException(EmailDuplicateException ex, WebRequest req){

        APIResponseDTO responseObject = new APIResponseDTO();
        responseObject.message ="Duplicate email";
        responseObject.timestamp = new Date();
        responseObject.status = "error";
        responseObject.code = HttpStatus.BAD_REQUEST.value();
        responseObject.data = new ArrayList<>();
        responseObject.errors= new HashMap<>();
        responseObject.errors.put(ex.getField(), ex.getReason());
        responseObject.path = ex.getResource();
        return new ResponseEntity<APIResponseDTO>(responseObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<?> handleAuthorizationException(AuthorizationException ex, WebRequest req){

        APIResponseDTO responseObject = new APIResponseDTO();
        responseObject.message ="Invalid token";
        responseObject.timestamp = new Date();
        responseObject.status = "error";
        responseObject.code = HttpStatus.BAD_REQUEST.value();
        responseObject.data = new ArrayList<>();
        responseObject.errors= new HashMap<>();
        responseObject.errors.put(ex.getField(), ex.getReason());
        responseObject.path = req.getContextPath();
        return new ResponseEntity<APIResponseDTO>(responseObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest req){

        APIResponseDTO responseObject = new APIResponseDTO();
        responseObject.message ="Unknown error";
        responseObject.timestamp = new Date();
        responseObject.status = "error";
        responseObject.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        responseObject.data = new ArrayList<>();
        responseObject.errors= new HashMap<>();
        responseObject.errors.put("reason", ex.getMessage());
        responseObject.path = req.getContextPath();
        return new ResponseEntity<APIResponseDTO>(responseObject, HttpStatus.NOT_FOUND);
    }
}
