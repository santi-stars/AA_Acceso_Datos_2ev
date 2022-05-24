package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.Mecanico;
import com.svalero.gestitaller2.exception.MecanicoNotFoundException;

import java.util.List;

public interface MecanicoService {

    List<Mecanico> findAllMecanicos();

    Mecanico findMecanico(long id) throws MecanicoNotFoundException;

    List<Mecanico> findByNombre(String nombre) throws MecanicoNotFoundException;

    Mecanico deleteMecanico(long id) throws MecanicoNotFoundException;

    Mecanico addMecanico(Mecanico mecanico);

    Mecanico modifyMecanico(long id, Mecanico mecanico) throws MecanicoNotFoundException;

    Mecanico modifyFacturaPagada(long id, boolean disponible) throws MecanicoNotFoundException;
}
