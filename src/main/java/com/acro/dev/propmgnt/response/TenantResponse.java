package com.acro.dev.propmgnt.response;

import lombok.Data;
@Data
public class TenantResponse {
    private Long tenantId;
    private Long propertyId;
    private Long profileId;
    private String fName;
    private String lName;
    private String email;
    private Long phoneNumber;
    private Long ssn;
    //private List<Address> addresses;
}
