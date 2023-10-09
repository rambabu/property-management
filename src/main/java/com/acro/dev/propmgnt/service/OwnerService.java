package com.acro.dev.propmgnt.service;

import com.acro.dev.propmgnt.request.OwnerRequest;
import com.acro.dev.propmgnt.response.OwnerResponse;

public interface OwnerService {
    public OwnerResponse createOwner(OwnerRequest ownerRequest);

    public OwnerResponse updateOwner(Long id, OwnerRequest ownerRequest);

    public OwnerResponse findOwnerById(Long id);

    public boolean deleteOwnerById(Long id);

    //public void uploadFile(MultipartFile file) throws IOException;
    public OwnerResponse generateFileByOwnerId(Long id) throws Exception;
}