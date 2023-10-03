package com.acro.dev.propmgnt.repository;

import com.acro.dev.propmgnt.entity.LeaseDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaseRepository extends JpaRepository<LeaseDetails,Long> {
    public List<LeaseDetails> findByPropertyId(Long propertyId);

    LeaseDetails findByPropertyIdAndLeaseStatus(Long propertyId, boolean leaseStatus);
}
