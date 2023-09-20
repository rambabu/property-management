package com.acro.dev.propmgnt.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Table(name="owner")
public class Owner extends BaseEntity {


        @Column(name="owner_firstname")
        private String ownerFirstName;

        @Column(name="owner_lastname")
        private  String ownerLastName;

        @Getter
        @Column(name="owner_email")
        private String ownerEmail;


        @Column(name="owner_phonenumber")
        private Long ownerPhoneNumber;

        @Column(name="ein_number")
        private Long einNumber;

         @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)//1o----many properties
         List<Property> PropertyList;


    }
