package com.acro.dev.propmgnt.repository;

import com.acro.dev.propmgnt.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property,Long> {

}
