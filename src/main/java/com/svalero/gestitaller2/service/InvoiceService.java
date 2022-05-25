package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.Client;
import com.svalero.gestitaller2.domain.Invoice;
import com.svalero.gestitaller2.domain.dto.InvoiceDTO;
import com.svalero.gestitaller2.exception.*;

import java.util.List;

public interface InvoiceService {

    List<Invoice> findAll();

    Invoice findById(long id) throws InvoiceNotFoundException;

    List<Invoice> findByPaid(boolean paid) throws InvoiceNotFoundException;

    Invoice deleteInvoice(long id) throws InvoiceNotFoundException;

    Invoice addInvoice(InvoiceDTO newInvoiceDTO) throws
            SpareNotFoundException, ClientNotFoundException, BikeNotFoundException, WorkOrderNotFoundException;

    Invoice modifyInvoice(long id, InvoiceDTO invoiceDTO) throws InvoiceNotFoundException,
            SpareNotFoundException, ClientNotFoundException, BikeNotFoundException, WorkOrderNotFoundException;

    Invoice modifyPaidInvoice(long id, boolean paid) throws InvoiceNotFoundException;

    List<Invoice> findByClient(Client client) throws InvoiceNotFoundException;

    List<Invoice> findByClientAndBike(InvoiceDTO invoiceDTO) throws ClientNotFoundException, BikeNotFoundException;

    void deleteByBike(InvoiceDTO invoiceDTO) throws BikeNotFoundException;
}
