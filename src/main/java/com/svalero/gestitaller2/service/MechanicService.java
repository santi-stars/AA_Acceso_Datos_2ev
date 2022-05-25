package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.Mechanic;
import com.svalero.gestitaller2.exception.MechanicNotFoundException;

import java.util.List;

public interface MechanicService {

    List<Mechanic> findAllMechanics();

    Mechanic findMechanic(long id) throws MechanicNotFoundException;

    List<Mechanic> findByName(String name) throws MechanicNotFoundException;

    Mechanic deleteMechanic(long id) throws MechanicNotFoundException;

    Mechanic addMechanic(Mechanic mechanic);

    Mechanic modifyMechanic(long id, Mechanic mechanic) throws MechanicNotFoundException;

    Mechanic modifyPaidInvoice(long id, boolean available) throws MechanicNotFoundException;
}
