package com.acro.dev.propmgnt.repository;

import com.acro.dev.propmgnt.entity.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkOrderRepository extends JpaRepository<WorkOrder,Long> {
}
