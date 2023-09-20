package com.acro.dev.propmgnt.response;
import com.acro.dev.propmgnt.entity.AddressType;
import lombok.Data;

@Data
public class AddressResponse {
    private Long addressId;
    private String lineOne;
    private String lineTwo;
    private String city;
    private String state;
    private int zipcode;
    private AddressType type;

}
