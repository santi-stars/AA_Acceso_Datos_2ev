package com.svalero.gestitaller2.controller;

import com.svalero.gestitaller2.domain.WorkOrder;
import com.svalero.gestitaller2.domain.dto.WorkOrderDTO;
import com.svalero.gestitaller2.exception.*;
import com.svalero.gestitaller2.service.WorkOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkOrderController {

    @Autowired
    private WorkOrderService workOrderService;

    private final Logger logger = LoggerFactory.getLogger(WorkOrderController.class);

    @GetMapping("/orders")
    public List<WorkOrder> getOrders() {
        logger.info("Inicio getOrders");
        List<WorkOrder> orders = workOrderService.findAll();
        logger.info("Fin getOrders");
        return orders;
    }

    @GetMapping("/order/{id}")
    public WorkOrder getById(@PathVariable long id) throws WorkOrderNotFoundException {
        logger.info("Inicio getById " + id);
        WorkOrder order = workOrderService.findById(id);
        logger.info("Fin getById " + id);
        return order;
    }

    @GetMapping("/orders/{executed}")
    public List<WorkOrder> getByExecuted(@PathVariable boolean executed) throws WorkOrderNotFoundException {
        logger.info("Inicio getByExecuted " + executed);
        List<WorkOrder> orders = workOrderService.findByExecuted(executed);
        logger.info("Fin getByExecuted " + executed);
        return orders;
    }

    @DeleteMapping("/order/{id}")
    public WorkOrder deleteOrder(@PathVariable long id) throws WorkOrderNotFoundException {
        logger.info("Inicio deleteOrder " + id);
        WorkOrder order = workOrderService.deleteOrder(id);
        logger.info("Fin deleteOrder " + id);
        return order;
    }

    // DTO
    @PostMapping("/order")
    public WorkOrder addOrder(@RequestBody WorkOrderDTO newWorkOrderDTO) throws
            MechanicNotFoundException, BikeNotFoundException, InvoiceNotFoundException {
        logger.info("Inicio addOrder");
        WorkOrder newOrder = workOrderService.addOrder(newWorkOrderDTO);
        logger.info("Fin addOrder");
        return newOrder;
    }

    // DTO
    @PutMapping("/order/{id}")
    public WorkOrder modifyOrder(@RequestBody WorkOrderDTO workOrderDTO, @PathVariable long id) throws WorkOrderNotFoundException,
            MechanicNotFoundException, BikeNotFoundException, InvoiceNotFoundException {
        logger.info("Inicio modifyOrder " + id);
        WorkOrder newOrder = workOrderService.modifyOrder(id, workOrderDTO);
        logger.info("Fin modifyOrder " + id);
        return newOrder;
    }

    @PatchMapping("/order/{id}")
    public WorkOrder modifyOrderDescription(@PathVariable long id, @RequestBody String description) throws WorkOrderNotFoundException {
        logger.info("Inicio modifyOrderDescription " + id + " a " + description);
        WorkOrder order = workOrderService.modifyOrderDescription(id, description);
        logger.info("Fin modifyOrderDescription " + id + " a " + description);
        return order;
    }

    @ExceptionHandler(WorkOrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFoundException(WorkOrderNotFoundException onfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", onfe.getMessage());
        logger.info(onfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WorkOrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse("999", "Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
