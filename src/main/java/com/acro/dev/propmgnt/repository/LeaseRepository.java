package com.acro.dev.propmgnt.repository;

import com.acro.dev.propmgnt.entity.LeaseDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaseRepository extends JpaRepository<LeaseDetails,Long> {
    public LeaseDetails findByPropertyId(Long propertyId);

}
