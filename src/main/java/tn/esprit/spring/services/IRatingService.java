package tn.esprit.spring.services;

import tn.esprit.spring.entities.Rating;
import tn.esprit.spring.entities.Reclamation;

import java.util.List;

public interface IRatingService {

    public Rating ajouterRating(Rating rating, int recId);
    public List<Rating> getRatingByRecID(int recId);
    public void deleteRating(int id);

}
