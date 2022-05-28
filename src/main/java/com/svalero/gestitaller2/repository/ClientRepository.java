package com.svalero.gestitaller2.repository;

import com.svalero.gestitaller2.domain.Bike;
import com.svalero.gestitaller2.domain.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    List<Client> findAll();

    // JPQL
    @Query("SELECT c FROM client c WHERE name = :name")
    List<Client> findByName(@Param("name") String name);

    List<Client> findByNameOrSurnameOrDni(String name, String surname, String dni);

}
