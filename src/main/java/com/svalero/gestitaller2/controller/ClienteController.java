package com.svalero.gestitaller2.controller;

import com.svalero.gestitaller2.domain.Cliente;
import com.svalero.gestitaller2.domain.Factura;
import com.svalero.gestitaller2.domain.dto.FacturaDTO;
import com.svalero.gestitaller2.exception.ClienteNotFoundException;
import com.svalero.gestitaller2.exception.ErrorResponse;
import com.svalero.gestitaller2.exception.FacturaNotFoundException;
import com.svalero.gestitaller2.exception.MotoNotFoundException;
import com.svalero.gestitaller2.service.ClienteService;
import com.svalero.gestitaller2.service.FacturaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private FacturaService facturaService;

    private final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    // FILTRADO por 3 campos
    @GetMapping("/clientes")
    public List<Cliente> getClientes(@RequestParam(name = "nombre", required = false) String nombre,
                                     @RequestParam(name = "apellido", required = false) String apellido,
                                     @RequestParam(name = "dni", required = false) String dni,
                                     @RequestParam(name = "all", defaultValue = "false") boolean all) {
        List<Cliente> clientes;
        logger.info("Inicio getClientes");
        if (all) {
            logger.info("Mostrado de todos los clientes");
            clientes = clienteService.findAllClientes();
        } else {
            logger.info("Filtrado por nombre, apellido o dni");
            clientes = clienteService.findAllClientes(nombre, apellido, dni);
        }
        logger.info("Fin getClientes");
        return clientes;
    }

    @GetMapping("/cliente/{id}")
    public Cliente getById(@PathVariable long id) throws ClienteNotFoundException {
        logger.info("Inicio getById " + id);
        Cliente cliente = clienteService.findById(id);
        logger.info("Fin getById " + id);
        return cliente;
    }

    @GetMapping("/clientes/{nombre}")
    public List<Cliente> getByNombre(@PathVariable String nombre) throws ClienteNotFoundException {
        logger.info("Inicio getByNombre " + nombre);
        List<Cliente> clientes = clienteService.findByNombre(nombre);
        logger.info("Fin getByNombre " + nombre);
        return clientes;
    }

    @DeleteMapping("/cliente/{id}")
    public Cliente deleteCliente(@PathVariable long id) throws ClienteNotFoundException {
        logger.info("Inicio deleteCliente " + id);
        Cliente cliente = clienteService.deleteCliente(id);
        logger.info("Fin deleteCliente " + id);
        return cliente;
    }

    @PostMapping("/cliente")
    public Cliente addCliente(@RequestBody Cliente cliente) {
        logger.info("Inicio addCliente");
        Cliente newCliente = clienteService.addCliente(cliente);
        logger.info("Fin addCliente");
        return newCliente;
    }

    @PutMapping("/cliente/{id}")
    public Cliente modifyCliente(@RequestBody Cliente cliente, @PathVariable long id) throws ClienteNotFoundException {
        logger.info("Inicio modifyCliente " + id);
        Cliente newCliente = clienteService.modifyCliente(id, cliente);
        logger.info("Fin modifyCliente " + id);
        return newCliente;
    }

    @PatchMapping("/cliente/{id}")
    public Cliente modifyNombreCliente(@PathVariable long id, @RequestBody String nombre) throws ClienteNotFoundException {
        logger.info("Inicio modifyEdadCliente " + id + " a nombre " + nombre);
        Cliente cliente = clienteService.modifyNombreCliente(id, nombre);
        logger.info("Fin modifyEdadCliente " + id + " a nombre " + nombre);
        return cliente;
    }

    @GetMapping("/cliente/{id}/facturas")
    public List<Factura> getFacturasPorCliente(@PathVariable long id) throws ClienteNotFoundException, FacturaNotFoundException {
        Cliente cliente = clienteService.findById(id);
        List<Factura> facturas = facturaService.findByCliente(cliente);

        return facturas;
    }

    // JPQL y DTO
    @GetMapping("/cliente/moto/facturas")
    public List<Factura> findByClienteAndMoto(@RequestBody FacturaDTO facturaDTO) throws
            ClienteNotFoundException, MotoNotFoundException {
        logger.info("Inicio findByClienteAndMoto");
        List<Factura> facturas = facturaService.findByClienteAndMoto(facturaDTO);
        logger.info("Fin findByClienteAndMoto");
        return facturas;
    }

    // JPQL y DTO
    @DeleteMapping("moto/facturas")
    public void deleteByMoto(@RequestBody FacturaDTO facturaDTO) throws MotoNotFoundException {
        logger.info("Inicio deleteByMoto");
        facturaService.deleteByMoto(facturaDTO);
        logger.info("Fin deleteByMoto");
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleClienteNotFoundException(ClienteNotFoundException cnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", cnfe.getMessage());
        logger.info(cnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse("999", "Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
