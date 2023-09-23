package com.acro.dev.propmgnt.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyRequest {
    private Long ownerId;
    private Long propertyId;
    private Long addressId;

    @NotNull
    int noOfBeds;
    @NotNull
    int noOfBaths;
    private String areaOfUnit;
    private String hallDimension;
    private String kitchenDimension;
    private String bedRoomDimension;
    private String garageDimension;
    @NotNull
    private double rent;

}
