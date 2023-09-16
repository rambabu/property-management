package com.acro.dev.propmgnt.service;

import com.acro.dev.propmgnt.controller.PropertyController;
import com.acro.dev.propmgnt.entity.Address;
import com.acro.dev.propmgnt.entity.Owner;
import com.acro.dev.propmgnt.entity.Property;
import com.acro.dev.propmgnt.repository.AddressRepository;
import com.acro.dev.propmgnt.repository.OwnerRepository;
import com.acro.dev.propmgnt.repository.PropertyRepository;
import com.acro.dev.propmgnt.request.AddressRequest;
import com.acro.dev.propmgnt.request.PropertyRequest;
import com.acro.dev.propmgnt.response.AddressResponse;
import com.acro.dev.propmgnt.response.PropertyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;
    private final OwnerRepository ownerRepository;
    private final AddressRepository addressRepository;


    public PropertyServiceImpl(@Autowired PropertyRepository propertyRepository,
                               @Autowired OwnerRepository ownerRepository,
                               @Autowired AddressRepository addressRepository) {
        this.propertyRepository = propertyRepository;
        this.ownerRepository = ownerRepository;
        this.addressRepository = addressRepository;

    }

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyController.class);

    @Override
    public PropertyResponse createProperty(PropertyRequest propertyRequest) {
        LOGGER.info("Received request to property");
        Property property = new Property();
        Address address = new Address();
        AddressRequest addressRequest = new AddressRequest();
        Optional<Owner> owner = ownerRepository.findById(propertyRequest.getOwnerId());
        if (owner.isPresent()) {
            Owner ownerOne = owner.get();
            address.setId(addressRequest.getAddressId());
            address.setLineOne(addressRequest.getLineOne());
            address.setLineTwo(addressRequest.getLineTwo());
            address.setZipcode(addressRequest.getZipcode());
            address.setCity(addressRequest.getCity());
            address.setState(addressRequest.getState());
            address.setType(addressRequest.getType());
            Address addressOne = addressRepository.save(address);

            property.setAreaOfUnit(propertyRequest.getAreaOfUnit());
            property.setNoOfBeds(propertyRequest.getNoOfBeds());
            property.setNoOfBaths(propertyRequest.getNoOfBaths());
            property.setBedRoomDimension(propertyRequest.getBedRoomDimension());
            property.setHallDimension(propertyRequest.getHallDimension());
            property.setKitchenDimension(propertyRequest.getKitchenDimension());
            property.setGarageDimension(propertyRequest.getGarageDimension());
            property.setRent(propertyRequest.getRent());
            property.setOwner(owner.get());
            property.setAddress(addressOne);


            Property propertyOne = propertyRepository.save(property);
            LOGGER.info("Owner address saved");
            LOGGER.info("Create property");
            // return getAddressResponse(address);
            return getPropertyResponse(propertyOne);


        } else {
            return null;
        }

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

    public AddressResponse getAddressResponse(Address address) {
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setAddressId(address.getId());
        addressResponse.setLineOne(address.getLineOne());
        addressResponse.setLineTwo(address.getLineTwo());
        addressResponse.setCity(address.getCity());
        addressResponse.setState(address.getState());
        addressResponse.setZipcode(address.getZipcode());
        addressResponse.setType(address.getType());
        return addressResponse;
    }

    @Override
    public PropertyResponse updateProperty(Long id, PropertyRequest propertyRequest) {
        Optional<Property> property = propertyRepository.findById(id);
        if (property.isPresent()) {
            Property existingProperty = property.get();
            existingProperty.setNoOfBaths(propertyRequest.getNoOfBaths());
            existingProperty.setNoOfBeds(propertyRequest.getNoOfBeds());
            existingProperty.setHallDimension(propertyRequest.getHallDimension());
            existingProperty.setKitchenDimension(propertyRequest.getKitchenDimension());
            existingProperty.setBedRoomDimension(propertyRequest.getBedRoomDimension());
            existingProperty.setRent(propertyRequest.getRent());


            Property updatedProperty = propertyRepository.save(existingProperty);
            LOGGER.info("Property Updated Successfully");
            return getPropertyResponse(updatedProperty);
        }
        return null;
    }


    @Override
    public PropertyResponse findPropertyById(Long id) {
        Optional<Property> property = propertyRepository.findById(id);
        if (property.isPresent()) {
            Property propertyOne = property.get();
            return getPropertyResponse(propertyOne);
        }
        return null;
    }

   /* @Override
    public List<PropertyResponse> getPropertyByOwnerId(Long id) {
        return null;
    }*/

    @Override
    public boolean deletePropertyById(Long id) {
        try {
            propertyRepository.findById(id);
            return true;
        } catch (Exception e) {
            LOGGER.error("Failed to delete");
        }
        return false;
    }
}