package com.acro.dev.propmgnt.repository;

import com.acro.dev.propmgnt.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {

}
