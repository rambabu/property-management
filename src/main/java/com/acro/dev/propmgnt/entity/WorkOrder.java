package com.acro.dev.propmgnt.entity;
import lombok.Data;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class WorkOrder extends  BaseEntity{
    String typeOfRepair;
    boolean isInsurance;
    int costOfRepair;
    @OneToOne
    @JoinColumn(name="property_id")
    private Property property;
}

