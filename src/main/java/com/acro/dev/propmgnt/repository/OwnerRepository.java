package com.acro.dev.propmgnt.repository;

import com.acro.dev.propmgnt.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Long> {
  //public List<Owner> findByPropertyId(Long propertyId);



}
