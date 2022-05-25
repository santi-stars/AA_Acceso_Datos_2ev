package com.svalero.gestitaller2.controller;

import com.svalero.gestitaller2.domain.Mechanic;
import com.svalero.gestitaller2.exception.ErrorResponse;
import com.svalero.gestitaller2.exception.MechanicNotFoundException;
import com.svalero.gestitaller2.service.MechanicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MechanicController {

    @Autowired
    private MechanicService mechanicService;

    private final Logger logger = LoggerFactory.getLogger(MechanicController.class);

    @GetMapping("/mechanics")
    public List<Mechanic> getMechanics(@RequestParam(value = "name", defaultValue = "0") String name) throws MechanicNotFoundException {
        logger.info("Inicio getMechanics");
        List<Mechanic> mechanics;

        if (name.equals("0")) {
            logger.info("Mostrado de todos los mechanics");
            logger.info("Fin getMechanics");
            return mechanics = mechanicService.findAllMechanics();
        } else {
            logger.info("Filtrado por name de mechanic: " + name);
            logger.info("Fin getMechanics");
            return mechanics = mechanicService.findByName(name);
        }
    }

    @GetMapping("/mechanic/{id}")
    public Mechanic getMechanic(@PathVariable long id) throws MechanicNotFoundException {
        logger.info("Inicio getMechanic " + id);
        Mechanic mechanic = mechanicService.findMechanic(id);
        logger.info("Fin getMechanic " + id);
        return mechanic;
    }

    @GetMapping("/mechanics/{name}")
    public List<Mechanic> getMechanic(@PathVariable String name) throws MechanicNotFoundException {
        logger.info("Inicio getMechanic " + name);
        List<Mechanic> mechanics = mechanicService.findByName(name);
        logger.info("Fin getMechanic " + name);
        return mechanics;
    }

    @DeleteMapping("/mechanic/{id}")
    public Mechanic deleteMechanic(@PathVariable long id) throws MechanicNotFoundException {
        logger.info("Inicio deleteMechanic " + id);
        Mechanic mechanic = mechanicService.deleteMechanic(id);
        logger.info("Fin deleteMechanic " + id);
        return mechanic;
    }

    @PostMapping("/mechanic")
    public Mechanic addMechanic(@RequestBody Mechanic mechanic) {
        logger.info("Inicio addMechanic");
        Mechanic newMechanic = mechanicService.addMechanic(mechanic);
        logger.info("Fin addMechanic");
        return newMechanic;
    }

    @PutMapping("/mechanic/{id}")
    public Mechanic modifyMechanic(@RequestBody Mechanic mechanic, @PathVariable long id) throws MechanicNotFoundException {
        logger.info("Inicio modifyMechanic " + id);
        Mechanic newMechanic = mechanicService.modifyMechanic(id, mechanic);
        logger.info("Fin modifyMechanic " + id);
        return newMechanic;
    }

    @PatchMapping("/mechanic/{id}")
    public Mechanic modifyMechanicAvailable(@PathVariable long id, @RequestBody boolean available) throws MechanicNotFoundException {
        logger.info("Inicio modifyMechanicAvailable " + id + " a " + available);
        Mechanic mechanic = mechanicService.modifyPaidInvoice(id, available);
        logger.info("Fin modifyMechanicAvailable " + id + " a " + available);
        return mechanic;
    }

    @ExceptionHandler(MechanicNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMechanicNotFoundException(MechanicNotFoundException mnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", mnfe.getMessage());
        logger.info(mnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MechanicNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse("999", "Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
