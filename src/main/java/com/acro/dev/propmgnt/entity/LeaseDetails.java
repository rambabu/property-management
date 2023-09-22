package com.acro.dev.propmgnt.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
public class LeaseDetails extends BaseEntity{
    //list of payments
    @OneToOne
    @JoinColumn(name= "property_id", referencedColumnName = "id")
    private Property property;
    @OneToMany(mappedBy = "tenant")
    private List<Tenant> tenants;
   private Date leaseDate;
    private Date leaseStartDate;
    private Date leaseEndDate;
    private int leaseTerm;
    private double monthlyRent;
    private double securityDepositAmount;
    private int pets;
    private boolean isInsured;
    private double petDeposit;
    private boolean leaseStatus;
    private int noOfPeople;










}
