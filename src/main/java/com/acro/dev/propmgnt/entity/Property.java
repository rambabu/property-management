package com.acro.dev.propmgnt.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="property")
public class Property extends BaseEntity {
    @Column(name="noof_beds")
    int noOfBeds;

    @Column(name="noof_baths")
    int noOfBaths;

    @Column(name="area_ofunit")
    String areaOfUnit;

    @Column(name="hall_dimension")
    String hallDimension;


    @Column(name="kitchen_dimension")
    String kitchenDimension;

    @Column(name="bedroom_dimension")
    String bedRoomDimension;

    @Column(name="garage_dimension")
    String garageDimension;

    @Column(name="rent")
    double rent;

    @OneToOne
    @JoinColumn(name="address_id", referencedColumnName = "id")
    private Address address;

    //many properties----to----one owner
    @ManyToOne
    @JoinColumn(name="owner_id",referencedColumnName = "id")
    private Owner owner;
}

