package tn.esprit.spring.services;

import tn.esprit.spring.entities.Reclamation;
import tn.esprit.spring.entities.Stat;

import java.util.List;

public interface IReclamationService {

    public Reclamation ajouterReclamation(Reclamation reclamation);
    public List<Reclamation> getAllReclamations();
    public Reclamation getReclamationById(int reclamationId);
    public List<Stat> getStatByEtat();
    public List<Stat> getStatByDepartement();
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
