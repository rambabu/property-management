package com.acro.dev.propmgnt.request;

import com.acro.dev.propmgnt.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantRequest {
    private Long tenantId;
    private Long profileId;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    @Email(message = "Please provide proper email")
    private String email;
    @NotNull
    private Long phoneNumber;
    @NotNull
    private Long ssn;
    @NotNull
    private Long propertyId;
    private List<Address> addresses;
}
