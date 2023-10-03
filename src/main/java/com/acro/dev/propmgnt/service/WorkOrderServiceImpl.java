package com.acro.dev.propmgnt.service;

import com.acro.dev.propmgnt.entity.Property;
import com.acro.dev.propmgnt.entity.Tenant;
import com.acro.dev.propmgnt.entity.WorkOrder;
import com.acro.dev.propmgnt.exception.PropertyManagementException;
import com.acro.dev.propmgnt.repository.PropertyRepository;
import com.acro.dev.propmgnt.repository.TenantRepository;
import com.acro.dev.propmgnt.repository.WorkOrderRepository;
import com.acro.dev.propmgnt.request.WorkOrderRequest;
import com.acro.dev.propmgnt.response.WorkOrderResponse;
import com.acro.dev.propmgnt.responsemethod.CommonResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class WorkOrderServiceImpl implements WorkOrderService{
    private static final Logger LOGGER= LoggerFactory.getLogger(WorkOrderServiceImpl.class);
    private final WorkOrderRepository workOrderRepository;
    private final PropertyRepository propertyRepository;
    private final TenantRepository tenantRepository;
    private final CommonResponseMapper commonResponseMapper;
    public WorkOrderServiceImpl(@Autowired WorkOrderRepository workOrderRepository,
                                @Autowired PropertyRepository propertyRepository,
                                @Autowired TenantRepository tenantRepository,
                                @Autowired CommonResponseMapper commonResponseMapper){
        this.workOrderRepository=workOrderRepository;
        this.propertyRepository=propertyRepository;
        this.tenantRepository=tenantRepository;
        this.commonResponseMapper=commonResponseMapper;
    }
    @Override
   // @Transactional
    public WorkOrderResponse createWorkOrder(WorkOrderRequest workOrderRequest) {
        LOGGER.info("Received request to WorkOrder");
        WorkOrder workOrder=new WorkOrder();
        Optional<Property> property=propertyRepository.findById(workOrderRequest.getPropertyId());
        Optional<Tenant> tenant=tenantRepository.findById(workOrderRequest.getTenantId());
        if(property.isPresent()&& tenant.isPresent()){
           Property propertyOne=property.get();
           Tenant tenantOne=tenant.get();
            workOrder.setProblemDescription((workOrderRequest.getProblemDescription()));
            workOrder.setTypeOfWorkOrder(workOrderRequest.getTypeOfWorkOrder());
            workOrder.setWoDate(workOrderRequest.getWoDate());
            workOrder.setWoStartDate(workOrderRequest.getWoStartDate());
            workOrder.setWoEndDate(workOrderRequest.getWoEndDate());
            workOrder.setPermissionToEnter(workOrderRequest.getPermissionToEnter());
            WorkOrder workOrderOne=workOrderRepository.save(workOrder);
            LOGGER.info("Created WorkOrder ");
            return commonResponseMapper.getWorkOrderResponse(workOrderOne);
        }else{
            throw new PropertyManagementException("WorkOrder not Created");
        }
    }

    @Override
   // @Transactional
    public WorkOrderResponse updateWorkOrder(Long id,WorkOrderRequest workOrderRequest) {
        LOGGER.info("updating WorkOrder");
        Optional<WorkOrder> workOrder = workOrderRepository.findById(id);
        if (workOrder.isPresent()) {
            WorkOrder existingWorkOrder = workOrder.get();
            existingWorkOrder.setProblemDescription((workOrderRequest.getProblemDescription()));
            existingWorkOrder.setTypeOfWorkOrder(workOrderRequest.getTypeOfWorkOrder());
            existingWorkOrder.setPermissionToEnter(workOrderRequest.getPermissionToEnter());
            existingWorkOrder.setWoDate(workOrderRequest.getWoDate());
            existingWorkOrder.setWoStartDate(workOrderRequest.getWoStartDate());
            existingWorkOrder.setWoEndDate(workOrderRequest.getWoEndDate());
            WorkOrder updatedWorkOrder = workOrderRepository.save(existingWorkOrder);
            LOGGER.info("Updated WorkOrder Successfully");
            return commonResponseMapper.getWorkOrderResponse(updatedWorkOrder);
        } else {
            throw new PropertyManagementException("Not updated WorkOrder");
        }
    }

    @Override
    public WorkOrderResponse findWorkOrderById(Long id) {
            Optional<WorkOrder> WorkOrderId=workOrderRepository.findById(id);
            if(WorkOrderId.isPresent()){
                WorkOrder getWorkOrder=WorkOrderId.get();
                return commonResponseMapper.getWorkOrderResponse(getWorkOrder);
        }
        throw new PropertyManagementException("Not found  workOrderId");
    }

    @Override
    public boolean deleteWorkOrderById(Long id) {
        Optional<WorkOrder> workOrder=workOrderRepository.findById(id);
        if(workOrder.isEmpty()){
            LOGGER.info("Failed to delete WorkOrder Id");
            throw new PropertyManagementException("WorkOrder not found");
        }else{
            try{
                WorkOrder callWorkOrder=workOrder.get();
                workOrderRepository.deleteById(id);
                return true;
            }catch(Exception e){
                LOGGER.error("Failed to delete WorkOrder by PropertyId {}",id);
                throw new PropertyManagementException("failed to delete WorkOrder");
            }
    }
}
}
