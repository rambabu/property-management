package com.acro.dev.propmgnt.repository;
import com.acro.dev.propmgnt.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    public Boolean deletePropertyById(Long id);

}
