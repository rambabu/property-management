package com.acro.dev.propmgnt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name= "profile")
public class Profile extends BaseEntity{
    private String fName;
    private String lName;
    private String email;
    private Long phoneNumber;
    private Long ssn;

}
