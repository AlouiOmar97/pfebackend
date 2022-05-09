package tn.esprit.spring.services;

import tn.esprit.spring.entities.ResponseReclamation;

import java.util.List;

public interface IResponseReclamationService {

    public ResponseReclamation ajouterResponseReclamation(ResponseReclamation responseReclamation,int recId);
    public List<ResponseReclamation> getAllResponseReclamations();
    public ResponseReclamation getResponseReclamationById(int responseReclamationId);


}
