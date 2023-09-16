package com.acro.dev.propmgnt.controller;

import com.acro.dev.propmgnt.request.PropertyRequest;
import com.acro.dev.propmgnt.response.PropertyResponse;
import com.acro.dev.propmgnt.service.PropertyService;
import jakarta.validation.Valid;
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


    public PropertyController(@Autowired PropertyService propertyService) {

        this.propertyService = propertyService;
    }

    @PostMapping("/")
    public ResponseEntity<PropertyResponse> createProperty
            (@RequestBody @Valid PropertyRequest propertyRequest) {
        if (propertyRequest != null) {
            PropertyResponse propertyResponse = propertyService.createProperty(propertyRequest);
            if (propertyResponse != null)
                return ResponseEntity.ok(propertyResponse);
        }
        LOGGER.info("Failed to Create");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyResponse> updateProperty(@PathVariable Long id,
                                                           @RequestBody PropertyRequest propertyRequest) {
        if (propertyRequest != null) {
            PropertyResponse propertyResponse = propertyService.updateProperty(id, propertyRequest);
            if (propertyResponse != null)
                return ResponseEntity.ok(propertyResponse);
        }
        LOGGER.info("Failed to update");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<PropertyResponse> findPropertyById(@PathVariable Long id) {
        PropertyResponse propertyResponse = propertyService.findPropertyById(id);
        if (propertyResponse != null)
            return ResponseEntity.ok(propertyResponse);

        LOGGER.error("Invalid propertyId");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOwnerById(@PathVariable Long id) {
        boolean res = this.propertyService.deletePropertyById(id);
        LOGGER.error("Not Deleted Successfully");
        return ResponseEntity.ok(res);

    }
}