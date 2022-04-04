package com.fidelity.usermanager.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    @Size(min = 3, max = 128)
    @NotBlank(message = "Please provide Firstname field")
    @NotEmpty
    @NotNull
    private String FirstName;

    @Size( max = 128)
    @NotEmpty
    @NotEmpty
    private String LastName;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDate;

    @Email(message = "Email is not valid" )
    @NotEmpty(message = "Email cannot be empty")
    private String email;


}
