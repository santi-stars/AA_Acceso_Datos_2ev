package com.svalero.gestitaller2.controller;

import com.svalero.gestitaller2.domain.Invoice;
import com.svalero.gestitaller2.domain.dto.InvoiceDTO;
import com.svalero.gestitaller2.exception.*;
import com.svalero.gestitaller2.service.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    private final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    @GetMapping("/invoices")
    public List<Invoice> getInvoices() {
        logger.info("Inicio getInvoices");
        List<Invoice> invoices = invoiceService.findAll();
        logger.info("Fin getInvoices");
        return invoices;
    }

    @GetMapping("/invoice/{id}")
    public Invoice getById(@PathVariable long id) throws InvoiceNotFoundException {
        logger.info("Inicio getById " + id);
        Invoice invoice = invoiceService.findById(id);
        logger.info("Fin getById " + id);
        return invoice;
    }

    @GetMapping("/invoices/{paid}")
    public List<Invoice> getByPaid(@PathVariable boolean paid) throws InvoiceNotFoundException {
        logger.info("Inicio getByPaid " + paid);
        List<Invoice> invoices = invoiceService.findByPaid(paid);
        logger.info("Fin getByPaid " + paid);
        return invoices;
    }

    @DeleteMapping("/invoice/{id}")
    public Invoice deleteInvoice(@PathVariable long id) throws InvoiceNotFoundException {
        logger.info("Inicio deleteInvoice " + id);
        Invoice invoice = invoiceService.deleteInvoice(id);
        logger.info("Fin deleteInvoice " + id);
        return invoice;
    }

    // DTO
    @PostMapping("/invoice")
    public Invoice addInvoice(@RequestBody InvoiceDTO invoiceDTO) throws
            SpareNotFoundException, ClientNotFoundException, BikeNotFoundException, WorkOrderNotFoundException {
        logger.info("Inicio addInvoice");
        Invoice newinvoice = invoiceService.addInvoice(invoiceDTO);
        logger.info("Fin addInvoice");
        return newinvoice;
    }

    // DTO
    @PutMapping("/invoice/{id}")
    public Invoice modifyInvoice(@RequestBody InvoiceDTO invoiceDTO, @PathVariable long id) throws
            InvoiceNotFoundException, SpareNotFoundException, ClientNotFoundException, BikeNotFoundException, WorkOrderNotFoundException {
        logger.info("Inicio modifyInvoice " + id);
        Invoice newinvoice = invoiceService.modifyInvoice(id, invoiceDTO);
        logger.info("Fin modifyInvoice " + id);
        return newinvoice;
    }

    @PatchMapping("/invoice/{id}")
    public Invoice modifyPaidInvoice(@PathVariable long id, @RequestBody boolean paid) throws InvoiceNotFoundException {
        logger.info("Inicio modifyPaidInvoice " + id + " a " + paid);
        Invoice invoice = invoiceService.modifyPaidInvoice(id, paid);
        logger.info("Fin modifyPaidInvoice " + id + " a " + paid);
        return invoice;
    }

    @ExceptionHandler(InvoiceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleInvoiceNotFoundException(InvoiceNotFoundException infe) {
        ErrorResponse errorResponse = new ErrorResponse("404", infe.getMessage());
        logger.info(infe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvoiceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse("999", "Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
