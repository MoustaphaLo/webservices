package dev.project.servicesweb.controllers;

import dev.project.servicesweb.exceptions.ResourceNotFoundException;
import dev.project.servicesweb.models.Agence;
import dev.project.servicesweb.repositories.AgenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/agence")
public class AgenceController {

    @Autowired
    private AgenceRepository agenceRepository;

    @GetMapping("/agence-list")
    public List<Agence> getAllAgences() {
        return agenceRepository.findAll();
    }

    @GetMapping("/agence-list/{code}")
    public ResponseEntity<Agence> getAgenceById(@PathVariable(value = "code") String code) throws ResourceNotFoundException{
        Agence agence = agenceRepository.findById(code).orElseThrow(() -> new ResourceNotFoundException("Agence not found for code: "+code));

        return ResponseEntity.ok().body(agence);
    }

    @PostMapping("/agence-list")
    public Agence createAgence(@RequestBody Agence agence) {
        return agenceRepository.save(agence);
    }

    @PutMapping("/agence-list/{code}")
    public ResponseEntity<Agence> updateAgence(@PathVariable(value = "code") String code, @RequestBody Agence agencedetails) throws ResourceNotFoundException {
        Agence agence = agenceRepository.findById(code).orElseThrow(() -> new ResourceNotFoundException("Agence not found with code: "+code));

        //agence.setCode(agencedetails.getCode());
        agence.setNom_agence(agencedetails.getNom_agence());
        agence.setAddress(agencedetails.getAddress());
        agence.setTelephone(agencedetails.getTelephone());
        final Agence updatedAgence = agenceRepository.save(agence);
        return ResponseEntity.ok(updatedAgence);
    }

    @DeleteMapping("/agence-list/{code}")
    public Map<String, Boolean> deleteAgence(@PathVariable(value = "code") String code) throws ResourceNotFoundException {
        Agence agence = agenceRepository.findById(code).orElseThrow(() -> new ResourceNotFoundException("Agence not found with code: "+code));

        agenceRepository.delete(agence);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
