package tn.esprit.spring.services;

import tn.esprit.spring.entities.Reclamation;
import tn.esprit.spring.entities.Stat;
import tn.esprit.spring.entities.User;

import java.util.List;

public interface IReclamationService {

    public Reclamation addReclamation(Reclamation reclamation);
    public Reclamation updateReclamation(Reclamation reclamation,int id);
    public Reclamation updateReclamationEtat(Reclamation reclamation,int id);
    public void deleteReclamation(int id);
    public List<Reclamation> getAllReclamations();
    public List<Reclamation> getMyReclamations(User user);
    public Reclamation getReclamationById(int reclamationId);
    public List<Stat> getStatByEtat();
    public List<Stat> getStatByEtatWeek();
    public List<Stat> getStatByDepartement();
    public List<Stat> getStatByDepartementWeek();
    public List<Stat> getStatByType();
    public List<Stat> getStatRecToday();
    public List<Stat> getStatRecWeek();
    public List<Stat> getStatRecPreviousWeek();
    public List<Stat> getStatRecMonthGroupedByDate();
    public List<Stat> getStatRecTreatedToday();
    public List<Stat> getStatRecTreatedWeek();
    public List<Stat> getStatRecAvgResTime();
    public List<Stat> getStatRecTreatedByAgent();
    public List<Stat> getStatRecTreatedByAgentToday();
    public List<Stat> getStatRecTreatedByAgentWeek();
    public List<Stat> getStatRecTreatedBestAgent();



}
