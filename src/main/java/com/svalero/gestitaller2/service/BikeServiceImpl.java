package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.Bike;
import com.svalero.gestitaller2.exception.BikeNotFoundException;
import com.svalero.gestitaller2.exception.ClientNotFoundException;
import com.svalero.gestitaller2.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeServiceImpl implements BikeService {

    @Autowired
    private BikeRepository bikeRepository;

    @Override
    public List<Bike> findAll() {
        return bikeRepository.findAll();
    }

    @Override
    public Bike findById(long id) throws BikeNotFoundException {
        return bikeRepository.findById(id).orElseThrow(BikeNotFoundException::new);
    }

    @Override
    public List<Bike> findByBrand(String brand) {
        return bikeRepository.findByBrand(brand);
    }

    @Override
    public List<Bike> findBikesByClient(long id) {
        return bikeRepository.findBikesByClient_Id(id);
    }

    @Override
    public Bike deleteBike(long id) throws BikeNotFoundException {
        Bike bike = bikeRepository.findById(id).orElseThrow(BikeNotFoundException::new);
        bikeRepository.delete(bike);
        return bike;
    }

    @Override
    public Bike addBike(Bike bike) {
        return bikeRepository.save(bike);
    }

    @Override
    public Bike modifyBike(long id, Bike newBike) throws BikeNotFoundException {
        bikeRepository.findById(id).orElseThrow(BikeNotFoundException::new);
        newBike.setId(id);
        bikeRepository.save(newBike);
        return newBike;
    }

    @Override
    public Bike modifyBrand(long id, String brand) throws BikeNotFoundException {
        Bike bike = bikeRepository.findById(id).orElseThrow(BikeNotFoundException::new);
        bike.setBrand(brand);
        return bikeRepository.save(bike);
    }
}
