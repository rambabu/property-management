package com.acro.dev.propmgnt.controller;
import com.acro.dev.propmgnt.advice.CommonExceptionHandler;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonExceptionHandler.class);

    private final OwnerService ownerService;

    public OwnerController(@Autowired OwnerService ownerService) {

        this.ownerService = ownerService;
    }


    @PostMapping("/")
    public ResponseEntity<OwnerResponse> createOwner(@RequestBody @Valid @NotNull OwnerRequest ownerRequest) {
        if (ownerRequest != null) {
            OwnerResponse ownerResponse = ownerService.createOwner(ownerRequest);
            if (ownerResponse != null) {
                return ResponseEntity.ok(ownerResponse);
            }
        }
            LOGGER.error("Create Failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    @PutMapping("/{id}")
    public ResponseEntity<OwnerResponse> updateOwner(@PathVariable Long id,
                                                     @RequestBody @Valid @NotNull OwnerRequest ownerRequest) {
        if (ownerRequest != null) {
            OwnerResponse ownerResponse = ownerService.updateOwner(id, ownerRequest);
            if (ownerResponse != null) {
                return ResponseEntity.ok(ownerResponse);
            }
        }
            LOGGER.error("Update Failed");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }
    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponse> findOwnerById(@PathVariable Long id) {
        OwnerResponse ownerResponse = ownerService.findOwnerById(id);
        if (ownerResponse != null) {
            return ResponseEntity.ok(ownerResponse);
        }
        LOGGER.error("Invalid ownerId");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOwnerById(@PathVariable Long id) {
        boolean res = this.ownerService.deleteOwnerById(id);
        LOGGER.info("Owner Deleted Successfully");
        return ResponseEntity.ok(res);
    }

}
