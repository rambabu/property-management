package com.acro.dev.propmgnt.service;

import com.acro.dev.propmgnt.request.LeaseRequest;
import com.acro.dev.propmgnt.response.LeaseResponse;

public interface LeaseService {

    LeaseResponse createLease(LeaseRequest leaseRequest);

    LeaseResponse updateLease(Long id, LeaseRequest leaseRequest);

    Boolean deleteLeaseById(Long id);

    LeaseResponse getLeaseByTenantId(Long id);
}
