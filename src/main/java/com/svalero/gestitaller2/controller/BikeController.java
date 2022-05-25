package com.svalero.gestitaller2.controller;

import com.svalero.gestitaller2.domain.Bike;
import com.svalero.gestitaller2.exception.ErrorResponse;
import com.svalero.gestitaller2.exception.BikeNotFoundException;
import com.svalero.gestitaller2.service.BikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BikeController {

    @Autowired
    private BikeService bikeService;

    private final Logger logger = LoggerFactory.getLogger(BikeController.class);

    @GetMapping("/bikes")
    public List<Bike> getBikes() {
        logger.info("Inicio getBikes");
        List<Bike> bikes = bikeService.findAll();
        logger.info("Fin getBikes");
        return bikes;
    }

    @GetMapping("/bike/{id}")
    public Bike getById(@PathVariable long id) throws BikeNotFoundException {
        logger.info("Inicio getById " + id);
        Bike bike = bikeService.findById(id);
        logger.info("Fin getById " + id);
        return bike;
    }

    @GetMapping("/bikes/{brand}")
    public List<Bike> getByBrand(@PathVariable String brand) throws BikeNotFoundException {
        logger.info("Inicio getByBrand " + brand);
        List<Bike> bikes = bikeService.findByBrand(brand);
        logger.info("Fin getByBrand " + brand);
        return bikes;
    }

    @DeleteMapping("/bike/{id}")
    public Bike deleteBike(@PathVariable long id) throws BikeNotFoundException {
        logger.info("Inicio deleteBike " + id);
        Bike bike = bikeService.deleteBike(id);
        logger.info("Fin deleteBike " + id);
        return bike;
    }

    @PostMapping("/bike")
    public Bike addBike(@RequestBody Bike bike) {
        logger.info("Inicio addBike");
        Bike newBike = bikeService.addBike(bike);
        logger.info("Fin addBike");
        return newBike;
    }

    @PutMapping("/bike/{id}")
    public Bike modifyBike(@RequestBody Bike bike, @PathVariable long id) throws BikeNotFoundException {
        logger.info("Inicio modifyBike " + id);
        Bike newBike = bikeService.modifyBike(id, bike);
        logger.info("Fin modifyBike " + id);
        return newBike;
    }

    @PatchMapping("/bike/{id}")
    public Bike modifyBrandBike(@PathVariable long id, @RequestBody String brand) throws BikeNotFoundException {
        logger.info("Inicio modifyBrandBike " + id + " a " + brand);
        Bike bike = bikeService.modifyBrand(id, brand);
        logger.info("Fin modifyBrandBike " + id + " a " + brand);
        return bike;
    }

    @ExceptionHandler(BikeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBikeNotFoundException(BikeNotFoundException bnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", bnfe.getMessage());
        logger.info(bnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BikeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse("999", "Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}