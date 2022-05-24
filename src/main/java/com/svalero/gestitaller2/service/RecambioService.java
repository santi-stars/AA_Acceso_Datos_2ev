package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.Recambio;
import com.svalero.gestitaller2.exception.RecambioNotFoundException;

import java.util.List;

public interface RecambioService {

    List<Recambio> findAll();

    Recambio findById(long id) throws RecambioNotFoundException;

    List<Recambio> findByCantidadStock(int cantidadStock) throws RecambioNotFoundException;

    Recambio deleteRecambio(long id) throws RecambioNotFoundException;

    Recambio addRecambio(Recambio recambio);

    Recambio modifyRecambio(long id, Recambio recambio) throws RecambioNotFoundException;

    Recambio modifyDescripcionRecambio(long id, String descripcion) throws RecambioNotFoundException;
}
