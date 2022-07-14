package tn.esprit.spring.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Rating;
import tn.esprit.spring.entities.Reclamation;
import tn.esprit.spring.repository.RatingRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RatingService implements IRatingService {



    private static final Logger l = LogManager.getLogger(ResponseReclamationServiceImpl.class);

    @Autowired
    RatingRepository ratingRepoditory;

    @Autowired
    IReclamationService reclamationRepository;


    @Override
    public Rating ajouterRating(Rating rating, int recId) {

        try{

            Reclamation reclamation=reclamationRepository.getReclamationById(recId);
            rating.setReclamation(reclamation);
            rating.setDate(new Date());
            ratingRepoditory.save(rating);
        }
        catch (Exception e) {
            l.error("create Rating error.", e.getMessage());
        }

        return rating;
    }

    @Override
    public List<Rating> getRatingByRecID(int recId) {
        Reclamation reclamation=reclamationRepository.getReclamationById(recId);
        return  ratingRepoditory.findByReclamation(reclamation);
    }

    @Override
    public void deleteRating(int id) {

        try {
            Optional<Rating> opRec = ratingRepoditory.findById(id);
            if (opRec.isPresent()) {
                Rating rating1 = opRec.get();
                ratingRepoditory.delete(rating1);
            }
        } catch (Exception e) {
            l.error("delete Rating error.", e.getMessage());
        }
    }
}
