package com.svalero.gestitaller2.controller;

import com.svalero.gestitaller2.domain.Recambio;
import com.svalero.gestitaller2.exception.ErrorResponse;
import com.svalero.gestitaller2.exception.RecambioNotFoundException;
import com.svalero.gestitaller2.service.RecambioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecambioController {

    @Autowired
    private RecambioService recambioService;

    private final Logger logger = LoggerFactory.getLogger(RecambioController.class);

    @GetMapping("/recambios")
    public List<Recambio> getRecambios() {
        logger.info("Inicio getRecambios");
        List<Recambio> recambios = recambioService.findAll();
        logger.info("Fin getRecambios");
        return recambios;
    }

    @GetMapping("/recambio/{id}")
    public Recambio getById(@PathVariable long id) throws RecambioNotFoundException {
        logger.info("Inicio getById " + id);
        Recambio recambio = recambioService.findById(id);
        logger.info("Fin getById " + id);
        return recambio;
    }

    @GetMapping("/recambios/{cantidad}")
    public List<Recambio> getByCantidadStock(@PathVariable int cantidad) throws RecambioNotFoundException {
        logger.info("Inicio getByCantidadStock " + cantidad);
        List<Recambio> recambios = recambioService.findByCantidadStock(cantidad);
        logger.info("Fin getByCantidadStock " + cantidad);
        return recambios;
    }

    @DeleteMapping("/recambio/{id}")
    public Recambio deleteRecambio(@PathVariable long id) throws RecambioNotFoundException {
        logger.info("Inicio deleteRecambio " + id);
        Recambio recambio = recambioService.deleteRecambio(id);
        logger.info("Fin deleteRecambio " + id);
        return recambio;
    }

    @PostMapping("/recambio")
    public Recambio addRecambio(@RequestBody Recambio recambio) {
        logger.info("Inicio addRecambio");
        Recambio newRecambio = recambioService.addRecambio(recambio);
        logger.info("Fin addRecambio");
        return newRecambio;
    }

    @PutMapping("/recambio/{id}")
    public Recambio modifyRecambio(@RequestBody Recambio recambio, @PathVariable long id) throws RecambioNotFoundException {
        logger.info("Inicio modifyRecambio " + id);
        Recambio newRecambio = recambioService.modifyRecambio(id, recambio);
        logger.info("Fin modifyRecambio " + id);
        return newRecambio;
    }

    @PatchMapping("/recambio/{id}")
    public Recambio modifyDescripcionRecambio(@PathVariable long id, @RequestBody String descripcion) throws RecambioNotFoundException {
        logger.info("Inicio modifyDescripcionRecambio " + id + " a " + descripcion);
        Recambio recambio = recambioService.modifyDescripcionRecambio(id, descripcion);
        logger.info("Fin modifyDescripcionRecambio " + id + " a " + descripcion);
        return recambio;
    }

    @ExceptionHandler(RecambioNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRecambioNotFoundException(RecambioNotFoundException rnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", rnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecambioNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse("999", "Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
