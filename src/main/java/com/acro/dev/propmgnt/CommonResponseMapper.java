package com.acro.dev.propmgnt;

import com.acro.dev.propmgnt.entity.Address;
import com.acro.dev.propmgnt.entity.LeaseDetails;
import com.acro.dev.propmgnt.entity.Profile;
import com.acro.dev.propmgnt.entity.Tenant;
import com.acro.dev.propmgnt.response.AddressResponse;
import com.acro.dev.propmgnt.response.LeaseResponse;
import com.acro.dev.propmgnt.response.ProfileResponse;
import com.acro.dev.propmgnt.response.TenantResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommonResponseMapper {
    public TenantResponse convertToTenantResponse(Tenant tenant1) {
        TenantResponse tenantResponse = new TenantResponse();
        tenantResponse.setTenantId(tenant1.getId());
        tenantResponse.setProfileId(tenant1.getProfile().getId());
        tenantResponse.setFName(tenant1.getProfile().getFirstName());
        tenantResponse.setLName(tenant1.getProfile().getLastName());
        tenantResponse.setEmail(tenant1.getProfile().getEmail());
        tenantResponse.setSsn(tenant1.getProfile().getSsn());
        tenantResponse.setPhoneNumber(tenant1.getProfile().getPhoneNumber());
        // tenantResponse.setPropertyId(tenant1.getPropertyId());
        return tenantResponse;
    }
    public LeaseResponse convertToLeaseResponse(LeaseDetails leaseDetails ) {
        LeaseResponse leaseResponse = new LeaseResponse();
        BeanUtils.copyProperties(leaseDetails, leaseResponse);  //It will copy the properties with same Name.
        leaseResponse.setLeaseId(leaseDetails.getId());
        leaseResponse.setPropertyId(leaseDetails.getProperty().getId());
        return leaseResponse;
    }
    public LeaseResponse convertToLeaseAndTenantResponse(LeaseDetails leaseDetails, List<Tenant> tenantList) {
        LeaseResponse leaseResponse = new LeaseResponse();
        BeanUtils.copyProperties(leaseDetails, leaseResponse);  //It will copy the properties with same Name.
        List<TenantResponse> tenantResponses = tenantList.stream().map(tenant -> convertToTenantResponse(tenant)).collect(Collectors.toList());
        leaseResponse.setLeaseId(leaseDetails.getId());
        leaseResponse.setPropertyId(leaseDetails.getProperty().getId());
        leaseResponse.setTenants(tenantResponses);
        return leaseResponse;
    }
    public AddressResponse convertToAddressResponse(Address address){
        AddressResponse addressResponse = new AddressResponse();
        BeanUtils.copyProperties(address,addressResponse);
        addressResponse.setAddressId(address.getId());
        return addressResponse;
    }
    public ProfileResponse convertToProfileResponse(Profile profile){
        ProfileResponse profileResponse = new ProfileResponse();
        BeanUtils.copyProperties(profile,profileResponse);
        profileResponse.setId(profile.getId());
        return profileResponse;
    }
}
