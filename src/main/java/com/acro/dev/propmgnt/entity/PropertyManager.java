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
    int managerId;
    String managerName;
    String managerPhoneNumber;
    String managerEmail;
    @OneToMany(mappedBy="propertyManager",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<Owner> OwnerList;
  }

