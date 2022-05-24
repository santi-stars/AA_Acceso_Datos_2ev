package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.Cliente;
import com.svalero.gestitaller2.exception.ClienteNotFoundException;
import com.svalero.gestitaller2.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public List<Cliente> findAllClientes(String nombre, String apellido, String dni) {
        return clienteRepository.findByNombreOrApellidoOrDni(nombre, apellido, dni);
    }

    @Override
    public Cliente findById(long id) throws ClienteNotFoundException {
        return clienteRepository.findById(id).orElseThrow(ClienteNotFoundException::new);
    }

    @Override
    public List<Cliente> findByNombre(String nombre) {
        return clienteRepository.findByNombre(nombre);
    }

    @Override
    public Cliente deleteCliente(long id) throws ClienteNotFoundException {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(ClienteNotFoundException::new);
        clienteRepository.delete(cliente);
        return cliente;
    }

    @Override
    public Cliente addCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente modifyCliente(long id, Cliente newCliente) throws ClienteNotFoundException {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(ClienteNotFoundException::new);
        cliente.setNombre(newCliente.getNombre());
        cliente.setApellido(newCliente.getApellido());
        cliente.setEdad(newCliente.getEdad());
        cliente.setDni(newCliente.getDni());
        cliente.setDireccion(newCliente.getDireccion());
        cliente.setClienteVip(newCliente.isClienteVip());

        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente modifyNombreCliente(long id, String nombre) throws ClienteNotFoundException {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(ClienteNotFoundException::new);
        cliente.setNombre(nombre);
        return clienteRepository.save(cliente);
    }
}