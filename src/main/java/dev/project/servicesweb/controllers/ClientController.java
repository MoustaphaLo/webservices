package dev.project.servicesweb.controllers;

import dev.project.servicesweb.exceptions.ResourceNotFoundException;
import dev.project.servicesweb.models.Client;
import dev.project.servicesweb.repositories.ClientRepository;
import dev.project.servicesweb.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    private CompteRepository compteRepository;

    @GetMapping("/clients-list")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/clients-list/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + id));

        return ResponseEntity.ok().body(client);
    }

    @PostMapping("/clients-list")
    public Client createClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PutMapping("/clients-list/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable(value = "id") Long id, @RequestBody Client clientDetails) throws ResourceNotFoundException {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found for this id: "+id));

        client.setPrenom(clientDetails.getPrenom());
        client.setNom(clientDetails.getNom());
        client.setDate_naissance(clientDetails.getDate_naissance());
        final Client updatedClient = clientRepository.save(client);

        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/clients-list/{id}")
    public Map<String, Boolean> deleteClient(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found for this id: "+id));


        clientRepository.delete(client);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return  response;
    }
}
