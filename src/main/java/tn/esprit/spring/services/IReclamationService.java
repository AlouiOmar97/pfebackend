package tn.esprit.spring.services;

import tn.esprit.spring.entities.Reclamation;

import java.util.List;

public interface IReclamationService {

    public Reclamation ajouterReclamation(Reclamation reclamation);
    public List<Reclamation> getAllReclamations();
    public Reclamation getReclamationById(int reclamationId);


}
