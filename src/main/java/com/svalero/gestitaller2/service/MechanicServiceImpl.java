package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.Mechanic;
import com.svalero.gestitaller2.exception.MechanicNotFoundException;
import com.svalero.gestitaller2.repository.MechanicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MechanicServiceImpl implements MechanicService {

    @Autowired
    private MechanicRepository mechanicRepository;

    @Override
    public List<Mechanic> findAllMechanics() {
        return mechanicRepository.findAll();
    }

    @Override
    public Mechanic findMechanic(long id) throws MechanicNotFoundException {
        return mechanicRepository.findById(id).orElseThrow(MechanicNotFoundException::new);
    }

    @Override
    public List<Mechanic> findByName(String name) {
        return mechanicRepository.findByName(name);
    }

    @Override
    public Mechanic deleteMechanic(long id) throws MechanicNotFoundException {
        Mechanic mechanic = mechanicRepository.findById(id).orElseThrow(MechanicNotFoundException::new);
        mechanicRepository.delete(mechanic);
        return mechanic;
    }

    @Override
    public Mechanic addMechanic(Mechanic mechanic) {
        return mechanicRepository.save(mechanic);
    }

    @Override
    public Mechanic modifyMechanic(long id, Mechanic newMechanic) throws MechanicNotFoundException {
        Mechanic mechanic = mechanicRepository.findById(id).orElseThrow(MechanicNotFoundException::new);
        mechanic.setName(newMechanic.getName());
        mechanic.setSurname(newMechanic.getSurname());
        mechanic.setAge(newMechanic.getAge());
        mechanic.setDni(newMechanic.getDni());
        mechanic.setAvailable(newMechanic.isAvailable());
        mechanic.setHiringDate(newMechanic.getHiringDate());

        return mechanicRepository.save(mechanic);
    }

    @Override
    public Mechanic modifyPaidInvoice(long id, boolean available) throws MechanicNotFoundException {
        Mechanic mechanic = mechanicRepository.findById(id).orElseThrow(MechanicNotFoundException::new);
        mechanic.setAvailable(available);
        return mechanicRepository.save(mechanic);
    }
}
