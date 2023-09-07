package com.acro.dev.propmgnt.service;

import com.acro.dev.propmgnt.controller.TenantController;
import com.acro.dev.propmgnt.entity.Profile;
import com.acro.dev.propmgnt.entity.Tenant;
import com.acro.dev.propmgnt.repository.TenantRepository;
import com.acro.dev.propmgnt.request.TenantRequest;
import com.acro.dev.propmgnt.response.TenantResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TenantServiceImpl implements TenantService {
        private static final Logger LOGGER= LoggerFactory.getLogger(TenantController.class);

        private final TenantRepository tenantRepository;

        public TenantServiceImpl(@Autowired TenantRepository tenantRepository) {
                this.tenantRepository = tenantRepository;
        }

        @Override
        public TenantResponse createTenant(TenantRequest tenantReq) {
                LOGGER.info("Received request to Tenant");
                // Convert request object to model entities
                Tenant tenant = new Tenant();
                Profile profile = new Profile();
                profile.setFName(tenantReq.getFirstName());
                profile.setLName(tenantReq.getLastName());
                profile.setEmail(tenantReq.getEmail());
                profile.setPhoneNumber(tenantReq.getPhoneNumber());
                profile.setSsn(tenantReq.getSsn());
                tenant.setProfile(profile);
                tenant.setPropertyId(tenantReq.getPropertyId());
                // save model to DB
                Tenant tenant1 = tenantRepository.save(tenant);
                // convert model to response object
                LOGGER.info("Saved Successfully");
                return getTenantResponse(tenant1);
        }

        private  TenantResponse getTenantResponse(Tenant tenant1) {
                TenantResponse tenantResponse = new TenantResponse();
                tenantResponse.setTenantId(tenant1.getId());
                tenantResponse.setProfileId(tenant1.getProfile().getId());
                tenantResponse.setFName(tenant1.getProfile().getFName());
                tenantResponse.setLName(tenant1.getProfile().getLName());
                tenantResponse.setEmail(tenant1.getProfile().getEmail());
                tenantResponse.setSsn(tenant1.getProfile().getSsn());
                tenantResponse.setPhoneNumber(tenant1.getProfile().getPhoneNumber());
                tenantResponse.setPropertyId(tenant1.getPropertyId());
                return tenantResponse;
        }

        @Override
        public TenantResponse updateTenant(Long id,TenantRequest tenantReq) {
                // Check if tenant is present
                Optional<Tenant> tenant = tenantRepository.findById(id);
                if (tenant.isPresent()) {
                        // set values from request to tenant model
                        Tenant tenant1 = tenant.get();
                        Profile profile = tenant1.getProfile();
                        profile.setFName(tenantReq.getFirstName());
                        profile.setLName(tenantReq.getLastName());
                        profile.setEmail(tenantReq.getEmail());
                        profile.setPhoneNumber(tenantReq.getPhoneNumber());
                        profile.setSsn(tenantReq.getSsn());
                        tenant1.setPropertyId(tenantReq.getPropertyId());
                        // call save to update the DB record
                        Tenant tenant2 = tenantRepository.save(tenant1);
                        // convert the result to response object
                        LOGGER.info("Updated Successfully");
                        return getTenantResponse(tenant2);
                }

                return null;
        }

        @Override
        public TenantResponse getById(Long id) {
                Optional<Tenant> tenant = tenantRepository.findById(id);
                if (tenant.isPresent()) {
                        Tenant tenant1=tenant.get();
                        return getTenantResponse(tenant1);
                }
                return null;  // throw create not found exception
        }
        public List<TenantResponse> getAllTenantsByPropertyId(Long propertyId) {
                List<Tenant> tenants = tenantRepository.findByPropertyId(propertyId);
            return tenants.stream().map(t -> getTenantResponse(t)).collect(Collectors.toList());
        }
        public boolean deleteTenantById(Long id) {
                try {
                        tenantRepository.deleteById(id);
                        return  true;
                }
                catch (Exception e){
                        LOGGER.error("Delete Failed");           //prints Exception
                }
                return false;
        }



}

