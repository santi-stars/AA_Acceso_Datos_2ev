package com.svalero.gestitaller2.controller;

import com.svalero.gestitaller2.domain.Mecanico;
import com.svalero.gestitaller2.exception.ErrorResponse;
import com.svalero.gestitaller2.exception.MecanicoNotFoundException;
import com.svalero.gestitaller2.service.MecanicoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MecanicoController {

    @Autowired
    private MecanicoService mecanicoService;

    private final Logger logger = LoggerFactory.getLogger(MecanicoController.class);

    @GetMapping("/mecanicos")
    public List<Mecanico> getMecanicos(@RequestParam(value = "nombre", defaultValue = "0") String nombre) throws MecanicoNotFoundException {
        logger.info("Inicio getMecanicos");
        List<Mecanico> mecanicos;

        if (nombre.equals("0")) {
            logger.info("Mostrado de todos los mecanicos");
            logger.info("Fin getMecanicos");
            return mecanicos = mecanicoService.findAllMecanicos();
        } else {
            logger.info("Filtrado por nombre de mecanico: " + nombre);
            logger.info("Fin getMecanicos");
            return mecanicos = mecanicoService.findByNombre(nombre);
        }
    }

    @GetMapping("/mecanico/{id}")
    public Mecanico getMecanico(@PathVariable long id) throws MecanicoNotFoundException {
        logger.info("Inicio getMecanico " + id);
        Mecanico mecanico = mecanicoService.findMecanico(id);
        logger.info("Fin getMecanico " + id);
        return mecanico;
    }

    @GetMapping("/mecanicos/{nombre}")
    public List<Mecanico> getMecanico(@PathVariable String nombre) throws MecanicoNotFoundException {
        logger.info("Inicio getMecanico " + nombre);
        List<Mecanico> mecanicos = mecanicoService.findByNombre(nombre);
        logger.info("Fin getMecanico " + nombre);
        return mecanicos;
    }

    @DeleteMapping("/mecanico/{id}")
    public Mecanico deleteMecanico(@PathVariable long id) throws MecanicoNotFoundException {
        logger.info("Inicio deleteMecanico " + id);
        Mecanico mecanico = mecanicoService.deleteMecanico(id);
        logger.info("Fin deleteMecanico " + id);
        return mecanico;
    }

    @PostMapping("/mecanico")
    public Mecanico addMecanico(@RequestBody Mecanico mecanico) {
        logger.info("Inicio addMecanico");
        Mecanico newMecanico = mecanicoService.addMecanico(mecanico);
        logger.info("Fin addMecanico");
        return newMecanico;
    }

    @PutMapping("/mecanico/{id}")
    public Mecanico modifyMecanico(@RequestBody Mecanico mecanico, @PathVariable long id) throws MecanicoNotFoundException {
        logger.info("Inicio modifyMecanico " + id);
        Mecanico newMecanico = mecanicoService.modifyMecanico(id, mecanico);
        logger.info("Fin modifyMecanico " + id);
        return newMecanico;
    }

    @PatchMapping("/mecanico/{id}")
    public Mecanico modifyMecanicoDisponible(@PathVariable long id, @RequestBody boolean disponible) throws MecanicoNotFoundException {
        logger.info("Inicio modifyMecanicoDisponible " + id + " a " + disponible);
        Mecanico mecanico = mecanicoService.modifyFacturaPagada(id, disponible);
        logger.info("Fin modifyMecanicoDisponible " + id + " a " + disponible);
        return mecanico;
    }

    @ExceptionHandler(MecanicoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMecanicoNotFoundException(MecanicoNotFoundException mnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", mnfe.getMessage());
        logger.info(mnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MecanicoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse("999", "Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
