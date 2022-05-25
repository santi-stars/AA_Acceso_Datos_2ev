package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.Bike;
import com.svalero.gestitaller2.exception.BikeNotFoundException;

import java.util.List;

public interface BikeService {

    List<Bike> findAll();

    Bike findById(long id) throws BikeNotFoundException;

    List<Bike> findByBrand(String brand) throws BikeNotFoundException;

    Bike deleteBike(long id) throws BikeNotFoundException;

    Bike addBike(Bike bike);

    Bike modifyBike(long id, Bike bike) throws BikeNotFoundException;

    Bike modifyBrand(long id, String brand) throws BikeNotFoundException;
}
