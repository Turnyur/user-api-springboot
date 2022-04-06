package com.fidelity.usermanager.DTOs;

import com.fidelity.usermanager.Models.User;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class APIResponseDTO {
    public int code;
    public String message;
    public String path;
    public String status;
    public Date timestamp;
    public List<?> data;
    public Map<String, Object> errors;

}
