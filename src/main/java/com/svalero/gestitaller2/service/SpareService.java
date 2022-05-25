package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.Spare;
import com.svalero.gestitaller2.exception.SpareNotFoundException;

import java.util.List;

public interface SpareService {

    List<Spare> findAll();

    Spare findById(long id) throws SpareNotFoundException;

    List<Spare> findByStockAmount(int amountStock) throws SpareNotFoundException;

    Spare deleteSpare(long id) throws SpareNotFoundException;

    Spare addSpare(Spare spare);

    Spare modifySpare(long id, Spare spare) throws SpareNotFoundException;

    Spare modifySpareDescription(long id, String description) throws SpareNotFoundException;
}
