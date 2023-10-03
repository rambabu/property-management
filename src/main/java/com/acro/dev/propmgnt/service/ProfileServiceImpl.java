package com.acro.dev.propmgnt.service;

import com.acro.dev.propmgnt.CommonResponseMapper;
import com.acro.dev.propmgnt.entity.Profile;
import com.acro.dev.propmgnt.exception.PropertyManagementException;
import com.acro.dev.propmgnt.repository.ProfileRepository;
import com.acro.dev.propmgnt.request.ProfileRequest;
import com.acro.dev.propmgnt.response.ProfileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {
    private static final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);
    private final ProfileRepository profileRepository;
    private final CommonResponseMapper commonResponseMapper;

    public ProfileServiceImpl(@Autowired ProfileRepository profileRepository, CommonResponseMapper commonResponseMapper) {
        this.profileRepository = profileRepository;
        this.commonResponseMapper = commonResponseMapper;
    }
    @Transactional
    public ProfileResponse createProfile(ProfileRequest profileRequest) {
        logger.info("Received profile request {}", profileRequest);
        Profile profile = new Profile();
        profile.setFirstName(profileRequest.getFirstName());
        profile.setLastName(profileRequest.getLastName());
        profile.setEmail(profileRequest.getEmail());
        profile.setPhoneNumber(profileRequest.getPhoneNumber());
        profile.setSsn(profileRequest.getSsn());
        Profile savedProfile = profileRepository.save(profile);
        logger.info("saved successfully");
        return commonResponseMapper.convertToProfileResponse(savedProfile);
    }

    @Transactional
    public ProfileResponse updateProfile(Long id,ProfileRequest profileRequest){
        Optional<Profile> profile= profileRepository.findById(id);
        if(profile.isPresent()) {
            Profile profileOne= profile.get();
            BeanUtils.copyProperties(profileRequest, profileOne);
            Profile savedProfile = profileRepository.save(profileOne);
                    logger.info("profile updated Successfully {}", savedProfile);
                    return commonResponseMapper. convertToProfileResponse(savedProfile);
        }
        throw new PropertyManagementException("profile not found");
    }
    public Boolean deleteProfileById(Long id){
        try{
            Optional<Profile> profile = profileRepository.findById(id);
            if(profile.isPresent()){
                Profile profile1=profile.get();
                profileRepository.deleteById(id);
                logger.debug(" lease deleted successfully{}", profile1);
                return true;
            }
        }
        catch(Exception e){
            logger.error("Unable delete Profile", e);
        }
        throw new PropertyManagementException("Unable to Delete");
    }
}
