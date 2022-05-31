package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.Bike;
import com.svalero.gestitaller2.domain.Client;
import com.svalero.gestitaller2.domain.WorkOrder;
import com.svalero.gestitaller2.exception.ClientNotFoundException;
import com.svalero.gestitaller2.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public List<Client> findAllClients(String name, String surname, String dni) {
        return clientRepository.findByNameOrSurnameOrDni(name, surname, dni);
    }

    @Override
    public Client findById(long id) throws ClientNotFoundException {
        return clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
    }

    @Override
    public List<Client> findByName(String name) {
        return clientRepository.findByName(name);
    }

    @Override
    public Client deleteClient(long id) throws ClientNotFoundException {
        Client client = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        // TODO pone a null la lista de órdenes del cliente a borrar
        for (WorkOrder workOrder : client.getWorkOrders()) workOrder.setClient(null);
        for (Bike bike : client.getBikes()) bike.setClient(null);
        clientRepository.delete(client);
        return client;
    }

    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client modifyClient(long id, Client newClient) throws ClientNotFoundException {
        clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        newClient.setId(id);
        clientRepository.save(newClient);
        return newClient;
    }

    @Override
    public Client modifyClientName(long id, String name) throws ClientNotFoundException {
        Client client = clientRepository.findById(id)
                .orElseThrow(ClientNotFoundException::new);
        client.setName(name);
        return clientRepository.save(client);
    }
}