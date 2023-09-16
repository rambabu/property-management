package com.acro.dev.propmgnt.entity;
import lombok.Data;
import jakarta.persistence.*;


//repar--property 1-1-tentant
@Entity
@Data
public class Repair {
    @Id
    @GeneratedValue
    int repairId;
    // int tenantId;
    String typeOfRepair;
    boolean isInsurance;
    int costOfRepair;
    @OneToOne
    @JoinColumn(name="property_id")
    private Property property;
}

