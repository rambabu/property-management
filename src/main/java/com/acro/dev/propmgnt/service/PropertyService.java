package com.acro.dev.propmgnt.service;
import com.acro.dev.propmgnt.request.PropertyRequest;
import com.acro.dev.propmgnt.response.PropertyResponse;

public interface PropertyService {
    public PropertyResponse createProperty(PropertyRequest propertyRequest);
    public PropertyResponse updateProperty(Long id,PropertyRequest propertyRequest);
    public PropertyResponse findPropertyById(Long id);
    public boolean deletePropertyById(Long id);
}
