package com.example.assignment.Service.Impl;

import com.example.assignment.DTO.ClientDTO;
import com.example.assignment.Entity.Client;
import com.example.assignment.Errors.ClientNotFoundError;
import com.example.assignment.Errors.InternalServerError;
import com.example.assignment.Repository.ClientRepo;
import com.example.assignment.Service.ClientService;
import com.example.assignment.Service.CloudinaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private CloudinaryService cloudinaryService;
    private static final Logger logs = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Override
    public Client saveClient(ClientDTO clientDTO) {
        try {
            logs.info("building client data from : {}", clientDTO);
            Client buildClient = Client.builder()
                    .clientID(UUID.randomUUID())
                    .clientName(clientDTO.getClientName())
                    .contactInfo(clientDTO.getContactInfo())
                    .receivedDate(clientDTO.getReceivedDate())
                    .inventoryReceived(clientDTO.getInventoryReceived())
                    .reportedIssues(clientDTO.getReportedIssues())
                    .clientNotes(clientDTO.getClientNotes())
                    .assignedTechnician(clientDTO.getAssignedTechnician())
                    .estimatedAmount(clientDTO.getEstimatedAmount())
                    .deadline(clientDTO.getDeadline())
                    .status(clientDTO.getStatus())
                    .Notes(clientDTO.getNotes())
                    .build();

            if(clientDTO.getInventoryMedia() != null)
                buildClient.setInventoryURL(cloudinaryService.uploadMediaFile(clientDTO.getInventoryMedia(), clientDTO.getInventoryReceived()));

            logs.info("Saving client data into client table: {}", buildClient);
            Client client = clientRepo.save(buildClient);
            logs.info("Client Data saved successfully in client table : {}", client);

            return client;
        }catch (Exception exception){
            logs.warn("Error occurred during saving client data : {}", exception.getMessage());
            throw new InternalServerError("Internal server Error");
        }
    }

    @Override
    public Client fetchClientByID(UUID clientID) {
        logs.info("Searching Client from clientID: {}", clientID);
        Client client = clientRepo.findById(clientID)
                .orElseThrow(() -> new ClientNotFoundError("Client Not found"));
        logs.info("Client Successfully found from clientID: {}", clientID);
        return client;
    }

    @Override
    public Client updateClientByID(UUID clientID, ClientDTO clientDTO) {
        Client getClient = fetchClientByID(clientID);

        logs.info("Updating client with ID: {}", clientID);
        getClient.setClientName(clientDTO.getClientName());
        getClient.setContactInfo(clientDTO.getContactInfo());
        getClient.setReceivedDate(clientDTO.getReceivedDate());
        getClient.setInventoryReceived(clientDTO.getInventoryReceived());
        getClient.setReportedIssues(clientDTO.getReportedIssues());
        getClient.setClientNotes(clientDTO.getClientNotes());
        getClient.setAssignedTechnician(clientDTO.getAssignedTechnician());
        getClient.setEstimatedAmount(clientDTO.getEstimatedAmount());
        getClient.setDeadline(clientDTO.getDeadline());
        getClient.setStatus(clientDTO.getStatus());
        getClient.setNotes(clientDTO.getNotes());

        Client client = clientRepo.save(getClient);
        logs.info("Successfully updated Client with ID: {}", clientID);
        return client;
    }

    @Override
    public void deleteClientByID(UUID clientID) {
        clientRepo.findById(clientID)
                .orElseThrow(() -> new ClientNotFoundError("Client Not found"));
        logs.info("Deleting Client by ID: {}", clientID);
        clientRepo.deleteById(clientID);
        logs.info("Client Successfully Deleted for ID: {}", clientID);
    }

    @Override
    public List<Client> fetchClientByName(String clientName, int page, int size) {
        logs.info("Searching Clients from name: {}", clientName);
        List<Client> clientList = clientRepo.findByClientNameContainingIgnoreCase(clientName, PageRequest.of(page, size));
        if(clientList.isEmpty())
            throw new ClientNotFoundError("No Client Found");

        logs.info("Clients Record found from name: {}", clientName);
        return clientList;
    }
}
