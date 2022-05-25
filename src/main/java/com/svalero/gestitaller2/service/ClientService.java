package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.Client;
import com.svalero.gestitaller2.exception.ClientNotFoundException;

import java.util.List;

public interface ClientService {

    List<Client> findAllClients();

    List<Client> findAllClients(String name, String surname, String dni);

    Client findById(long id) throws ClientNotFoundException;

    List<Client> findByName(String name) throws ClientNotFoundException;

    Client deleteClient(long id) throws ClientNotFoundException;

    Client addClient(Client client);

    Client modifyClient(long id, Client client) throws ClientNotFoundException;

    Client modifyClientName(long id, String name) throws ClientNotFoundException;
}
