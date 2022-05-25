package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.*;
import com.svalero.gestitaller2.domain.dto.InvoiceDTO;
import com.svalero.gestitaller2.exception.*;
import com.svalero.gestitaller2.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private SpareRepository spareRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BikeRepository bikeRepository;
    @Autowired
    private WokrOrderRepository wokrOrderRepository;

    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice findById(long id) throws InvoiceNotFoundException {
        return invoiceRepository.findById(id).orElseThrow(InvoiceNotFoundException::new);
    }

    @Override
    public List<Invoice> findByPaid(boolean paid) {
        return invoiceRepository.findByPaid(paid);
    }

    @Override
    public Invoice deleteInvoice(long id) throws InvoiceNotFoundException {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(InvoiceNotFoundException::new);
        invoiceRepository.delete(invoice);
        return invoice;
    }

    @Override
    public Invoice addInvoice(InvoiceDTO newInvoiceDTO) throws
            SpareNotFoundException, ClientNotFoundException, BikeNotFoundException, WorkOrderNotFoundException {

        Spare spare = spareRepository.findById(newInvoiceDTO.getSpare())
                .orElseThrow(SpareNotFoundException::new);

        Client client = clientRepository.findById(newInvoiceDTO.getClient())
                .orElseThrow(ClientNotFoundException::new);

        Bike bike = bikeRepository.findById(newInvoiceDTO.getBike())
                .orElseThrow(BikeNotFoundException::new);

        WorkOrder workOrder = wokrOrderRepository.findById(newInvoiceDTO.getWorkOrder())
                .orElseThrow(WorkOrderNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        Invoice invoice = mapper.map(newInvoiceDTO, Invoice.class);
        invoice.setSpare(spare);
        invoice.setClient(client);
        invoice.setBike(bike);
        invoice.setWorkOrder(workOrder);

        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice modifyInvoice(long id, InvoiceDTO newInvoiceDTO) throws
            SpareNotFoundException, ClientNotFoundException, BikeNotFoundException, WorkOrderNotFoundException {

        Spare spare = spareRepository.findById(newInvoiceDTO.getSpare())
                .orElseThrow(SpareNotFoundException::new);

        Client client = clientRepository.findById(newInvoiceDTO.getClient())
                .orElseThrow(ClientNotFoundException::new);

        Bike bike = bikeRepository.findById(newInvoiceDTO.getBike())
                .orElseThrow(BikeNotFoundException::new);

        WorkOrder workOrder = wokrOrderRepository.findById(newInvoiceDTO.getWorkOrder())
                .orElseThrow(WorkOrderNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        Invoice invoice = mapper.map(newInvoiceDTO, Invoice.class);
        invoice.setId(id);
        invoice.setSpare(spare);
        invoice.setClient(client);
        invoice.setBike(bike);
        invoice.setWorkOrder(workOrder);

        return invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> findByClient(Client client) throws InvoiceNotFoundException {
        return invoiceRepository.findByClient(client);
    }

    @Override
    public List<Invoice> findByClientAndBike(InvoiceDTO invoiceDTO) throws ClientNotFoundException, BikeNotFoundException {

        Client client = clientRepository.findById(invoiceDTO.getClient())
                .orElseThrow(ClientNotFoundException::new);

        Bike bike = bikeRepository.findById(invoiceDTO.getBike())
                .orElseThrow(BikeNotFoundException::new);

        return invoiceRepository.findByClientAndBike(client, bike);
    }

    @Override
    public void deleteByBike(InvoiceDTO invoiceDTO) throws BikeNotFoundException {

        Bike bike = bikeRepository.findById(invoiceDTO.getBike())
                .orElseThrow(BikeNotFoundException::new);

        invoiceRepository.deleteByBike(bike);
    }

    @Override
    public Invoice modifyPaidInvoice(long id, boolean paid) throws InvoiceNotFoundException {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(InvoiceNotFoundException::new);
        invoice.setPaid(paid);
        return invoiceRepository.save(invoice);
    }
}
