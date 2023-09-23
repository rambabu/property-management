package com.acro.dev.propmgnt.repository;

import com.acro.dev.propmgnt.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
