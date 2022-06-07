package com.svalero.gestitaller2.controller;

import com.svalero.gestitaller2.domain.Bike;
import com.svalero.gestitaller2.domain.Client;
import com.svalero.gestitaller2.domain.dto.BikeDTO;
import com.svalero.gestitaller2.exception.ClientNotFoundException;
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
    public List<Bike> getBikes(@RequestParam(name = "brand", required = false) String brand,
                               @RequestParam(name = "model", required = false) String model,
                               @RequestParam(name = "license", required = false) String license,
                               @RequestParam(name = "all", defaultValue = "false") boolean all) {
        List<Bike> bikes;
        logger.info("Inicio getBikes");
        if (all) {
            logger.info("Mostrado de todas las bikes");
            bikes = bikeService.findAll();
        } else {
            logger.info("Filtrado por brand, model, license");
            bikes = bikeService.findAll(brand, model, license);
        }
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

    @GetMapping("/client/{id}/bikes")
    public List<Bike> getBikesByClient(@PathVariable long id) throws ClientNotFoundException, BikeNotFoundException {
        logger.info("Inicio getBikesByClient " + id);
        List<Bike> bikes = bikeService.findBikesByClient(id);
        logger.info("Fin getBikesByClient " + id);
        return bikes;
    }

    @PostMapping("/bike")
    public Bike addBike(@RequestBody BikeDTO bikeDTO) throws ClientNotFoundException {
        logger.info("Inicio addBike");
        Bike newBike = bikeService.addBike(bikeDTO);
        logger.info("Fin addBike");
        return newBike;
    }

    @DeleteMapping("/bike/{id}")
    public Bike deleteBike(@PathVariable long id) throws BikeNotFoundException {
        logger.info("Inicio deleteBike " + id);
        Bike bike = bikeService.deleteBike(id);
        logger.info("Fin deleteBike " + id);
        return bike;
    }

    @PutMapping("/bike/{id}")
    public Bike modifyBike(@RequestBody BikeDTO bikeDTO, @PathVariable long id) throws BikeNotFoundException, ClientNotFoundException {
        logger.info("Inicio modifyBike " + id);
        Bike newBike = bikeService.modifyBike(id, bikeDTO);
        logger.info("Fin modifyBike " + id);
        return newBike;
    }

    @PatchMapping("/bike/{id}/brand")
    public Bike modifyBrandBike(@PathVariable long id, @RequestBody String brand) throws BikeNotFoundException {
        logger.info("Inicio modifyBrandBike " + id + " a " + brand);
        Bike bike = bikeService.modifyBrand(id, brand);
        logger.info("Fin modifyBrandBike " + id + " a " + brand);
        return bike;
    }

    @PatchMapping("/bike/{id}/client")
    public Bike modifyClientBike(@PathVariable long id, @RequestBody Client client) throws BikeNotFoundException {
        logger.info("Inicio modifyClientBike " + id + " a " + client);
        Bike bike = bikeService.modifyClient(id, client);
        logger.info("Fin modifyClientBike " + id + " a " + client);
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
