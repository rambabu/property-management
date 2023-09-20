package com.acro.dev.propmgnt.response;
import lombok.Data;

@Data
public class OwnerResponse {
   private Long ownerId;
    private String ownerFirstName;
    private  String ownerLastName;
    private String ownerEmail;
    private Long ownerPhoneNumber;
    private Long einNumber;
    //private Long propertyId;

}
