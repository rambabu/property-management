package com.acro.dev.propmgnt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PropertyManager {
    @Id
    @GeneratedValue
    int managerId;
    String managerName;
    String managerPhoneNumber;
    String managerEmail;
   /* @OneToMany(mappedBy = "propertyManager")
    private List<Owner> owners;
    @OneToMany(mappedBy = "propertyManager")
    List<Repair> repairs;*/
}

