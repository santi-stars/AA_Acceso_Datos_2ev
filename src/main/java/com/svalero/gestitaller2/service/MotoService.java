package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.Moto;
import com.svalero.gestitaller2.exception.MotoNotFoundException;

import java.util.List;

public interface MotoService {

    List<Moto> findAll();

    Moto findById(long id) throws MotoNotFoundException;

    List<Moto> findByMarca(String marca) throws MotoNotFoundException;

    Moto deleteMoto(long id) throws MotoNotFoundException;

    Moto addMoto(Moto moto);

    Moto modifyMoto(long id, Moto moto) throws MotoNotFoundException;

    Moto modifyMarcaMoto(long id, String marca) throws MotoNotFoundException;
}
