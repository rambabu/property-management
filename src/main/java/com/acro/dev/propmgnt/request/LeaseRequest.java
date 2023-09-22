package com.acro.dev.propmgnt.request;

import com.acro.dev.propmgnt.entity.Profile;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class LeaseRequest {
    private Long leaseId;
    private Long propertyId;
    private List<Profile> profiles;  //list of tenant id's
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date leaseDate;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date leaseStartDate;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date leaseEndDate;
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
