package com.svalero.gestitaller2.repository;

import com.svalero.gestitaller2.domain.Client;
import com.svalero.gestitaller2.domain.Invoice;
import com.svalero.gestitaller2.domain.Bike;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
    List<Invoice> findAll();

    List<Invoice> findByPaid(boolean paid);

    List<Invoice> findByClient(Client client);

    // JPQL
    @Query("SELECT f FROM invoice f WHERE client = :client AND bike = :bike")
    List<Invoice> findByClientAndBike(@Param("client") Client client, @Param("bike") Bike bike);

    // JPQL
    @Transactional
    @Modifying
    @Query("DELETE FROM invoice WHERE bike = :bike")
    void deleteByBike(@Param("bike") Bike bike);
}
