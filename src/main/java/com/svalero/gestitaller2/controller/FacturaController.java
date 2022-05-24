package com.svalero.gestitaller2.controller;

import com.svalero.gestitaller2.domain.Factura;
import com.svalero.gestitaller2.domain.dto.FacturaDTO;
import com.svalero.gestitaller2.exception.*;
import com.svalero.gestitaller2.service.FacturaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    private final Logger logger = LoggerFactory.getLogger(FacturaController.class);

    @GetMapping("/facturas")
    public List<Factura> getFacturas() {
        logger.info("Inicio getFacturas");
        List<Factura> facturas = facturaService.findAll();
        logger.info("Fin getFacturas");
        return facturas;
    }

    @GetMapping("/factura/{id}")
    public Factura getById(@PathVariable long id) throws FacturaNotFoundException {
        logger.info("Inicio getById " + id);
        Factura factura = facturaService.findById(id);
        logger.info("Fin getById " + id);
        return factura;
    }

    @GetMapping("/facturas/{pagada}")
    public List<Factura> getByPagada(@PathVariable boolean pagada) throws FacturaNotFoundException {
        logger.info("Inicio getByPagada " + pagada);
        List<Factura> facturas = facturaService.findByPagada(pagada);
        logger.info("Fin getByPagada " + pagada);
        return facturas;
    }

    @DeleteMapping("/factura/{id}")
    public Factura deleteFactura(@PathVariable long id) throws FacturaNotFoundException {
        logger.info("Inicio deleteFactura " + id);
        Factura factura = facturaService.deleteFactura(id);
        logger.info("Fin deleteFactura " + id);
        return factura;
    }

    // DTO
    @PostMapping("/factura")
    public Factura addFactura(@RequestBody FacturaDTO facturaDTO) throws
            RecambioNotFoundException, ClienteNotFoundException, MotoNotFoundException, OrdenNotFoundException {
        logger.info("Inicio addFactura");
        Factura newfactura = facturaService.addFactura(facturaDTO);
        logger.info("Fin addFactura");
        return newfactura;
    }

    // DTO
    @PutMapping("/factura/{id}")
    public Factura modifyFactura(@RequestBody FacturaDTO facturaDTO, @PathVariable long id) throws
            FacturaNotFoundException, RecambioNotFoundException, ClienteNotFoundException, MotoNotFoundException, OrdenNotFoundException {
        logger.info("Inicio modifyFactura " + id);
        Factura newfactura = facturaService.modifyFactura(id, facturaDTO);
        logger.info("Fin modifyFactura " + id);
        return newfactura;
    }

    @PatchMapping("/factura/{id}")
    public Factura modifyFacturaPagada(@PathVariable long id, @RequestBody boolean pagada) throws FacturaNotFoundException {
        logger.info("Inicio modifyFacturaPagada " + id + " a " + pagada);
        Factura factura = facturaService.modifyFacturaPagada(id, pagada);
        logger.info("Fin modifyFacturaPagada " + id + " a " + pagada);
        return factura;
    }

    @ExceptionHandler(FacturaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleFacturaNotFoundException(FacturaNotFoundException fnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", fnfe.getMessage());
        logger.info(fnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FacturaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse("999", "Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
