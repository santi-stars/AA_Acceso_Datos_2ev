package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.OrdenTrabajo;
import com.svalero.gestitaller2.domain.dto.OrdenTrabajoDTO;
import com.svalero.gestitaller2.exception.FacturaNotFoundException;
import com.svalero.gestitaller2.exception.MecanicoNotFoundException;
import com.svalero.gestitaller2.exception.MotoNotFoundException;
import com.svalero.gestitaller2.exception.OrdenNotFoundException;

import java.util.List;

public interface OrdenTrabajoService {

    List<OrdenTrabajo> findAll();

    OrdenTrabajo findById(long id) throws OrdenNotFoundException;

    List<OrdenTrabajo> findByEjecutada(boolean ejecutada) throws OrdenNotFoundException;

    OrdenTrabajo deleteOrden(long id) throws OrdenNotFoundException;

    OrdenTrabajo addOrden(OrdenTrabajoDTO newOrdenTrabajoDTO) throws
            MecanicoNotFoundException, MotoNotFoundException, FacturaNotFoundException;

    OrdenTrabajo modifyOrden(long id, OrdenTrabajoDTO ordenTrabajoDTO) throws OrdenNotFoundException,
            MecanicoNotFoundException, MotoNotFoundException, FacturaNotFoundException;

    OrdenTrabajo modifyOrdenEjecutada(long id, boolean ejecutada) throws OrdenNotFoundException;
}
