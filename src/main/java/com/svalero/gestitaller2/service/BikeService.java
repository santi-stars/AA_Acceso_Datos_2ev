package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.Bike;
import com.svalero.gestitaller2.domain.Client;
import com.svalero.gestitaller2.domain.dto.BikeDTO;
import com.svalero.gestitaller2.exception.BikeNotFoundException;
import com.svalero.gestitaller2.exception.ClientNotFoundException;
import com.svalero.gestitaller2.exception.WorkOrderNotFoundException;

import java.util.List;

public interface BikeService {

    List<Bike> findAll();

    Bike findById(long id) throws BikeNotFoundException;

    List<Bike> findByBrand(String brand) throws BikeNotFoundException;

    List<Bike> findBikesByClient(long id) throws ClientNotFoundException, BikeNotFoundException;

    Bike deleteBike(long id) throws BikeNotFoundException;

    Bike addBike(BikeDTO bikeDTO) throws ClientNotFoundException;

    Bike modifyBike(long id, BikeDTO bikeDTO) throws BikeNotFoundException, ClientNotFoundException;

    Bike modifyBrand(long id, String brand) throws BikeNotFoundException;

    Bike modifyClient(long id, Client client) throws BikeNotFoundException;
}
