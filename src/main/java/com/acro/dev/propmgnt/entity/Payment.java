package com.acro.dev.propmgnt.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
public class Payment extends BaseEntity {
    private PaymentType type;
    private Date paymentDate;
    private double paymentAmount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "tenant_id", referencedColumnName = "id")
    private Tenant tenant;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "lease_id", referencedColumnName = "id")
    private LeaseDetails lease;
    private double LateFee;

}
