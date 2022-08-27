package com.svalero.gestitaller2.controller;

import com.svalero.gestitaller2.domain.Client;
import com.svalero.gestitaller2.exception.ClientNotFoundException;
import com.svalero.gestitaller2.exception.ErrorResponse;
import com.svalero.gestitaller2.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    private final Logger logger = LoggerFactory.getLogger(ClientController.class);

    // FILTRADO por 3 campos
    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getClients(@RequestParam(name = "name", required = false) String name,
                                                   @RequestParam(name = "surname", required = false) String surname,
                                                   @RequestParam(name = "dni", required = false) String dni,
                                                   @RequestParam(name = "all", defaultValue = "false") boolean all) {
        List<Client> clients;
        logger.info("Inicio getClients");
        if (all) {
            logger.info("Mostrado de todos los clients");
            clients = clientService.findAllClients();
        } else {
            logger.info("Filtrado por name, surname o dni");
            clients = clientService.findAllClients(name, surname, dni);
        }
        logger.info("Fin getClients");
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Client> getById(@PathVariable long id) throws ClientNotFoundException {
        logger.info("Inicio getById " + id);
        Client client = clientService.findById(id);
        logger.info("Fin getById " + id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping("/client")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        logger.info("Inicio addClient");
        Client newClient = clientService.addClient(client);
        logger.info("Fin addClient");
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable long id) throws ClientNotFoundException {
        logger.info("Inicio deleteClient " + id);
        Client client = clientService.deleteClient(id);
        logger.info("Fin deleteClient " + id);
        return new ResponseEntity<>(client, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<Client> modifyClient(@RequestBody Client client, @PathVariable long id) throws ClientNotFoundException {
        logger.info("Inicio modifyClient " + id);
        Client newClient = clientService.modifyClient(id, client);
        logger.info("Fin modifyClient " + id);
        return new ResponseEntity<>(newClient, HttpStatus.OK);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleClientNotFoundException(ClientNotFoundException cnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", cnfe.getMessage());
        logger.info(cnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse("999", "Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        logger.error(Arrays.toString(ex.getStackTrace()));
        logger.error(ex.getMessage(), ex);
        return errors;
    }
}
