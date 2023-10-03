package com.acro.dev.propmgnt.controller;

import com.acro.dev.propmgnt.exception.PropertyManagementException;
import com.acro.dev.propmgnt.request.WorkOrderRequest;
import com.acro.dev.propmgnt.response.WorkOrderResponse;
import com.acro.dev.propmgnt.service.WorkOrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/workorder")
public class WorkOrderController {
    public static final Logger LOGGER = LoggerFactory.getLogger(WorkOrderController.class);
    private final WorkOrderService workOrderService;

    public WorkOrderController(@Autowired WorkOrderService workOrderService) {
        this.workOrderService = workOrderService;
    }

    @PostMapping("/")
    public ResponseEntity<WorkOrderResponse> createWorkOrder
            (@RequestBody @Valid @NotNull WorkOrderRequest workOrderRequest) throws PropertyManagementException {
        try {
            WorkOrderResponse workOrderResponse = workOrderService.createWorkOrder(workOrderRequest);
            if (workOrderResponse != null) {
                return ResponseEntity.ok(workOrderResponse);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to create WorkOrder {}", workOrderRequest.getTypeOfWorkOrder());
            throw new PropertyManagementException("Not created workOrder {}" + workOrderRequest.getTypeOfWorkOrder());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkOrderResponse> updateWorkOrder(@PathVariable Long id,
                                                             @RequestBody @Valid @NotNull WorkOrderRequest workOrderRequest) {
        try {
            WorkOrderResponse workOrderResponse = workOrderService.updateWorkOrder(id, workOrderRequest);
            if (workOrderResponse != null) {
                return ResponseEntity.ok(workOrderResponse);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to update WorkOrder {}", workOrderRequest.getProblemDescription());
            throw new PropertyManagementException("Not updated WorkOrder {} " + workOrderRequest.getProblemDescription());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkOrderResponse> findWorkOrderById(@PathVariable Long id) {
        try {
            WorkOrderResponse workOrderResponse = workOrderService.findWorkOrderById(id);
            if (workOrderResponse != null) {
                return ResponseEntity.ok(workOrderResponse);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to find workOrderId {}", id);
            throw new PropertyManagementException("Failed to find workOrderId");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteWorkOrderById(@PathVariable Long id) {
        try {
            WorkOrderResponse workOrderResponse = workOrderService.findWorkOrderById(id);
            if (workOrderResponse != null) {
                boolean res = this.workOrderService.deleteWorkOrderById(id);
                LOGGER.info("Deleted successfully woekOrderId");
                return ResponseEntity.ok(res);
            }
        } catch (Exception e) {
            LOGGER.error("Not deleted workOrderId");
            throw new PropertyManagementException("Failed to delete workOrderId {}" + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }
}
