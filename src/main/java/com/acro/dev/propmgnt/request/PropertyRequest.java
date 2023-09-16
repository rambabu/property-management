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



   // private AddressRequest addressRequest;

    @NotNull
    int noOfBeds;
    @NotNull
    int noOfBaths;
  //  @NotEmpty
    private String areaOfUnit;
   // @NotEmpty
    private String hallDimension;
    // @NotEmpty
    private String kitchenDimension;
   // @NotEmpty
    private String bedRoomDimension;
   // @NotEmpty
    private String garageDimension;
    @NotNull
    private double rent;

}
