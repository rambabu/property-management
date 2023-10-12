package com.acro.dev.propmgnt.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name= "address")
public class Address extends BaseEntity {
        @Column(name="line_one")
        private String lineOne;

        @Column(name="line_two")
        private String lineTwo;

         private String city;
         private String state;
       //  @Column(name="zip_code")
         private int zipcode;
         private AddressType type;
         @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
         @JoinColumn(name="owner_id")
         Owner owner;
        /* @ManyToOne
         Tenant tenant;*/

}
