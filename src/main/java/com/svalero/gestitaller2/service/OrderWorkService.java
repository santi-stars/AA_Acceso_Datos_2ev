package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.WorkOrder;
import com.svalero.gestitaller2.domain.dto.WorkOrderDTO;
import com.svalero.gestitaller2.exception.InvoiceNotFoundException;
import com.svalero.gestitaller2.exception.MechanicNotFoundException;
import com.svalero.gestitaller2.exception.BikeNotFoundException;
import com.svalero.gestitaller2.exception.WorkOrderNotFoundException;

import java.util.List;

public interface OrderWorkService {

    List<WorkOrder> findAll();

    WorkOrder findById(long id) throws WorkOrderNotFoundException;

    List<WorkOrder> findByExecuted(boolean executed) throws WorkOrderNotFoundException;

    WorkOrder deleteOrder(long id) throws WorkOrderNotFoundException;

    WorkOrder addOrder(WorkOrderDTO newWorkOrderDTO) throws
            MechanicNotFoundException, BikeNotFoundException, InvoiceNotFoundException;

    WorkOrder modifyOrder(long id, WorkOrderDTO workOrderDTO) throws WorkOrderNotFoundException,
            MechanicNotFoundException, BikeNotFoundException, InvoiceNotFoundException;

    WorkOrder modifyExecutedOrder(long id, boolean executed) throws WorkOrderNotFoundException;
}
