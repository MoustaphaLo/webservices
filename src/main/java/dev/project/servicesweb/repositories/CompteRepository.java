package dev.project.servicesweb.repositories;

import dev.project.servicesweb.models.Agence;
import dev.project.servicesweb.models.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {
    List<Compte> findByAgenceCode(String agence_code);
    Optional<Compte> findByIdAndAgenceCode(Long id, String agence_code);

}
