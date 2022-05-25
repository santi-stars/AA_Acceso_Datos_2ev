package com.svalero.gestitaller2.repository;

import com.svalero.gestitaller2.domain.Spare;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpareRepository extends CrudRepository<Spare, Long> {
    List<Spare> findAll();

    List<Spare> findByStockAmount(int amountStock);
}
