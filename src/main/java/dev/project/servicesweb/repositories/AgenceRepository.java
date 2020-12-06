package dev.project.servicesweb.repositories;

import dev.project.servicesweb.models.Agence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenceRepository extends JpaRepository<Agence, String> {
}
