package com.acro.dev.propmgnt.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerRequest {
    private Long ownerId;

    private Long managerId;


    private String ownerFirstName;

    private  String ownerLastName;

    @NotNull
    @Email(message = "Please provide proper email")
    private String ownerEmail;

    @NotNull
    private Long ownerPhoneNumber;

    @NotNull
     private Long einNumber;

    private String data;
    private String fileName;



}
