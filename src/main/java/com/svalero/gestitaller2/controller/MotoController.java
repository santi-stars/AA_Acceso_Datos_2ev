package com.svalero.gestitaller2.controller;

import com.svalero.gestitaller2.domain.Moto;
import com.svalero.gestitaller2.exception.ErrorResponse;
import com.svalero.gestitaller2.exception.MotoNotFoundException;
import com.svalero.gestitaller2.service.MotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MotoController {

    @Autowired
    private MotoService motoService;

    private final Logger logger = LoggerFactory.getLogger(MotoController.class);

    @GetMapping("/motos")
    public List<Moto> getMotos() {
        logger.info("Inicio getMotos");
        List<Moto> motos = motoService.findAll();
        logger.info("Fin getMotos");
        return motos;
    }

    @GetMapping("/moto/{id}")
    public Moto getById(@PathVariable long id) throws MotoNotFoundException {
        logger.info("Inicio getById " + id);
        Moto moto = motoService.findById(id);
        logger.info("Fin getById " + id);
        return moto;
    }

    @GetMapping("/motos/{marca}")
    public List<Moto> getByMarca(@PathVariable String marca) throws MotoNotFoundException {
        logger.info("Inicio getByMarca " + marca);
        List<Moto> motos = motoService.findByMarca(marca);
        logger.info("Fin getByMarca " + marca);
        return motos;
    }

    @DeleteMapping("/moto/{id}")
    public Moto deleteMoto(@PathVariable long id) throws MotoNotFoundException {
        logger.info("Inicio deleteMoto " + id);
        Moto moto = motoService.deleteMoto(id);
        logger.info("Fin deleteMoto " + id);
        return moto;
    }

    @PostMapping("/moto")
    public Moto addMoto(@RequestBody Moto moto) {
        logger.info("Inicio addMoto");
        Moto newMoto = motoService.addMoto(moto);
        logger.info("Fin addMoto");
        return newMoto;
    }

    @PutMapping("/moto/{id}")
    public Moto modifyMoto(@RequestBody Moto moto, @PathVariable long id) throws MotoNotFoundException {
        logger.info("Inicio modifyMoto " + id);
        Moto newMoto = motoService.modifyMoto(id, moto);
        logger.info("Fin modifyMoto " + id);
        return newMoto;
    }

    @PatchMapping("/moto/{id}")
    public Moto modifyMarcaMoto(@PathVariable long id, @RequestBody String marca) throws MotoNotFoundException {
        logger.info("Inicio modifyMarcaMoto " + id + " a " + marca);
        Moto moto = motoService.modifyMarcaMoto(id, marca);
        logger.info("Fin modifyMarcaMoto " + id + " a " + marca);
        return moto;
    }

    @ExceptionHandler(MotoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMotoNotFoundException(MotoNotFoundException mnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", mnfe.getMessage());
        logger.info(mnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MotoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse("999", "Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
