package com.svalero.gestitaller2.controller;

import com.svalero.gestitaller2.domain.OrdenTrabajo;
import com.svalero.gestitaller2.domain.dto.OrdenTrabajoDTO;
import com.svalero.gestitaller2.exception.*;
import com.svalero.gestitaller2.service.OrdenTrabajoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrdenTrabajoController {

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    private final Logger logger = LoggerFactory.getLogger(OrdenTrabajoController.class);

    @GetMapping("/ordenes")
    public List<OrdenTrabajo> getOrdenes() {
        logger.info("Inicio getOrdenes");
        List<OrdenTrabajo> ordenes = ordenTrabajoService.findAll();
        logger.info("Fin getOrdenes");
        return ordenes;
    }

    @GetMapping("/orden/{id}")
    public OrdenTrabajo getById(@PathVariable long id) throws OrdenNotFoundException {
        logger.info("Inicio getById " + id);
        OrdenTrabajo orden = ordenTrabajoService.findById(id);
        logger.info("Fin getById " + id);
        return orden;
    }

    @GetMapping("/ordenes/{ejecutada}")
    public List<OrdenTrabajo> getByEjecutada(@PathVariable boolean ejecutada) throws OrdenNotFoundException {
        logger.info("Inicio getByEjecutada " + ejecutada);
        List<OrdenTrabajo> ordenes = ordenTrabajoService.findByEjecutada(ejecutada);
        logger.info("Fin getByEjecutada " + ejecutada);
        return ordenes;
    }

    @DeleteMapping("/orden/{id}")
    public OrdenTrabajo deleteOrden(@PathVariable long id) throws OrdenNotFoundException {
        logger.info("Inicio deleteOrden " + id);
        OrdenTrabajo orden = ordenTrabajoService.deleteOrden(id);
        logger.info("Fin deleteOrden " + id);
        return orden;
    }

    // DTO
    @PostMapping("/orden")
    public OrdenTrabajo addOrden(@RequestBody OrdenTrabajoDTO newOrdenTrabajoDTO) throws
            MecanicoNotFoundException, MotoNotFoundException, FacturaNotFoundException {
        logger.info("Inicio addOrden");
        OrdenTrabajo newOrden = ordenTrabajoService.addOrden(newOrdenTrabajoDTO);
        logger.info("Fin addOrden");
        return newOrden;
    }

    // DTO
    @PutMapping("/orden/{id}")
    public OrdenTrabajo modifyOrden(@RequestBody OrdenTrabajoDTO ordenTrabajoDTO, @PathVariable long id) throws OrdenNotFoundException,
            MecanicoNotFoundException, MotoNotFoundException, FacturaNotFoundException {
        logger.info("Inicio modifyOrden " + id);
        OrdenTrabajo newOrden = ordenTrabajoService.modifyOrden(id, ordenTrabajoDTO);
        logger.info("Fin modifyOrden " + id);
        return newOrden;
    }

    @PatchMapping("/orden/{id}")
    public OrdenTrabajo modifyOrdenEjecutada(@PathVariable long id, @RequestBody boolean ejecutada) throws OrdenNotFoundException {
        logger.info("Inicio modifyOrdenEjecutada " + id + " a " + ejecutada);
        OrdenTrabajo orden = ordenTrabajoService.modifyOrdenEjecutada(id, ejecutada);
        logger.info("Fin modifyOrdenEjecutada " + id + " a " + ejecutada);
        return orden;
    }

    @ExceptionHandler(OrdenNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrdenNotFoundException(OrdenNotFoundException onfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", onfe.getMessage());
        logger.info(onfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrdenNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse("999", "Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
