package com.acro.dev.propmgnt.controller;

import com.acro.dev.propmgnt.exception.PropertyManagementException;
import com.acro.dev.propmgnt.request.AddressRequest;
import com.acro.dev.propmgnt.response.AddressResponse;
import com.acro.dev.propmgnt.service.AddressService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
    private final AddressService addressService;

    public AddressController(@Autowired AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/")
    public ResponseEntity<AddressResponse> createLease(@RequestBody @Valid @NotNull AddressRequest addressRequest) throws PropertyManagementException {
        try {
            AddressResponse addressResponse = addressService.createAddress(addressRequest);
            if (addressResponse != null) return ResponseEntity.ok(addressResponse);
        } catch (Exception e) {
            logger.error("Creation Failed", e);
            throw new PropertyManagementException("creation failed", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getById(@PathVariable Long id) {
        AddressResponse response = addressService.getById(id);
        if (response != null) return ResponseEntity.ok(response);
        logger.error("Invalid Id");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable Long id,
                                                         @RequestBody @Valid @NotNull AddressRequest addressRequest) {
        AddressResponse tenantRes = addressService.updateAddress(id, addressRequest);
        if (tenantRes != null) return ResponseEntity.ok(tenantRes);
        logger.error("Update Failed");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAddressById(@PathVariable Long id) {
        Boolean result = addressService.deleteAddressById(id);
        logger.info("Deleted Successfully");
        return ResponseEntity.ok(result);

    }
}
