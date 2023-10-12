package com.acro.dev.propmgnt.service;

import com.acro.dev.propmgnt.CommonResponseMapper;
import com.acro.dev.propmgnt.entity.Address;
import com.acro.dev.propmgnt.exception.PropertyManagementException;
import com.acro.dev.propmgnt.repository.AddressRepository;
import com.acro.dev.propmgnt.repository.OwnerRepository;
import com.acro.dev.propmgnt.request.AddressRequest;
import com.acro.dev.propmgnt.response.AddressResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class AddressServiceImpl implements AddressService {
    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);
    private final AddressRepository addressRepository;
    private final OwnerRepository ownerRepository;
    private final CommonResponseMapper commonResponseMapper;
    @Value("${property.allowed.zipcodes}")
    private Long zipCode;

    public AddressServiceImpl(@Autowired AddressRepository addressRepository, AddressRepository addressRepository1, OwnerRepository ownerRepository, CommonResponseMapper commonResponseMapper) {
        this.addressRepository = addressRepository;
        this.ownerRepository = ownerRepository;
        this.commonResponseMapper = commonResponseMapper;
    }

    @Transactional
    @Override
    public AddressResponse createAddress(AddressRequest addressRequest) {
        logger.info("Received tenant request {}", addressRequest);
        Address address = new Address();
        address.setLineOne(addressRequest.getLineOne());
        address.setLineTwo(addressRequest.getLineTwo());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setZipcode(addressRequest.getZipcode());
        address.setType(addressRequest.getType());
        Address address1 = addressRepository.save(address);   // save model to DB
        logger.info("Saved Successfully");
        return commonResponseMapper.convertToAddressResponse(address1);
    }// convert model to response object

    @Override
    @Transactional
    public AddressResponse updateAddress(Long id, AddressRequest addressRequest) {
        // Check if tenant is present
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()) {
            Address address1 = address.get();  // set values from request to tenant model
            BeanUtils.copyProperties(address1,addressRequest);
            Address address2= addressRepository.save(address1);         // call save to update the DB record
            logger.info("Tenant updated Successfully {}", address2);
            return commonResponseMapper.convertToAddressResponse(address2);      // convert the result to response object
        }
        throw new PropertyManagementException("Address not found");
    }
    @Override
    public AddressResponse getById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()) {
            Address address1=address.get();
            return commonResponseMapper.convertToAddressResponse(address1);
        }
        throw new PropertyManagementException("Tenant not found");
    }
    public Boolean deleteAddressById(Long id) {
        Optional<Address> address= addressRepository.findById(id);
        if (address.isEmpty()) {
            logger.error("Delete Failed");
            throw new PropertyManagementException("Tenant not found");
        } else {
            try {
                Address newAddress = address.get();
                addressRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                logger.error("Unable to delete address", e);
                throw new PropertyManagementException("Unable to delete address", e);
            }
        }
    }
}
