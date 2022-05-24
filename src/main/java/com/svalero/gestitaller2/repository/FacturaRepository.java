package com.svalero.gestitaller2.repository;

import com.svalero.gestitaller2.domain.Cliente;
import com.svalero.gestitaller2.domain.Factura;
import com.svalero.gestitaller2.domain.Moto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FacturaRepository extends CrudRepository<Factura, Long> {
    List<Factura> findAll();

    List<Factura> findByPagada(boolean pagada);

    List<Factura> findByCliente(Cliente cliente);

    // JPQL
    @Query("SELECT f FROM factura f WHERE cliente = :cliente AND moto = :moto")
    List<Factura> findByClienteAndMoto(@Param("cliente") Cliente cliente, @Param("moto") Moto moto);

    // JPQL
    @Transactional
    @Modifying
    @Query("DELETE FROM factura WHERE moto = :moto")
    void deleteByMoto(@Param("moto") Moto moto);
}
