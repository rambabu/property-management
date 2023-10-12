package com.acro.dev.propmgnt.response;
import lombok.Data;

@Data
public class OwnerResponse {
   private Long ownerId;
  private Long managerId;

   private String ownerFirstName;
    private  String ownerLastName;
    private String ownerEmail;
    private Long ownerPhoneNumber;
    private Long einNumber;

    private String data;
    private String fileName;


}
