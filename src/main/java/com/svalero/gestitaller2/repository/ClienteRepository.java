package com.svalero.gestitaller2.repository;

import com.svalero.gestitaller2.domain.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    List<Cliente> findAll();

    // JPQL
    @Query("SELECT c FROM cliente c WHERE nombre = :nombre")
    List<Cliente> findByNombre(@Param("nombre") String nombre);

    List<Cliente> findByNombreOrApellidoOrDni(String nombre, String apellido, String dni);
}
