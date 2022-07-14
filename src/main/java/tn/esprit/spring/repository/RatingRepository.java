package tn.esprit.spring.repository;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.Rating;
import tn.esprit.spring.entities.Reclamation;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    Optional<Rating> findById(int id);
    List<Rating> findByReclamation(Reclamation reclamation);
}