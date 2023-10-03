package com.acro.dev.propmgnt.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaseRequest {
    private Long leaseId;
    private Long propertyId;
    private List <Long> profiles;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate leaseDate;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate leaseStartDate;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate leaseEndDate;
    @NotNull
    @Min(message = "Least term in Months",value=6)
    private int leaseTerm;
    @NotNull
    private double monthlyRent;
    @NotNull
    private double securityDepositAmount;
    private int pets;
    private boolean isInsured;
    @NotNull
    private double petDeposit;
    @NotNull
    private boolean leaseStatus;
    private int noOfPeople;

}
