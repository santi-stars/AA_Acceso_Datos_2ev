package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.Mechanic;
import com.svalero.gestitaller2.domain.Bike;
import com.svalero.gestitaller2.domain.WorkOrder;
import com.svalero.gestitaller2.domain.dto.WorkOrderDTO;
import com.svalero.gestitaller2.exception.MechanicNotFoundException;
import com.svalero.gestitaller2.exception.BikeNotFoundException;
import com.svalero.gestitaller2.exception.WorkOrderNotFoundException;
import com.svalero.gestitaller2.repository.MechanicRepository;
import com.svalero.gestitaller2.repository.BikeRepository;
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
    private MechanicRepository mechanicRepository;
    @Autowired
    private BikeRepository bikeRepository;

    @Override
    public List<WorkOrder> findAll() {
        return wokrOrderRepository.findAll();
    }

    @Override
    public WorkOrder findById(long id) throws WorkOrderNotFoundException {
        return wokrOrderRepository.findById(id).orElseThrow(WorkOrderNotFoundException::new);
    }

    @Override
    public List<WorkOrder> findByExecuted(boolean executed) {
        return wokrOrderRepository.findByExecuted(executed);
    }

    @Override
    public WorkOrder deleteOrder(long id) throws WorkOrderNotFoundException {
        WorkOrder mechanic = wokrOrderRepository.findById(id).orElseThrow(WorkOrderNotFoundException::new);
        wokrOrderRepository.delete(mechanic);
        return mechanic;
    }

    @Override
    public WorkOrder addOrder(WorkOrderDTO newWorkOrderDTO) throws
            MechanicNotFoundException, BikeNotFoundException {

        Bike bike = bikeRepository.findById(newWorkOrderDTO.getBike())
                .orElseThrow(BikeNotFoundException::new);

        Mechanic mechanic = mechanicRepository.findById(newWorkOrderDTO.getMechanic())
                .orElseThrow(MechanicNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        WorkOrder workOrder = mapper.map(newWorkOrderDTO, WorkOrder.class);
        workOrder.setMechanic(mechanic);
        workOrder.setBike(bike);

        return wokrOrderRepository.save(workOrder);
    }

    @Override
    public WorkOrder modifyOrder(long id, WorkOrderDTO newWorkOrderDTO) throws
            MechanicNotFoundException, BikeNotFoundException {

        Bike bike = bikeRepository.findById(newWorkOrderDTO.getBike())
                .orElseThrow(BikeNotFoundException::new);

        Mechanic mechanic = mechanicRepository.findById(newWorkOrderDTO.getMechanic())
                .orElseThrow(MechanicNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        WorkOrder workOrder = mapper.map(newWorkOrderDTO, WorkOrder.class);
        workOrder.setId(id);
        workOrder.setMechanic(mechanic);
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
