package com.acro.dev.propmgnt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name= "work_order")
public class WorkOrder {

    private Long orderId;
    private String urgency;
    private String description;
    private String allowed_enter;
    private Long tenantId;
    private Long propertyId;

}
