package com.acro.dev.propmgnt.controller;

import com.acro.dev.propmgnt.exception.PropertyManagementException;
import com.acro.dev.propmgnt.request.OwnerRequest;
import com.acro.dev.propmgnt.response.OwnerResponse;
import com.acro.dev.propmgnt.service.OwnerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/owner")
public class OwnerController {
    //private static final Logger LOGGER = LoggerFactory.getLogger(CommonExceptionHandler.class);
    private static final Logger LOGGER = LoggerFactory.getLogger(OwnerController.class);

    private final OwnerService ownerService;

    public OwnerController(@Autowired OwnerService ownerService) {

        this.ownerService = ownerService;
    }


    @PostMapping("/")
    public ResponseEntity<OwnerResponse> createOwner(@RequestBody @Valid @NotNull OwnerRequest ownerRequest)
            throws PropertyManagementException {
        try {
            OwnerResponse ownerResponse = ownerService.createOwner(ownerRequest);
            if (ownerResponse != null) {
                return ResponseEntity.ok(ownerResponse);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to create Owner name {} ", ownerRequest.getOwnerFirstName());
            throw new PropertyManagementException("Creation Failed ", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerResponse> updateOwner(@PathVariable Long id,
                                                     @RequestBody @Valid @NotNull OwnerRequest ownerRequest) {
        OwnerResponse ownerResponse = ownerService.updateOwner(id, ownerRequest);
        if (ownerResponse != null) {
            return ResponseEntity.ok(ownerResponse);
        }
        LOGGER.error("Failed to update Owner name {} ", ownerRequest.getOwnerFirstName());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponse> findOwnerById(@PathVariable Long id) {
        OwnerResponse ownerResponse = ownerService.findOwnerById(id);
        if (ownerResponse != null) {
            return ResponseEntity.ok(ownerResponse);
        }
        LOGGER.error("Failed to find Owner id{}",id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOwnerById(@PathVariable Long id) {
        OwnerResponse ownerResponse = ownerService.findOwnerById(id);
        if (ownerResponse != null) {
            boolean res = this.ownerService.deleteOwnerById(id);
            return ResponseEntity.ok(res);
        }
        LOGGER.error("Not Deleted Successfully Owner {} ",id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

