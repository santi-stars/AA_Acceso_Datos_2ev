package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.Cliente;
import com.svalero.gestitaller2.domain.Factura;
import com.svalero.gestitaller2.domain.dto.FacturaDTO;
import com.svalero.gestitaller2.exception.*;

import java.util.List;

public interface FacturaService {

    List<Factura> findAll();

    Factura findById(long id) throws FacturaNotFoundException;

    List<Factura> findByPagada(boolean pagada) throws FacturaNotFoundException;

    Factura deleteFactura(long id) throws FacturaNotFoundException;

    Factura addFactura(FacturaDTO newFacturaDTO) throws
            RecambioNotFoundException, ClienteNotFoundException, MotoNotFoundException, OrdenNotFoundException;

    Factura modifyFactura(long id, FacturaDTO facturaDTO) throws FacturaNotFoundException,
            RecambioNotFoundException, ClienteNotFoundException, MotoNotFoundException, OrdenNotFoundException;

    Factura modifyFacturaPagada(long id, boolean pagada) throws FacturaNotFoundException;

    List<Factura> findByCliente(Cliente cliente) throws FacturaNotFoundException;

    List<Factura> findByClienteAndMoto(FacturaDTO facturaDTO) throws ClienteNotFoundException, MotoNotFoundException;

    void deleteByMoto(FacturaDTO facturaDTO) throws MotoNotFoundException;
}
