package com.acro.dev.propmgnt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name= "property")
public class Property extends BaseEntity {
   @OneToOne
   @JoinColumn(name="address_id", referencedColumnName = "id")
   private Address address;
   private String type;
   private int noOfBeds;
   private int noOfBaths;
   private double rent;


}
