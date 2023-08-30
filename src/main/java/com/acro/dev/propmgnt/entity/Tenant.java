package com.acro.dev.propmgnt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name= " tenant ")
public class Tenant {
    private Long id;
    private Address addressId;
    private Address workAddId;
    private Address prevAddId;
    private int noOfPeople;
    private boolean isPrimary;
    private Long ssn;
    private int pets;
    private boolean isInsured;
    private Long profileId;
    private Long propertyId;




}
