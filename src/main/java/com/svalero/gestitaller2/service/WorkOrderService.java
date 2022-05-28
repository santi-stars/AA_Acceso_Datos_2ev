package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.WorkOrder;
import com.svalero.gestitaller2.domain.dto.WorkOrderDTO;
import com.svalero.gestitaller2.exception.BikeNotFoundException;
import com.svalero.gestitaller2.exception.WorkOrderNotFoundException;

import java.util.List;

public interface WorkOrderService {

    List<WorkOrder> findAll();

    WorkOrder findById(long id) throws WorkOrderNotFoundException;

    WorkOrder deleteOrder(long id) throws WorkOrderNotFoundException;

    WorkOrder addOrder(WorkOrderDTO newWorkOrderDTO) throws
            BikeNotFoundException;

    WorkOrder modifyOrder(long id, WorkOrderDTO workOrderDTO) throws WorkOrderNotFoundException,
            BikeNotFoundException;

    WorkOrder modifyOrderDescription(long id, String description) throws WorkOrderNotFoundException;
}
