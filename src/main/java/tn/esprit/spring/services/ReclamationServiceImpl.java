package tn.esprit.spring.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Reclamation;
import tn.esprit.spring.repository.ReclamationRepository;

import java.util.Date;
import java.util.List;


@Service
public class ReclamationServiceImpl implements IReclamationService {

    private static final Logger l = LogManager.getLogger(ReclamationServiceImpl.class);

    @Autowired
    ReclamationRepository reclamationRepository;

    public Reclamation ajouterReclamation(Reclamation reclamation) {
        try {
            reclamation.setDate(new Date());
            reclamation.setEtat("non traitée");
            reclamationRepository.save(reclamation);
        } catch (Exception e) {
            l.error("create reclamation error.", e.getMessage());
        }

        return reclamation;
    }
    public List<Reclamation> getAllReclamations() {
        return (List<Reclamation>) reclamationRepository.findAll();
    }
    public Reclamation getReclamationById(int reclamationId) { return reclamationRepository.findById(reclamationId).get(); }

}