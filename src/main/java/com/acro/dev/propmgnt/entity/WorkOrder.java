package com.acro.dev.propmgnt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name= "work_order")
public class WorkOrder extends BaseEntity{
    private String urgency;
    private String description;
    private String allowed_enter;
    private Long tenantId;
    private Long propertyId;

}
