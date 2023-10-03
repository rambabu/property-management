package com.acro.dev.propmgnt.response;

import lombok.Data;

import java.util.Date;
@Data
public class PaymentResponse {
    private Long tenant_id;
    private Long leaseId;
    private String paymentType;
    private Date paymentDate;
    private double paymentAmount;
    private double LateFee;

}
