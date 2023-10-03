package com.acro.dev.propmgnt.repository;

import com.acro.dev.propmgnt.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantRepository extends JpaRepository<Tenant,Long> {


    List<Tenant> findByPropertyId(Long propertyId);


}
