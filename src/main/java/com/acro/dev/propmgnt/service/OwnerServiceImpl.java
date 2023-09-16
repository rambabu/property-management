package com.acro.dev.propmgnt.service;

import com.acro.dev.propmgnt.controller.OwnerController;
import com.acro.dev.propmgnt.entity.Owner;
import com.acro.dev.propmgnt.repository.OwnerRepository;
import com.acro.dev.propmgnt.request.OwnerRequest;
import com.acro.dev.propmgnt.response.OwnerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerServiceImpl implements  OwnerService {

    private final OwnerRepository ownerRepository;
    private static final Logger LOGGER= LoggerFactory.getLogger(OwnerController.class);
    public OwnerServiceImpl(@Autowired OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    //Owner owner = new Owner();
    // EmployeeIdentificationNumber ein=new EmployeeIdentificationNumber();
    // OwnerResponse ownerResponse = new OwnerResponse();


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
        /*OwnerResponse ownerResponse = new OwnerResponse();
        ownerResponse.setOwnerId(owner.getOwnerId());
        ownerResponse.setOwnerFirstName(owner.getOwnerFirstName());
        ownerResponse.setOwnerLastName(owner.getOwnerLastName());
        ownerResponse.setOwnerEmail(owner.getOwnerEmail());
        ownerResponse.setOwnerPhoneNumber(ownerRequest.getOwnerPhoneNumber());*/
        //convert owner to ownerrespon
        LOGGER.info("Saved Successfully");
        return getOwnerResponse(owner);

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
            //existingOwner.setPropertyId(ownerRequest.getPropertyId());
            Owner updated = ownerRepository.save(existingOwner);
            LOGGER.info("Updated Successfully");
            return getOwnerResponse(updated);
        }
        return null;
    }

    private OwnerResponse getOwnerResponse(Owner owner) {
        OwnerResponse ownerResponse = new OwnerResponse();
        ownerResponse.setOwnerId(owner.getId());
        ownerResponse.setEinNumber(owner.getEinNumber());
        ownerResponse.setOwnerFirstName(owner.getOwnerFirstName());
        ownerResponse.setOwnerLastName(owner.getOwnerLastName());
        ownerResponse.setOwnerEmail(owner.getOwnerEmail());
        ownerResponse.setOwnerPhoneNumber(owner.getOwnerPhoneNumber());
       // ownerResponse.setPropertyId(owner.getPropertyId());
        return ownerResponse;

    }


    @Override
    public OwnerResponse findOwnerById(Long id) {
        Optional<Owner> owner=ownerRepository.findById(id);
        if(owner.isPresent()){
            Owner ownerOne=owner.get();
            return getOwnerResponse(ownerOne);
        }
         return null;
    }
    /*        public List<TenantResponse> getAllTenantsByPropertyId(Long propertyId) {
                List<Tenant> tenants = tenantRepository.findByPropertyId(propertyId);
            return tenants.stream().map(t -> getTenantResponse(t)).collect(Collectors.toList());
        }
*/

    /*@Override
    public List<OwnerResponse> getAllOwnersByPropertyId(Long propertyId) {
        List<Owner> owners=ownerRepository.findByPropertyId(propertyId);
        return owners.stream().map(o->getOwnerResponse(o)).collect(Collectors.toList());
    }*/

   /* @Override
    public OwnerResponse getAllOwnersByOwnerId(Long ownerId) {
        Optional<Owner>owner=ownerRepository.findById(ownerId);
        if(owner.isPresent()){
            Owner ownerOne=owner.get();
            return getOwnerResponse(ownerOne);
        }
          return null;
    }*/

    @Override
    public boolean deleteOwnerById(Long id) {
        try{
            ownerRepository.findById(id);
            return true;
        }catch(Exception e){
            LOGGER.error("Delete Failed");//prints exception
        }
        return false;
    }

}