package com.acro.dev.propmgnt.controller;

import com.acro.dev.propmgnt.exception.PropertyManagementException;
import com.acro.dev.propmgnt.request.ProfileRequest;
import com.acro.dev.propmgnt.response.ProfileResponse;
import com.acro.dev.propmgnt.service.ProfileService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
    private final ProfileService profileService;
    public ProfileController(@Autowired ProfileService profileService){
        this.profileService= profileService;
    }
    @PostMapping("/")
    public ResponseEntity<ProfileResponse> createProfile(@RequestBody @Valid @NotNull ProfileRequest profileRequest)throws PropertyManagementException {
        try {
            ProfileResponse profileResponse = profileService.createProfile(profileRequest);
            if (profileResponse != null) {
                return ResponseEntity.ok(profileResponse);
            }
        }
        catch(Exception e){
                logger.error("profile creation failed",e);
                throw new PropertyManagementException("profile creation failed",e);
            }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }
    @PutMapping("/{id}")
    public ResponseEntity<ProfileResponse> updateProfile(@PathVariable Long id,
                                                     @RequestBody @Valid ProfileRequest profileRequest) {
        if (profileRequest != null) {
            ProfileResponse profileRes = profileService.updateProfile(id, profileRequest);
            if (profileRes != null) {
                return ResponseEntity.ok(profileRes);
            }
        }
        logger.error("Update Failed");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteLeaseById(@PathVariable Long id) {
        Boolean result = profileService.deleteProfileById(id);
        logger.info("Deleted Successfully");
        return ResponseEntity.ok(result);
    }



}
