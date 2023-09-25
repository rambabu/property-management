package com.acro.dev.propmgnt.responsemethod;

import com.acro.dev.propmgnt.entity.Owner;
import com.acro.dev.propmgnt.entity.Property;
import com.acro.dev.propmgnt.response.OwnerResponse;
import com.acro.dev.propmgnt.response.PropertyResponse;
import org.springframework.stereotype.Component;

@Component
public class CommonResponseMapper {
    public OwnerResponse getOwnerResponse(Owner owner) {
        OwnerResponse ownerResponse = new OwnerResponse();
        ownerResponse.setOwnerId(owner.getId());
        ownerResponse.setEinNumber(owner.getEinNumber());
        ownerResponse.setOwnerFirstName(owner.getOwnerFirstName());
        ownerResponse.setOwnerLastName(owner.getOwnerLastName());
        ownerResponse.setOwnerEmail(owner.getOwnerEmail());
        ownerResponse.setOwnerPhoneNumber(owner.getOwnerPhoneNumber());
        return ownerResponse;
    }
    public PropertyResponse getPropertyResponse(Property property) {
        PropertyResponse propertyResponse = new PropertyResponse();
        propertyResponse.setPropertyId(property.getId());
        propertyResponse.setOwnerId(property.getOwner().getId());
        propertyResponse.setAddressId(property.getAddress().getId());
        propertyResponse.setAreaOfUnit(property.getAreaOfUnit());
        propertyResponse.setNoOfBeds(property.getNoOfBeds());
        propertyResponse.setNoOfBaths(property.getNoOfBaths());
        propertyResponse.setBedRoomDimension(property.getBedRoomDimension());
        propertyResponse.setKitchenDimension(property.getKitchenDimension());
        propertyResponse.setHallDimension(property.getHallDimension());
        propertyResponse.setGarageDimension(property.getGarageDimension());
        propertyResponse.setRent(property.getRent());
        return propertyResponse;
    }



}
