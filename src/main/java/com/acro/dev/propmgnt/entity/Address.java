package com.acro.dev.propmgnt.entity;

import jakarta.persistence.*;
import lombok.Data;

//@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name="address")
public class Address extends BaseEntity {
        @Column(name="line_one")
        private String lineOne;

        @Column(name="line_two")
        private String lineTwo;

         private String city;
         private String state;
         @Column(name="zip_code")
         private int zipcode;
         private AddressType type;
         @ManyToOne
         @JoinColumn(name="owner_id")
         Owner owner;
    }
