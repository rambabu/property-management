package com.acro.dev.propmgnt.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class LeaseResponse {
    private Long leaseId;
    private Long propertyId;
    private List<TenantResponse> tenants;
    private LocalDate leaseDate;
    private LocalDate leaseStartDate;
    private LocalDate leaseEndDate;
    private int leaseTerm;
    private double monthlyRent;
    private double securityDepositAmount;
    private double petDeposit;
    private boolean leaseStatus;

}
