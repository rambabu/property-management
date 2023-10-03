package com.acro.dev.propmgnt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name= "profile")
public class Profile extends BaseEntity{
    @Column(name="f_name")
    private String firstName;
    @Column(name="l_name")
    private String lastName;
    private String email;
    private Long phoneNumber;
    private Long ssn;
}

