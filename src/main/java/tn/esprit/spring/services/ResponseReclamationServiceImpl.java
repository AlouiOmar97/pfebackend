package tn.esprit.spring.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Reclamation;
import tn.esprit.spring.entities.ResponseReclamation;
import tn.esprit.spring.repository.ReclamationRepository;
import tn.esprit.spring.repository.ResponseReclamationRepository;

import java.util.Date;
import java.util.List;


@Service
public class ResponseReclamationServiceImpl implements IResponseReclamationService {

    private static final Logger l = LogManager.getLogger(ResponseReclamationServiceImpl.class);

    @Autowired
    ResponseReclamationRepository responseReclamationRepository;

    @Autowired
    IReclamationService reclamationRepository;

    public ResponseReclamation ajouterResponseReclamation(ResponseReclamation responseReclamation,int recId) {
        try {
            Reclamation reclamation=reclamationRepository.getReclamationById(recId);
            reclamation.setEtat("en cours");
            responseReclamation.setReclamation(reclamation);
            responseReclamation.setDate(new Date());
            responseReclamationRepository.save(responseReclamation);
        } catch (Exception e) {
            l.error("create responseReclamation error.", e.getMessage());
        }

        return responseReclamation;
    }
    public List<ResponseReclamation> getAllResponseReclamations() {
        return (List<ResponseReclamation>) responseReclamationRepository.findAll();
    }
    public ResponseReclamation getResponseReclamationById(int responseReclamationId) { return responseReclamationRepository.findById(responseReclamationId).get(); }

}