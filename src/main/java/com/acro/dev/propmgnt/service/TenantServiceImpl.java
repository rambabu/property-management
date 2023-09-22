package com.acro.dev.propmgnt.service;

import com.acro.dev.propmgnt.CommonResponseMapper;
import com.acro.dev.propmgnt.entity.Profile;
import com.acro.dev.propmgnt.entity.Tenant;
import com.acro.dev.propmgnt.exception.PropertyManagementException;
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
        private static final Logger LOGGER= LoggerFactory.getLogger(TenantServiceImpl.class);

        private final TenantRepository tenantRepository;
        private final CommonResponseMapper commonResponseMapper;

        public TenantServiceImpl(@Autowired TenantRepository tenantRepository, CommonResponseMapper commonResponseMapper1) {
                this.tenantRepository = tenantRepository;
                this.commonResponseMapper = commonResponseMapper1;
        }

        @Override
        public TenantResponse createTenant(TenantRequest tenantReq) {
                LOGGER.info("Received tenant request {}", tenantReq);
                Tenant tenant = new Tenant();
                Profile profile = new Profile();
                profile.setFName(tenantReq.getFirstName());     // Convert request object to model entities
                profile.setLName(tenantReq.getLastName());
                profile.setEmail(tenantReq.getEmail());
                profile.setPhoneNumber(tenantReq.getPhoneNumber());
                profile.setSsn(tenantReq.getSsn());
                tenant.setProfile(profile);
               // tenant.setPropertyId(tenantReq.getPropertyId());
                Tenant tenant1 = tenantRepository.save(tenant);   // save model to DB
                LOGGER.info("Saved Successfully");
                return commonResponseMapper.convertToTenantResponse(tenant1);      // convert model to response object
        }

        @Override
        public TenantResponse updateTenant(Long id,TenantRequest tenantReq) {
                // Check if tenant is present
                Optional<Tenant> tenant = tenantRepository.findById(id);
                if (tenant.isPresent()) {
                        Tenant tenant1 = tenant.get();  // set values from request to tenant model
                        Profile profile = tenant1.getProfile();
                        profile.setFName(tenantReq.getFirstName());
                        profile.setLName(tenantReq.getLastName());
                        profile.setEmail(tenantReq.getEmail());
                        profile.setPhoneNumber(tenantReq.getPhoneNumber());
                        profile.setSsn(tenantReq.getSsn());
                       // tenant1.setPropertyId(tenantReq.getPropertyId());
                        Tenant tenant2 = tenantRepository.save(tenant1);         // call save to update the DB record
                        LOGGER.info("Tenant updated Successfully {}", tenant2);
                        return commonResponseMapper.convertToTenantResponse(tenant2);      // convert the result to response object
                }
               throw new PropertyManagementException("Tenant not found");
        }


        @Override
        public TenantResponse getById(Long id) {
                Optional<Tenant> tenant = tenantRepository.findById(id);
                if (tenant.isPresent()) {
                        Tenant tenant1=tenant.get();
                        return commonResponseMapper.convertToTenantResponse(tenant1);
                }
                throw new PropertyManagementException("Tenant not found");
        }
        public List<TenantResponse> getAllTenantsByPropertyId(Long propertyId) {
                List<Tenant> tenants = tenantRepository.findByPropertyId(propertyId);
            return tenants.stream().map(t -> commonResponseMapper.convertToTenantResponse(t)).collect(Collectors.toList());
        }
        public boolean deleteTenantById(Long id) {
            Optional<Tenant> tenant = tenantRepository.findById(id);
            if (tenant.isEmpty()) {
                LOGGER.error("Delete Failed");
                throw new PropertyManagementException("Tenant not found");
            } else {
                try {
                    Tenant newtenant = tenant.get();
                    tenantRepository.deleteById(id);
                    return true;
                } catch (Exception e) {
                    LOGGER.error("Unable to delete tenant", e);
                    throw new PropertyManagementException("Unable to delete tenant", e);
                }
            }
        }


}

