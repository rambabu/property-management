package com.acro.dev.propmgnt.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name= "tenant")
public class Tenant extends BaseEntity{
 //@OneToMany
 //private List<Address> addresses;
   // private Address workAddId;
  //  private Address prevAddId;
    private int noOfPeople;
    private boolean isPrimary;
    private int pets;
    private boolean isInsured;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;
    private Long propertyId;







}
