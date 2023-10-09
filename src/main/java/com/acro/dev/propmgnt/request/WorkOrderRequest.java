package com.acro.dev.propmgnt.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkOrderRequest {
    private Long workOrderId;
   // private List<Property> property; //Is List possible here
    private Long propertyId;
   // private Long tenantId;
    @NotNull(message="please provide valid description")
    private String problemDescription;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date woDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date woStartDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date woEndDate;

    private String permissionToEnter;

    @NotNull
    private String typeOfWorkOrder;

}
