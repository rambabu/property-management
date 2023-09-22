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
 private boolean isPrimary;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;
   @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
   @JoinColumn(name = "property_id", referencedColumnName = "id")
    private Property property;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="lease_id",referencedColumnName = "id")
    private LeaseDetails lease;







}
