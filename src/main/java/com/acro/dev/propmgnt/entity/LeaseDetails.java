package com.acro.dev.propmgnt.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
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
    @OneToMany(mappedBy = "lease", fetch = FetchType.EAGER)
    private List<Tenant> tenants;
    private LocalDate leaseDate;
    private LocalDate leaseStartDate;
    private LocalDate leaseEndDate;
    private int leaseTerm;
    private double monthlyRent;
    private double securityDepositAmount;
    private int pets;
    @Column(name="is_insured")
    private boolean isInsured;

    private double petDeposit;
    private boolean leaseStatus;
    @Column(name="no_of_people")
    private int noOfPeople;










}
