package com.acro.dev.propmgnt.response;

import com.acro.dev.propmgnt.entity.Tenant;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class LeaseResponse {
    private Long leaseId;
    private Long propertyId;
    private List<Tenant> tenants;
    private Date leaseDate;
    private Date leaseStartDate;
    private Date leaseEndDate;
    private int leaseTerm;
    private double monthlyRent;
    private double securityDepositAmount;
    private double petDeposit;
    private boolean leaseStatus;

}
