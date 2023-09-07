package com.acro.dev.propmgnt.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name= "address")
public class Address extends BaseEntity {
    private String lineOne;
    private String lineTwo;
    private String city;
    private String state;
    private int zipcode;
    private AddressType type;
    @ManyToOne
    Tenant tenant;

}
