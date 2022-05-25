package com.svalero.gestitaller2.repository;

import com.svalero.gestitaller2.domain.Bike;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends CrudRepository<Bike, Long> {
    List<Bike> findAll();

    List<Bike> findByBrand(String brand);
}
