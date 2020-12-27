package dev.project.servicesweb.controllers;

import dev.project.servicesweb.exceptions.ResourceNotFoundException;
import dev.project.servicesweb.models.Client;
import dev.project.servicesweb.models.Compte;
import dev.project.servicesweb.repositories.AgenceRepository;
import dev.project.servicesweb.repositories.ClientRepository;
import dev.project.servicesweb.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/agence")
public class CompteController {

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private AgenceRepository agenceRepository;

    private ClientRepository clientRepository;
    private ClientController clientController;
    
    @GetMapping("/agence-list/{code}/compte-list")
    public List<Compte> getAllComptes(@PathVariable(value = "code")String agence_code) {return compteRepository.findByAgenceCode(agence_code);}

    @GetMapping("/agence-list/{code}/compte-list/{id}")
    public ResponseEntity<Compte> getCompteById(@PathVariable(value = "code") String agence_code, @PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Compte compte = compteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(" not found for this id: " + id));

        return ResponseEntity.ok().body(compte);
    }

    @PostMapping("/agence-list/{code}/compte-list")
    public Compte createCompte(@PathVariable(value = "code") String agence_code, @RequestBody Compte compte)  throws ResourceNotFoundException{
        return agenceRepository.findById(agence_code).map(agence -> { compte.setAgence(agence);
        return compteRepository.save(compte);
        }).orElseThrow(() -> new ResourceNotFoundException("Agence Not found"));
    }

    @PutMapping("/agence-list/{code}/compte-list/{id}")
    public Compte updateCompte(@PathVariable(value = "code") String agence_code, @PathVariable(value = "id") Long id, @RequestBody Compte compteDetails) throws ResourceNotFoundException {
        if(!agenceRepository.existsById(agence_code)) {
            throw new ResourceNotFoundException("Agence Not Found!");
        }

        return  compteRepository.findById(id).map(compte -> {
            compte.setDecouvert(compteDetails.getDecouvert());
            compte.setSolde(compteDetails.getSolde());
            compte.setClient(compteDetails.getClient());

            return compteRepository.save(compte);
        }).orElseThrow(() -> new org.apache.velocity.exception.ResourceNotFoundException("Compte id not found"));
        /*Compte compte = compteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found for this id: "+id));

        compte.setSolde(compteDetails.getSolde());
        compte.setDecouvert(compteDetails.getDecouvert());
        compte.setAgence(compteDetails.getAgence());
        compte.setClient(compteDetails.getClient());

        final Compte updatedCompte = compteRepository.save(compte);

        return ResponseEntity.ok(updatedCompte);*/
    }

    @DeleteMapping("/agence-list/{code}/compte-list/{id}")
    public ResponseEntity< ? > deleteCompte(@PathVariable(value="code") String agence_code, @PathVariable(value="id") Long id) throws ResourceNotFoundException {
        return compteRepository.findByIdAndAgenceCode(id, agence_code).map(compte -> {
            compteRepository.delete(compte);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Compte with id "+id+" and agence"+agence_code+" not founf"));
    }
    @DeleteMapping("/compte-list/{id}")
    public Map<String, Boolean> deleteAccompte(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Compte compte = compteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found for this id: "+id));

        compteRepository.delete(compte);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return  response;
    }

}
