package com.acro.dev.propmgnt.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name="owner")
public class Owner extends BaseEntity {


        @Column(name="owner_firstname")
        private String ownerFirstName;

        @Column(name="owner_lastname")
        private  String ownerLastName;

        @Column(name="owner_email")
        private String ownerEmail;


        @Column(name="owner_phonenumber")
        private Long ownerPhoneNumber;

        @Column(name="ein_number")
        private Long einNumber;

         @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)//1o----many properties
         List<Property> PropertyList;

         @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
         @JoinColumn(name="manager_id",referencedColumnName = "id")
         private PropertyManager propertyManager;


    }
