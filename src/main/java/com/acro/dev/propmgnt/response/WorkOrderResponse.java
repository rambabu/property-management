package com.acro.dev.propmgnt.response;

import lombok.Data;

import java.util.Date;

@Data
public class WorkOrderResponse {
    private Long workOrderId;
    private Long propertyId;
    private Long tenantId;

    private String problemDescription;
    private String typeOfWorkOrder;
    private Date woDate;
    private Date woStartDate;
    private Date woEndDate;
    private String permissionToEnter;


}
