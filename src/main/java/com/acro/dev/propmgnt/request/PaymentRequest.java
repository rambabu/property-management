package com.acro.dev.propmgnt.request;

import com.acro.dev.propmgnt.entity.PaymentType;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PaymentRequest {
    private Long tenant_id;
    private Long leaseId;
    @NotNull
    private PaymentType type;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date paymentDate;
    @NotNull
    private double paymentAmount;
    private double LateFee;

}
