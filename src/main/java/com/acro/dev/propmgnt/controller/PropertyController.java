package com.acro.dev.propmgnt.controller;

import com.acro.dev.propmgnt.exception.PropertyManagementException;
import com.acro.dev.propmgnt.request.PropertyRequest;
import com.acro.dev.propmgnt.response.PropertyResponse;
import com.acro.dev.propmgnt.service.OwnerService;
import com.acro.dev.propmgnt.service.PropertyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/property")
public class PropertyController {
    public static final Logger LOGGER = LoggerFactory.getLogger(PropertyController.class);
    private final PropertyService propertyService;
    private final OwnerService ownerService;


    public PropertyController(@Autowired PropertyService propertyService, @Autowired OwnerService ownerService) {

        this.propertyService = propertyService;
        this.ownerService = ownerService;
    }

    @PostMapping("/")
    public ResponseEntity<PropertyResponse> createProperty
            (@RequestBody @Valid @NotNull PropertyRequest propertyRequest) throws PropertyManagementException {
        try {
            PropertyResponse propertyResponse = propertyService.createProperty(propertyRequest);
            if (propertyResponse != null) {
                return ResponseEntity.ok(propertyResponse);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to Create Property {}", propertyRequest.getAreaOfUnit());
            throw new PropertyManagementException("Failed to Create property {}" + propertyRequest.getAreaOfUnit());

        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyResponse> updateProperty(@PathVariable Long id,
                                                           @RequestBody @Valid @NotNull PropertyRequest propertyRequest) {
        PropertyResponse propertyResponse = propertyService.updateProperty(id, propertyRequest);
        if (propertyResponse != null) {
            return ResponseEntity.ok(propertyResponse);
        }
        LOGGER.error("Failed to update property {}", propertyRequest.getAreaOfUnit());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyResponse> findPropertyById(@PathVariable Long id) {
        PropertyResponse propertyResponse = propertyService.findPropertyById(id);
        if (propertyResponse != null) {
            return ResponseEntity.ok(propertyResponse);

        }
        LOGGER.error("Failed to find property Id {}", id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePropertyById(@PathVariable Long id) {
        try {
            PropertyResponse propertyResponse = propertyService.findPropertyById(id);
            if (propertyResponse != null) {
                boolean res = this.propertyService.deletePropertyById(id);
                return ResponseEntity.ok(res);
            }
        } catch (Exception e) {
            LOGGER.error("Not Deleted Successfully Property {} ", id);
            throw new PropertyManagementException("Failed to delete propertyId {}" + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }
}







