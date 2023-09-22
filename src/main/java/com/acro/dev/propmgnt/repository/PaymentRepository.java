package com.acro.dev.propmgnt.repository;

import com.acro.dev.propmgnt.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
