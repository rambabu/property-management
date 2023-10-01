package com.acro.dev.propmgnt.service;

import com.acro.dev.propmgnt.advice.CommonExceptionHandler;
import com.acro.dev.propmgnt.entity.Owner;
import com.acro.dev.propmgnt.exception.PropertyManagementException;
import com.acro.dev.propmgnt.repository.OwnerRepository;
import com.acro.dev.propmgnt.request.OwnerRequest;
import com.acro.dev.propmgnt.response.OwnerResponse;
import com.acro.dev.propmgnt.responsemethod.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements  OwnerService {

    private final OwnerRepository ownerRepository;
    private final CommonResponse commonResponse;
    private static final Logger LOGGER= LoggerFactory.getLogger(CommonExceptionHandler.class);
    public OwnerServiceImpl(@Autowired OwnerRepository ownerRepository,
                            @Autowired CommonResponse commonResponse) {

        this.ownerRepository = ownerRepository;
        this.commonResponse=commonResponse;
    }

    @Override
    public OwnerResponse createOwner(OwnerRequest ownerRequest) {
        // Convert request object to model entities
        LOGGER.info("Received request to Owner");
        Owner owner=new Owner();
        // owner.setOwnerId(ownerRequest.getOwnerId());
        owner.setOwnerFirstName(ownerRequest.getOwnerFirstName());
        owner.setOwnerLastName(ownerRequest.getOwnerLastName());
        owner.setOwnerEmail(ownerRequest.getOwnerEmail());
        owner.setOwnerPhoneNumber(ownerRequest.getOwnerPhoneNumber());
        owner.setEinNumber(ownerRequest.getEinNumber());
        owner = ownerRepository.save(owner);
        //convert owner to ownerResponse
        LOGGER.info("Saved Successfully");
        return commonResponse.getOwnerResponse(owner);

    }


    @Override
    public OwnerResponse updateOwner(Long id, OwnerRequest ownerRequest) {
        Optional<Owner> owner=ownerRepository.findById(id);
        if (owner.isPresent()) {
            Owner existingOwner = owner.get();
            existingOwner.setOwnerFirstName(ownerRequest.getOwnerFirstName());
            existingOwner.setOwnerLastName(ownerRequest.getOwnerLastName());
            existingOwner.setOwnerEmail(ownerRequest.getOwnerEmail());
            existingOwner.setOwnerPhoneNumber(ownerRequest.getOwnerPhoneNumber());
            Owner updated = ownerRepository.save(existingOwner);
            LOGGER.info("Updated Successfully");
            return commonResponse.getOwnerResponse(updated);
        }
        throw new PropertyManagementException("Owner not found");
    }

    @Override
    public OwnerResponse findOwnerById(Long id) {
        Optional<Owner> owner=ownerRepository.findById(id);
        if(owner.isPresent()){
            Owner ownerOne=owner.get();
            return commonResponse.getOwnerResponse(ownerOne);
        }
         throw new PropertyManagementException("Owner not found");
    }

    @Override
    public boolean deleteOwnerById(Long id) {
        Optional<Owner> owner=ownerRepository.findById(id);
        try{
            if(owner.isPresent()){
                Owner ownerOne=owner.get();
                return true;
            }
        }catch(Exception e){
            LOGGER.error("Failed to delete Owner Id");
        }
        return false;
    }

}