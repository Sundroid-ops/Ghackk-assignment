package com.example.assignment.Controller;

import com.example.assignment.DTO.ClientDTO;
import com.example.assignment.Entity.Client;
import com.example.assignment.Service.ClientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ClientController {
    @Autowired
    private ClientService clientService;
    private static final Logger logs = LoggerFactory.getLogger(ClientController.class);

    @PostMapping("/clients")
    public ResponseEntity<Client> saveClient(@Valid @RequestBody ClientDTO clientDTO){
        logs.info("Incoming POST request for creating new client data : {}", clientDTO);
        Client client = clientService.saveClient(clientDTO);
        logs.info("Client Successfully created : {}", client);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/clients/{ID}")
    public ResponseEntity<Client> fetchClientByID(@PathVariable UUID ID){
        logs.info("Incoming GET request for searching client from ID: {}", ID);
        Client client = clientService.fetchClientByID(ID);
        logs.info("Client successfully found from ID: {}", ID);
        return ResponseEntity.ok(client);
    }

    @PutMapping("/clients/{ID}")
    public ResponseEntity<Client> updateClientByID(@PathVariable UUID ID, @Valid @RequestBody ClientDTO clientDTO){
        logs.info("Incoming PUT request for client ID: {}", ID);
        Client client = clientService.updateClientByID(ID, clientDTO);
        logs.info("Client Successfully updated for ID: {}", ID);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/clients/{ID}")
    public ResponseEntity<Map<String, String>> deleteClientByID(@PathVariable UUID ID){
        logs.info("Incoming Delete request for client ID: {}", ID);
        clientService.deleteClientByID(ID);
        logs.info("Client Successfully deleted for ID: {}", ID);
        Map<String, String> msg = new HashMap<>();
        msg.put("msg", "Client Successfully Deleted");
        return ResponseEntity.ok().body(msg);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> fetchClientByName(
            @RequestParam String name,
            @RequestParam int page,
            @RequestParam int size){
        logs.info("Incoming GET request for searching client from name: {}", name);
        List<Client> clientList = clientService.fetchClientByName(name, page, size);
        logs.info("Client successfully found from name: {}", name);
        return ResponseEntity.ok(clientList);
    }
}
