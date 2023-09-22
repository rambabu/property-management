package com.acro.dev.propmgnt.service;

import com.acro.dev.propmgnt.CommonResponseMapper;
import com.acro.dev.propmgnt.entity.LeaseDetails;
import com.acro.dev.propmgnt.entity.Profile;
import com.acro.dev.propmgnt.entity.Property;
import com.acro.dev.propmgnt.entity.Tenant;
import com.acro.dev.propmgnt.exception.PropertyManagementException;
import com.acro.dev.propmgnt.repository.LeaseRepository;
import com.acro.dev.propmgnt.repository.PropertyRepository;
import com.acro.dev.propmgnt.repository.TenantRepository;
import com.acro.dev.propmgnt.request.LeaseRequest;
import com.acro.dev.propmgnt.response.LeaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LeaseServiceImpl implements LeaseService {
    private static final Logger LOGGER= LoggerFactory.getLogger(LeaseServiceImpl.class);

    private final LeaseRepository leaseRepository;
    private final PropertyRepository propertyRepository;
    private final TenantRepository tenantRepository;
    private final CommonResponseMapper commonResponseMapper;
    public LeaseServiceImpl(@Autowired LeaseRepository leaseRepository, PropertyRepository propertyRepository, TenantRepository tenantRepository,CommonResponseMapper commonResponseMapper) {
        this.leaseRepository = leaseRepository;
        this.propertyRepository = propertyRepository;
        this.tenantRepository = tenantRepository;
        this.commonResponseMapper=commonResponseMapper;
    }
    @Override
    public LeaseResponse createLease(LeaseRequest leaseReq) {
        LOGGER.info("Received lease request{}",leaseReq);
        // Validation not null,
        // property exists - getPropertyById()....
        // there is no active lease...
        LeaseDetails leaseDetails=new LeaseDetails();
        if (leaseReq!=null){
            Optional<Property> propertyOptional = propertyRepository.findById(leaseReq.getPropertyId());
            if(propertyOptional.isPresent()){
                leaseDetails.setLeaseDate(leaseReq.getLeaseDate());
                leaseDetails.setLeaseStartDate(leaseReq.getLeaseStartDate());
                leaseDetails.setLeaseEndDate(leaseReq.getLeaseEndDate());
                leaseDetails.setLeaseStatus(leaseReq.isLeaseStatus());
                leaseDetails.setProperty(propertyOptional.get());
                leaseDetails.setLeaseTerm(leaseReq.getLeaseTerm());
                leaseDetails.setMonthlyRent(leaseReq.getMonthlyRent());
                leaseDetails.setPetDeposit(leaseReq.getPetDeposit());
                leaseDetails.setInsured(leaseReq.isInsured());
                leaseDetails.setPets(leaseReq.getPets());
                leaseDetails.setPetDeposit(leaseReq.getPetDeposit());
                leaseDetails.setNoOfPeople(leaseReq.getNoOfPeople());
                leaseDetails.setSecurityDepositAmount(leaseReq.getSecurityDepositAmount());
                LeaseDetails leaseDetails1=leaseRepository.save(leaseDetails);
                LOGGER.info("Saved Successfully");
                List<Tenant> tenants= createTenants(leaseReq.getProfiles(),leaseDetails1,propertyOptional.get());
                return commonResponseMapper.convertToLeaseResponse(leaseDetails1);
            }
        }
        throw new PropertyManagementException("Error processing Lease creation");
    }

    private List<Tenant> createTenants(List<Profile> profiles, LeaseDetails leaseDetails1, Property property) {

        List<Tenant> tenantList = profiles.stream().map(profile -> {
            Tenant tenant = new Tenant();
            tenant.setProfile(profile);
            tenant.setLease(leaseDetails1);
            tenant.setProperty(property);
            return tenant;
            ///tenant(profile)
        }).collect(Collectors.toList());
        List<Tenant> tenants = tenantRepository.saveAll(tenantList);
        LOGGER.info("Tenant Saved Successfully{}", tenants);
        return tenants;
    }

    @Override
    public LeaseResponse updateLease(Long id, LeaseRequest leaseReq) {
        Optional<LeaseDetails> leaseDetails=leaseRepository.findById(id);
        if(leaseDetails.isPresent()){
            LeaseDetails leaseDetails1=leaseDetails.get();
            BeanUtils.copyProperties(leaseReq,leaseDetails1);
            LeaseDetails savedLease = leaseRepository.save(leaseDetails1);
            LOGGER.info("lease Saved Successfully{}",savedLease);
            return commonResponseMapper.convertToLeaseResponse(savedLease);
        }
        throw new PropertyManagementException("Error processing Lease update");
    }
    @Override
    public Boolean deleteLeaseById(Long id) {
        try {
            Optional<LeaseDetails> leaseDetails = leaseRepository.findById(id);
            if (leaseDetails.isPresent()) {
                LeaseDetails newLease = leaseDetails.get();
                leaseRepository.deleteById(id);
                LOGGER.debug(" lease deleted successfully{}", newLease);
                return true;
            }
        }
        catch(Exception e){
                LOGGER.error("Unable delete Lease", e);
        }
       throw new PropertyManagementException("Unable to Delete");
    }
    @Override
    public LeaseResponse getLeaseByTenantId(Long id) {
        Optional<Tenant> tenant= tenantRepository.findById(id);
            if(tenant.isPresent()){
                Tenant tenant1=tenant.get();
                LeaseDetails tenantLease = tenant1.getLease();
                return commonResponseMapper.convertToLeaseResponse(tenantLease);
            }
        throw new PropertyManagementException("Lease details not found");
    }

}
