package com.acro.dev.propmgnt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name= "profile")
public class Profile {
    private Long profileId;
    private String fName;
    private String lName;
    private String email;
    private Long phoneNumber;
    private Long addressId;
}
