package com.acro.dev.propmgnt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name= " property ")
public class Property {
   private Long  pid;
   private Long addressId;
   private String type;
   private int noOfBeds;
   private int noOfBaths;
   private double rent;


}
