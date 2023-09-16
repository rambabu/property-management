package com.acro.dev.propmgnt.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
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

         @OneToMany(mappedBy = "owner")//1o----many properties
         //@JoinColumn(name="property_id")
         List<Property> PropertyList=new ArrayList<>();

        //owner ----many properties(in propert table have ownerid)

        //@OneToMany(mappedBy = "owner")//1o----many repairs
        //List<Repair> repairs;
       // @OneToMany(mappedBy = "owner")//1o----many properties
        // List<Property> properties;
        //@ManyToOne//many owners--1pm
        //private PropertyManager propertyManager;


    }
