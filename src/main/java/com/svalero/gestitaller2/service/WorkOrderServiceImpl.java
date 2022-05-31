package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.Bike;
import com.svalero.gestitaller2.domain.Client;
import com.svalero.gestitaller2.domain.WorkOrder;
import com.svalero.gestitaller2.domain.dto.WorkOrderDTO;
import com.svalero.gestitaller2.exception.BikeNotFoundException;
import com.svalero.gestitaller2.exception.ClientNotFoundException;
import com.svalero.gestitaller2.exception.WorkOrderNotFoundException;
import com.svalero.gestitaller2.repository.BikeRepository;
import com.svalero.gestitaller2.repository.ClientRepository;
import com.svalero.gestitaller2.repository.WokrOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkOrderServiceImpl implements WorkOrderService {

    @Autowired
    private WokrOrderRepository wokrOrderRepository;
    @Autowired
    private BikeRepository bikeRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<WorkOrder> findAll() {
        return wokrOrderRepository.findAll();
    }

    @Override
    public WorkOrder findById(long id) throws WorkOrderNotFoundException {
        return wokrOrderRepository.findById(id).orElseThrow(WorkOrderNotFoundException::new);
    }

    @Override
    public WorkOrder deleteOrder(long id) throws WorkOrderNotFoundException {
        WorkOrder mechanic = wokrOrderRepository.findById(id).orElseThrow(WorkOrderNotFoundException::new);
        wokrOrderRepository.delete(mechanic);
        return mechanic;
    }

    @Override
    public WorkOrder addOrder(WorkOrderDTO newWorkOrderDTO) throws BikeNotFoundException, ClientNotFoundException {

        ModelMapper mapper = new ModelMapper();
        WorkOrder workOrder = mapper.map(newWorkOrderDTO, WorkOrder.class);

        workOrder.setBike(bikeRepository.findById(newWorkOrderDTO.getBike())
                .orElseThrow(BikeNotFoundException::new));

        workOrder.setClient(clientRepository.findById(newWorkOrderDTO.getClient())
                .orElseThrow(ClientNotFoundException::new));

        return wokrOrderRepository.save(workOrder);
    }

    @Override   // TODO hacer correctamente con los atributos nuevos de workorder
    public WorkOrder modifyOrder(long id, WorkOrderDTO newWorkOrderDTO) throws BikeNotFoundException {

        Bike bike = bikeRepository.findById(newWorkOrderDTO.getBike())
                .orElseThrow(BikeNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        WorkOrder workOrder = mapper.map(newWorkOrderDTO, WorkOrder.class);
        workOrder.setId(id);
        workOrder.setBike(bike);

        return wokrOrderRepository.save(workOrder);
    }

    @Override
    public WorkOrder modifyOrderDescription(long id, String description) throws WorkOrderNotFoundException {
        WorkOrder workOrder = wokrOrderRepository.findById(id).orElseThrow(WorkOrderNotFoundException::new);
        workOrder.setDescription(description);
        return wokrOrderRepository.save(workOrder);
    }
}
