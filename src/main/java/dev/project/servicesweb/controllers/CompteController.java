package dev.project.servicesweb.controllers;

import dev.project.servicesweb.exceptions.ResourceNotFoundException;
import dev.project.servicesweb.models.Client;
import dev.project.servicesweb.models.Compte;
import dev.project.servicesweb.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/compte")
public class CompteController {

    @Autowired
    private CompteRepository compteRepository;

    @GetMapping("/compte-list")
    public List<Compte> getAllComptes() {return compteRepository.findAll();}

    @GetMapping("/compte-list/{id}")
    public ResponseEntity<Compte> getCompteById(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        Compte compte = compteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found for this id: " + id));

        return ResponseEntity.ok().body(compte);
    }

    @PostMapping("/compte-list")
    public Compte createCompte(@RequestBody Compte compte) {
        return compteRepository.save(compte);
    }

    @PutMapping("/compte-list/{id}")
    public ResponseEntity<Compte> updateCompte(@PathVariable(value = "id") int id, @RequestBody Compte compteDetails) throws ResourceNotFoundException {
        Compte compte = compteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found for this id: "+id));

        compte.setSolde(compteDetails.getSolde());
        compte.setDecouvert(compteDetails.getDecouvert());
        compte.setClient(compteDetails.getClient());
        compte.setAgence(compteDetails.getAgence());
        final Compte updatedCompte = compteRepository.save(compte);

        return ResponseEntity.ok(updatedCompte);
    }

    @DeleteMapping("/compte-list/{id}")
    public Map<String, Boolean> deleteCompte(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        Compte compte = compteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found for this id: "+id));

        compteRepository.delete(compte);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return  response;
    }

}
