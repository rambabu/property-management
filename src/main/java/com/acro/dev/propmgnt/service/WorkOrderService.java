package com.acro.dev.propmgnt.service;

import com.acro.dev.propmgnt.request.WorkOrderRequest;
import com.acro.dev.propmgnt.response.WorkOrderResponse;

public interface WorkOrderService {
    public WorkOrderResponse createWorkOrder(WorkOrderRequest workOrderRequest);
    public WorkOrderResponse updateWorkOrder(Long id,WorkOrderRequest workOrderRequest);
    public WorkOrderResponse findWorkOrderById(Long id);
    public boolean deleteWorkOrderById(Long id);
}
