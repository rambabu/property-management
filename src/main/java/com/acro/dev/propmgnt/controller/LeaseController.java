package com.acro.dev.propmgnt.controller;

import com.acro.dev.propmgnt.exception.PropertyManagementException;
import com.acro.dev.propmgnt.request.LeaseRequest;
import com.acro.dev.propmgnt.response.LeaseResponse;
import com.acro.dev.propmgnt.service.LeaseService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lease")
public class LeaseController {
    private static final Logger logger = LoggerFactory.getLogger(LeaseController.class);
    private final LeaseService leaseService;

    public LeaseController(@Autowired LeaseService leaseService) {

        this.leaseService = leaseService;
    }
    @PostMapping("/")
    public ResponseEntity<LeaseResponse> createLease(@RequestBody @Valid LeaseRequest leaseRequest) throws PropertyManagementException {
        try {
            if (leaseRequest != null) {
                LeaseResponse leaseResponse = leaseService.createLease(leaseRequest);
                if (leaseResponse != null) {
                    return ResponseEntity.ok(leaseResponse);
                }
            }
        } catch (Exception e) {
            logger.error("Create failed", e);
            throw new PropertyManagementException("creation failed", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<LeaseResponse> updateLease(@PathVariable Long id,
                                                     @RequestBody @Valid LeaseRequest leaseRequest) {
        if (leaseRequest != null) {
            LeaseResponse leaseRes = leaseService.updateLease(id, leaseRequest);
            if (leaseRes != null) {
                return ResponseEntity.ok(leaseRes);
            }
        }
        logger.error("Update Failed");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteLeaseById(@PathVariable Long id) {
        Boolean result = leaseService.deleteLeaseById(id);
        logger.info("Deleted Successfully");
        return ResponseEntity.ok(result);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LeaseResponse> getLeaseByTenantId(@PathVariable Long tenantId) {
        LeaseResponse response = leaseService.getLeaseByTenantId(tenantId);
        if(response !=null) {
            return ResponseEntity.ok(response);
        }
        logger.error("Invalid Id");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}