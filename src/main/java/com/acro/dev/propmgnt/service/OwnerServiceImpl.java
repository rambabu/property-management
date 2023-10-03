package com.acro.dev.propmgnt.service;

import com.acro.dev.propmgnt.entity.Owner;
import com.acro.dev.propmgnt.exception.PropertyManagementException;
import com.acro.dev.propmgnt.repository.OwnerRepository;
import com.acro.dev.propmgnt.request.OwnerRequest;
import com.acro.dev.propmgnt.response.OwnerResponse;
import com.acro.dev.propmgnt.responsemethod.CommonResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OwnerServiceImpl implements  OwnerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OwnerServiceImpl.class);
    private final OwnerRepository ownerRepository;
    private final CommonResponseMapper commonResponseMapper;

    public OwnerServiceImpl(@Autowired OwnerRepository ownerRepository, CommonResponseMapper commonResponseMapper) {

        this.ownerRepository = ownerRepository;
        this.commonResponseMapper = commonResponseMapper;
    }


    @Override
    @Transactional
    public OwnerResponse createOwner(OwnerRequest ownerRequest) {
        // Convert request object to model entities
        LOGGER.info("Received owner request {}", ownerRequest);

        Owner owner = new Owner();
        owner.setOwnerFirstName(ownerRequest.getOwnerFirstName());
        owner.setOwnerLastName(ownerRequest.getOwnerLastName());
        owner.setOwnerEmail(ownerRequest.getOwnerEmail());
        owner.setOwnerPhoneNumber(ownerRequest.getOwnerPhoneNumber());
        owner.setEinNumber(ownerRequest.getEinNumber());
        owner = ownerRepository.save(owner);
        //convert owner to ownerResponse
        LOGGER.info("Successfully saved Owner {}", ownerRequest.getOwnerFirstName());
        return commonResponseMapper.getOwnerResponse(owner);

    }


    @Override
    @Transactional
    public OwnerResponse updateOwner(Long id, OwnerRequest ownerRequest) {
        Optional<Owner> owner = ownerRepository.findById(id);

        if (owner.isPresent()) {
            Owner existingOwner = owner.get();
            existingOwner.setOwnerFirstName(ownerRequest.getOwnerFirstName());
            existingOwner.setOwnerLastName(ownerRequest.getOwnerLastName());
            existingOwner.setOwnerEmail(ownerRequest.getOwnerEmail());
            existingOwner.setOwnerPhoneNumber(ownerRequest.getOwnerPhoneNumber());
            Owner updated = ownerRepository.save(existingOwner);
            LOGGER.info("Successfully Updated Owner {} ", ownerRequest.getOwnerFirstName());
            return commonResponseMapper.getOwnerResponse(updated);
        }
        throw new PropertyManagementException("Owner not found");
    }


    @Override

    public OwnerResponse findOwnerById(Long id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isPresent()) {
            CommonResponseMapper commonResponse = new CommonResponseMapper();
            Owner ownerOne = owner.get();
            return commonResponse.getOwnerResponse(ownerOne);
        }
        throw new PropertyManagementException("Owner not found");
    }

    @Override
    public boolean deleteOwnerById(Long id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isEmpty()) {
            LOGGER.error("failed to delete OwnerId");
            throw new PropertyManagementException("Owner not found");
        } else {
            try {
                Owner callOwner = owner.get();
                ownerRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                LOGGER.error("Failed to delete Owner Id {}", id);
                throw new PropertyManagementException("failed to delete OwnerId");
            }


        }
    }
}