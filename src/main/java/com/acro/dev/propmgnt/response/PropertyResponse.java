package com.acro.dev.propmgnt.response;
import lombok.Data;

@Data
public class PropertyResponse {
    private Long ownerId;
    private Long propertyId;
    private Long addressId;

    int noOfBeds;
    int noOfBaths;
    private String areaOfUnit;
    private String hallDimension;
    private String kitchenDimension;
    private String bedRoomDimension;
    private String garageDimension;
    private double rent;

}
