package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Reclamation;

import java.util.Optional;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Integer> {
    Optional<Reclamation> findByDepartement(String department);

    Boolean existsByDepartement(String department);

}
