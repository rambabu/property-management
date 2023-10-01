package com.acro.dev.propmgnt.service;

import com.acro.dev.propmgnt.request.ProfileRequest;
import com.acro.dev.propmgnt.response.ProfileResponse;

public interface ProfileService {
    ProfileResponse createProfile(ProfileRequest profileRequest);

    Boolean deleteProfileById(Long id);

    ProfileResponse updateProfile(Long id, ProfileRequest profileRequest);
}
