package com.acro.dev.propmgnt;

import com.acro.dev.propmgnt.entity.*;
import com.acro.dev.propmgnt.response.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommonResponseMapper {
    public PropertyManagerResponse getPropertyManagerResponse(PropertyManager propertyManager){
        PropertyManagerResponse propertyManagerResponse=new PropertyManagerResponse();
        propertyManagerResponse.setManagerId(propertyManager.getId());
        propertyManagerResponse.setManagerName(propertyManager.getManagerName());
        propertyManagerResponse.setManagerEmail(propertyManager.getManagerEmail());
        propertyManagerResponse.setManagerPhoneNumber(propertyManager.getManagerPhoneNumber());
        return propertyManagerResponse;
    }
    public OwnerResponse getOwnerResponse(Owner owner) {
        OwnerResponse ownerResponse = new OwnerResponse();
        ownerResponse.setManagerId(owner.getPropertyManager().getId());
        ownerResponse.setOwnerId(owner.getId());
        ownerResponse.setEinNumber(owner.getEinNumber());
        ownerResponse.setOwnerFirstName(owner.getOwnerFirstName());
        ownerResponse.setOwnerLastName(owner.getOwnerLastName());
        ownerResponse.setOwnerEmail(owner.getOwnerEmail());
        ownerResponse.setOwnerPhoneNumber(owner.getOwnerPhoneNumber());
        return ownerResponse;
    }

    public PropertyResponse getPropertyResponse(Property property) {
        PropertyResponse propertyResponse = new PropertyResponse();
        propertyResponse.setPropertyId(property.getId());
        propertyResponse.setOwnerId(property.getOwner().getId());
        propertyResponse.setAddressId(property.getAddress().getId());
        propertyResponse.setAreaOfUnit(property.getAreaOfUnit());
        propertyResponse.setNoOfBeds(property.getNoOfBeds());
        propertyResponse.setNoOfBaths(property.getNoOfBaths());
        propertyResponse.setBedRoomDimension(property.getBedRoomDimension());
        propertyResponse.setKitchenDimension(property.getKitchenDimension());
        propertyResponse.setHallDimension(property.getHallDimension());
        propertyResponse.setGarageDimension(property.getGarageDimension());
        propertyResponse.setRent(property.getRent());
        return propertyResponse;
    }

    public WorkOrderResponse getWorkOrderResponse(WorkOrder workOrder) {
        WorkOrderResponse workOrderResponse = new WorkOrderResponse();
        workOrderResponse.setWorkOrderId(workOrder.getId());
        workOrderResponse.setPropertyId(workOrder.getProperty().getId());
        //workOrderResponse.setTenantId(workOrder.getTenant().getId());
        workOrderResponse.setProblemDescription((workOrder.getProblemDescription()));
        workOrderResponse.setTypeOfWorkOrder(workOrder.getTypeOfWorkOrder());
        workOrderResponse.setWoDate(workOrder.getWoDate());
        workOrderResponse.setWoStartDate(workOrder.getWoStartDate());
        workOrderResponse.setWoEndDate(workOrder.getWoEndDate());
        workOrderResponse.setPermissionToEnter(workOrder.getPermissionToEnter());
        return workOrderResponse;


    }

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
        addressResponse.setOwnerId(address.getOwner().getId());
        return addressResponse;
    }
    public ProfileResponse convertToProfileResponse(Profile profile){
        ProfileResponse profileResponse = new ProfileResponse();
        BeanUtils.copyProperties(profile,profileResponse);
        profileResponse.setId(profile.getId());
        return profileResponse;
    }
}
