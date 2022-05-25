package com.svalero.gestitaller2.controller;

import com.svalero.gestitaller2.domain.Spare;
import com.svalero.gestitaller2.exception.ErrorResponse;
import com.svalero.gestitaller2.exception.SpareNotFoundException;
import com.svalero.gestitaller2.service.SpareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpareController {

    @Autowired
    private SpareService spareService;

    private final Logger logger = LoggerFactory.getLogger(SpareController.class);

    @GetMapping("/spares")
    public List<Spare> getSpares() {
        logger.info("Inicio getSpares");
        List<Spare> spares = spareService.findAll();
        logger.info("Fin getSpares");
        return spares;
    }

    @GetMapping("/spare/{id}")
    public Spare getById(@PathVariable long id) throws SpareNotFoundException {
        logger.info("Inicio getById " + id);
        Spare spare = spareService.findById(id);
        logger.info("Fin getById " + id);
        return spare;
    }

    @GetMapping("/spares/{amount}")
    public List<Spare> getByStockAmount(@PathVariable int amount) throws SpareNotFoundException {
        logger.info("Inicio getByAmountStock " + amount);
        List<Spare> spares = spareService.findByStockAmount(amount);
        logger.info("Fin getByAmountStock " + amount);
        return spares;
    }

    @DeleteMapping("/spare/{id}")
    public Spare deleteSpare(@PathVariable long id) throws SpareNotFoundException {
        logger.info("Inicio deleteSpare " + id);
        Spare spare = spareService.deleteSpare(id);
        logger.info("Fin deleteSpare " + id);
        return spare;
    }

    @PostMapping("/spare")
    public Spare addSpare(@RequestBody Spare spare) {
        logger.info("Inicio addSpare");
        Spare newSpare = spareService.addSpare(spare);
        logger.info("Fin addSpare");
        return newSpare;
    }

    @PutMapping("/spare/{id}")
    public Spare modifySpare(@RequestBody Spare spare, @PathVariable long id) throws SpareNotFoundException {
        logger.info("Inicio modifySpare " + id);
        Spare newSpare = spareService.modifySpare(id, spare);
        logger.info("Fin modifySpare " + id);
        return newSpare;
    }

    @PatchMapping("/spare/{id}")
    public Spare modifySpareDescription(@PathVariable long id, @RequestBody String description) throws SpareNotFoundException {
        logger.info("Inicio modifySpareDescription " + id + " a " + description);
        Spare spare = spareService.modifySpareDescription(id, description);
        logger.info("Fin modifySpareDescription " + id + " a " + description);
        return spare;
    }

    @ExceptionHandler(SpareNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSpareNotFoundException(SpareNotFoundException snfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", snfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SpareNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse("999", "Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
