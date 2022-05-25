package com.svalero.gestitaller2.repository;

import com.svalero.gestitaller2.domain.Mechanic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MechanicRepository extends CrudRepository<Mechanic, Long> {
    List<Mechanic> findAll();

    List<Mechanic> findByName(String name);
}
