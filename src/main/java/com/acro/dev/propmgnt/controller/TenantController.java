package com.acro.dev.propmgnt.controller;

import com.acro.dev.propmgnt.exception.PropertyManagementException;
import com.acro.dev.propmgnt.request.TenantRequest;
import com.acro.dev.propmgnt.response.TenantResponse;
import com.acro.dev.propmgnt.service.TenantService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/tenant")
public class TenantController {
    private static final Logger LOGGER= LoggerFactory.getLogger(TenantController.class);

    private final TenantService tenantService;

    public TenantController(@Autowired TenantService tenantService) {

        this.tenantService = tenantService;
    }

    @PostMapping("/")
    public ResponseEntity<TenantResponse> createTenant(@RequestBody @Valid @NotNull TenantRequest tenantReq) throws PropertyManagementException {
        try {
                TenantResponse tenantRes = tenantService.createTenant(tenantReq);
                if (tenantRes != null) return ResponseEntity.ok(tenantRes);
        } catch (Exception e) {
            LOGGER.error("Creation Failed", e);
            throw new PropertyManagementException("creation failed",e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }
    @GetMapping("/{id}")
    public ResponseEntity<TenantResponse> getById(@PathVariable Long id) {
        TenantResponse response = tenantService.getById(id);
        if(response !=null) return ResponseEntity.ok(response);
        LOGGER.error("Invalid Id");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<TenantResponse> updateTenant(@PathVariable Long id,
                                                       @RequestBody @Valid @NotNull TenantRequest tenantRequest){
            TenantResponse tenantRes = tenantService.updateTenant(id,tenantRequest);
            if(tenantRes !=null) return ResponseEntity.ok(tenantRes);
        LOGGER.error("Update Failed");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }
    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<TenantResponse>> getAllTenantsByPropertyId(@PathVariable Long propertyId) {
        List<TenantResponse> allTenants = tenantService.getAllTenantsByPropertyId(propertyId);
        if (allTenants != null) return ResponseEntity.ok(allTenants);
        return ResponseEntity.ok(Collections.emptyList());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTenantById(@PathVariable Long id){
        Boolean result = tenantService.deleteTenantById(id);
        LOGGER.info("Deleted Successfully");
        return ResponseEntity.ok(result);

       // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

