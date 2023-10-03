package com.acro.dev.propmgnt.service;

import com.acro.dev.propmgnt.request.TenantRequest;
import com.acro.dev.propmgnt.response.TenantResponse;

import java.util.List;

public interface TenantService {
    TenantResponse createTenant(TenantRequest tenantReq);

    TenantResponse updateTenant(Long id, TenantRequest tenantRequest);

    TenantResponse getById(Long id);

    List<TenantResponse> getAllTenantsByPropertyId(Long pId);

    boolean deleteTenantById(Long id);
}
