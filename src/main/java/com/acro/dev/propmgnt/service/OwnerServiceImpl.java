package com.acro.dev.propmgnt.service;

import com.acro.dev.propmgnt.CommonResponseMapper;
import com.acro.dev.propmgnt.entity.Owner;
import com.acro.dev.propmgnt.entity.PropertyManager;
import com.acro.dev.propmgnt.exception.PropertyManagementException;
import com.acro.dev.propmgnt.repository.OwnerRepository;
import com.acro.dev.propmgnt.repository.PropertyManagerRepository;
import com.acro.dev.propmgnt.request.OwnerRequest;
import com.acro.dev.propmgnt.response.OwnerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements  OwnerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OwnerServiceImpl.class);
    private final OwnerRepository ownerRepository;
    private final PropertyManagerRepository propertyManagerRepository;
    private final CommonResponseMapper commonResponseMapper;

    public OwnerServiceImpl(@Autowired OwnerRepository ownerRepository,
                            PropertyManagerRepository propertyManagerRepository, @Autowired CommonResponseMapper commonResponseMapper) {

        this.ownerRepository = ownerRepository;
        this.propertyManagerRepository = propertyManagerRepository;
        this.commonResponseMapper = commonResponseMapper;
    }


    @Override
    @Transactional
    public OwnerResponse createOwner(OwnerRequest ownerRequest) {
        // Convert request object to model entities
        LOGGER.info("Received request to Owner");
        Owner owner = new Owner();
        Optional<PropertyManager> propertyManager = propertyManagerRepository.findById(ownerRequest.getManagerId());
        if (propertyManager.isPresent()) {
            PropertyManager propertyManagerOne = propertyManager.get();
            //  PropertyManager propertyManagerObj=new PropertyManager();
            owner.setOwnerFirstName(ownerRequest.getOwnerFirstName());
            owner.setOwnerLastName(ownerRequest.getOwnerLastName());
            owner.setOwnerEmail(ownerRequest.getOwnerEmail());
            owner.setOwnerPhoneNumber(ownerRequest.getOwnerPhoneNumber());
            owner.setEinNumber(ownerRequest.getEinNumber());
            owner.setPropertyManager(propertyManager.get());
            Owner ownerOne = ownerRepository.save(owner);
            //convert owner to ownerResponse
            LOGGER.info("Successfully saved Owner {}", ownerRequest.getOwnerFirstName());
            return commonResponseMapper.getOwnerResponse(ownerOne);

        } else {
            throw new PropertyManagementException("Owner not created");
        }
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
            LOGGER.info("Updated Successfully");
            return commonResponseMapper.getOwnerResponse(updated);
        }
        throw new PropertyManagementException("Owner not found");
    }


    @Override
    public OwnerResponse findOwnerById(Long id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isPresent()) {
            Owner ownerOne = owner.get();
            return commonResponseMapper.getOwnerResponse(ownerOne);
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
                LOGGER.error("Failed to delete Owner Id");
            }
            return false;
        }

    }

    @Override
    public OwnerResponse generateFileByOwnerId(Long id) throws Exception {
        // final Resource fileResource=resourceLoader.getResource("classpath.store/"+fileName);
        // return fileResource;Owner> owner=findOwnerById(id);
        //OwnerResponse owner=findOwnerById(id);
        Optional<Owner> owner = ownerRepository.findById(id);
        //   Owner ownerOne = null;
        Owner ownerOne = null;
        if (owner.isPresent()) {
            ownerOne = owner.get();
            try {
                File file = new File("C:\\Users\\default.LAPTOP-V169K058\\OneDrive\\Desktop\\New folder\\Sample.txt");
                if (file.createNewFile()) {
                    FileWriter myWriter = new FileWriter("C:\\Users\\default.LAPTOP-V169K058\\OneDrive\\Desktop\\New folder\\Sample.txt");
                    myWriter.write(ownerOne.toString());
                    LOGGER.info("Created new file");
                } else {
                    System.out.println("File already exists");
                }
            } catch (IOException e) {
                LOGGER.error("An error occurred.");

            }
        }

        return commonResponseMapper.getOwnerResponse(ownerOne);
    }

}