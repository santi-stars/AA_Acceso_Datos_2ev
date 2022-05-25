package com.svalero.gestitaller2.repository;

import com.svalero.gestitaller2.domain.WorkOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WokrOrderRepository extends CrudRepository<WorkOrder, Long> {
    List<WorkOrder> findAll();

    List<WorkOrder> findByExecuted(boolean executed);
}
