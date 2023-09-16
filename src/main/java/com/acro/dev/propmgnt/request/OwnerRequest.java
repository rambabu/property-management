package com.acro.dev.propmgnt.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//javax--@col,table
//only variables comprises in requests/response
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerRequest {
    private Long ownerId;

    @NotEmpty
    @Size(max=16,message="firstName cannot have more than 16 characters")
    private String ownerFirstName;

    @NotEmpty
    @Size(max=13,message="lastName cannot have more than 13 characters")
    private  String ownerLastName;

    @NotNull
    @Email
    private String ownerEmail;

    @NotNull
    private Long ownerPhoneNumber;

    @NotNull
      /// @Size(max=9,message="ein number cannot have more than 9 numbers")
     private Long einNumber;
     /*@NotNull
     private Long propertyId;*/


}
