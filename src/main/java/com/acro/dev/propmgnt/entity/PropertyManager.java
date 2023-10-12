package com.acro.dev.propmgnt.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name="propertymanager")
public class PropertyManager extends BaseEntity {
    @Column(name="manager_name")
    String managerName;
    @Column(name="manager_phone_number")
    Long managerPhoneNumber;
    @Column(name = "manager_email")
    String managerEmail;
    @OneToMany(mappedBy="propertyManager",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<Owner> OwnerList;
  }

