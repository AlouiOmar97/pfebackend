package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.ResponseReclamation;

import java.util.Optional;

@Repository
public interface ResponseReclamationRepository extends JpaRepository<ResponseReclamation, Integer> {
    Optional<ResponseReclamation> findById(int id);


}
