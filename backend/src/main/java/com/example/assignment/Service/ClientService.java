package com.example.assignment.Service;

import com.example.assignment.DTO.ClientDTO;
import com.example.assignment.Entity.Client;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    public Client saveClient(ClientDTO clientDTO);
    public Client fetchClientByID(UUID clientID);
    public Client updateClientByID(UUID clientID, ClientDTO clientDTO);
    public void deleteClientByID(UUID clientID);
    public List<Client> fetchClientByName(String clientName, int page, int size);
}
