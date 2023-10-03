package com.acro.dev.propmgnt.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name="property")
public class Property extends BaseEntity {
    @Column(name="noof_beds")
    private int noOfBeds;
    @Column(name="noof_baths")
    private int noOfBaths;
    private double rent;

    @Column(name = "area_ofunit")
    private String areaOfUnit;
    @Column(name="hall_dimension")
    private String hallDimension;
    @Column(name="kitchen_dimension")
    private String kitchenDimension;
    @Column(name="bedroom_dimension")
    private String bedRoomDimension;
    @Column(name="garage_dimension")
    private String garageDimension;
    //many properties----to----one owner
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="owner_id",referencedColumnName = "id")
    private Owner owner;

    @OneToMany(mappedBy = "property",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<WorkOrder> workOrderList;


}

