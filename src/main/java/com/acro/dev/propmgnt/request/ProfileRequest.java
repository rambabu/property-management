package com.acro.dev.propmgnt.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileRequest {
    private Long id;
    @NotEmpty(message = "please provide FirstName")
   // @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    private String firstName;
    @NotEmpty(message = "please provide LastName")
   // @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    private String lastName;
    @Email(message = "please provide valid Email")
    private String email;
    @NotNull(message="Please enter your phone number")
    private Long phoneNumber;
    @NotNull
    private Long ssn;

}
