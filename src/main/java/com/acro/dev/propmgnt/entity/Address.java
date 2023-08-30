package com.acro.dev.propmgnt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data
@Table(name= "address")
public class Address {
    private Long id;
    private String lineOne;
    private String lineTwo;
    private String city;
    private String state;
    private int zipcode;
}
