package com.acro.dev.propmgnt.service;

import com.acro.dev.propmgnt.entity.Address;
import com.acro.dev.propmgnt.entity.Owner;
import com.acro.dev.propmgnt.entity.Property;
import com.acro.dev.propmgnt.exception.PropertyManagementException;
import com.acro.dev.propmgnt.repository.AddressRepository;
import com.acro.dev.propmgnt.repository.OwnerRepository;
import com.acro.dev.propmgnt.repository.PropertyRepository;
import com.acro.dev.propmgnt.request.AddressRequest;
import com.acro.dev.propmgnt.request.PropertyRequest;
import com.acro.dev.propmgnt.response.PropertyResponse;
import com.acro.dev.propmgnt.responsemethod.CommonResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class PropertyServiceImpl implements PropertyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyServiceImpl.class);

    private final PropertyRepository propertyRepository;
    private final OwnerRepository ownerRepository;

    private final AddressRepository addressRepository;
    private final CommonResponseMapper commonResponseMapper;

    public PropertyServiceImpl(@Autowired PropertyRepository propertyRepository,
                               @Autowired OwnerRepository ownerRepository,
                               @Autowired AddressRepository addressRepository,
                               @Autowired CommonResponseMapper commonResponseMapper) {
        this.propertyRepository = propertyRepository;
        this.ownerRepository = ownerRepository;
        this.addressRepository = addressRepository;
        this.commonResponseMapper = commonResponseMapper;

    }


    @Override
    @Transactional
    public PropertyResponse createProperty(PropertyRequest propertyRequest) {
        LOGGER.info("Received request to property");
        Property property = new Property();
        Address address = new Address();
        Optional<Owner> owner = ownerRepository.findById(propertyRequest.getOwnerId());
        if (owner.isPresent()) {
            Owner ownerOne = owner.get();
            AddressRequest addressRequest = new AddressRequest();
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
            //property.setAddress(addressOne);


            Property propertyOne = propertyRepository.save(property);
            LOGGER.info("Owner address saved");
            LOGGER.info("Created property");
            return commonResponseMapper.getPropertyResponse(propertyOne);


        } else {
            throw new PropertyManagementException("Property not created");
        }

    }


    @Override
    @Transactional
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
            return commonResponseMapper.getPropertyResponse(updatedProperty);
        }
        throw new PropertyManagementException("Property not found");
    }


    @Override
    public PropertyResponse findPropertyById(Long id) {
        Optional<Property> property = propertyRepository.findById(id);
        if (property.isPresent()) {
            Property propertyOne = property.get();
            return commonResponseMapper.getPropertyResponse(propertyOne);
        }
        throw new PropertyManagementException("Property not found");
    }


    @Override

    public boolean deletePropertyByOwnerId(Long id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isEmpty()) {
            LOGGER.error("Failed to delete Property Id");
            throw new PropertyManagementException("Property not found");
        } else {
            try {
                Owner callOwner = owner.get();
                ownerRepository.deleteOwnerById(id);
                return true;
            } catch (Exception e) {
                LOGGER.error("Failed to delete Property by Owner Id {}", id);
                throw new PropertyManagementException("failed to delete PropertyByOwnerId");
            }
        }
    }
}