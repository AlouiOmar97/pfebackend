package tn.esprit.spring.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Reclamation;
import tn.esprit.spring.entities.Stat;
import tn.esprit.spring.repository.ReclamationRepository;

import java.util.ArrayList;
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

    public List<Stat> getStatByEtat(){
        List<Stat> statList=new ArrayList<>();

        List<Object[]> stat=reclamationRepository.getStatEtat();
        for(int j=0;j<stat.size();j++){
            Stat stat1=new Stat(stat.get(j)[0].toString(),stat.get(j)[1].toString());
            statList.add(stat1);
        }

        return statList;
    }

    public List<Stat> getStatByDepartement(){
        List<Stat> statList=new ArrayList<>();

        List<Object[]> stat=reclamationRepository.getStatDepartement();
        for(int j=0;j<stat.size();j++){
            Stat stat1=new Stat(stat.get(j)[0].toString(),stat.get(j)[1].toString());
            statList.add(stat1);
        }

        return statList;
    }

    public List<Stat> getStatByType(){
        List<Stat> statList=new ArrayList<>();

        List<Object[]> stat=reclamationRepository.getStatType();
        for(int j=0;j<stat.size();j++){
            Stat stat1=new Stat(stat.get(j)[0].toString(),stat.get(j)[1].toString());
            statList.add(stat1);
        }

        return statList;
    }

    public List<Stat> getStatRecToday(){
        List<Stat> statList=new ArrayList<>();

        List<Object[]> stat=reclamationRepository.getStatRecToday();
        for(int j=0;j<stat.size();j++){
            Stat stat1=new Stat(stat.get(j)[0].toString(),stat.get(j)[1].toString());
            statList.add(stat1);
        }

        return statList;
    }

    public List<Stat> getStatRecWeek(){
        List<Stat> statList=new ArrayList<>();

        List<Object[]> stat=reclamationRepository.getStatRecWeek();
        for(int j=0;j<stat.size();j++){
            Stat stat1=new Stat(stat.get(j)[0].toString(),stat.get(j)[1].toString());
            statList.add(stat1);
        }

        return statList;
    }

    public List<Stat> getStatRecPreviousWeek(){
        List<Stat> statList=new ArrayList<>();

        List<Object[]> stat=reclamationRepository.getStatRecPreviousWeek();
        for(int j=0;j<stat.size();j++){
            Stat stat1=new Stat(stat.get(j)[0].toString(),stat.get(j)[1].toString());
            statList.add(stat1);
        }

        return statList;
    }

    public List<Stat> getStatRecMonthGroupedByDate(){
        List<Stat> statList=new ArrayList<>();

        List<Object[]> stat=reclamationRepository.getStatRecMonthGroupedDate();
        for(int j=0;j<stat.size();j++){
            Stat stat1=new Stat(stat.get(j)[0].toString(),stat.get(j)[1].toString());
            statList.add(stat1);
        }

        return statList;
    }

    public List<Stat> getStatRecTreatedToday(){
        List<Stat> statList=new ArrayList<>();

        List<Object[]> stat=reclamationRepository.getStatRecTreatedToday();
        for(int j=0;j<stat.size();j++){
            Stat stat1=new Stat(stat.get(j)[0].toString(),stat.get(j)[1].toString());
            statList.add(stat1);
        }

        return statList;
    }

    public List<Stat> getStatRecTreatedWeek(){
        List<Stat> statList=new ArrayList<>();

        List<Object[]> stat=reclamationRepository.getStatRecTreatedWeek();
        for(int j=0;j<stat.size();j++){
            Stat stat1=new Stat(stat.get(j)[0].toString(),stat.get(j)[1].toString());
            statList.add(stat1);
        }

        return statList;
    }



    public List<Stat> getStatRecAvgResTime(){
        List<Stat> statList=new ArrayList<>();

        List<Object[]> stat=reclamationRepository.getStatAvgResTime();
        for(int j=0;j<stat.size();j++){
            Stat stat1=new Stat(stat.get(j)[0].toString(),stat.get(j)[1].toString());
            statList.add(stat1);
        }

        return statList;
    }

    public List<Stat> getStatRecTreatedByAgent(){
        List<Stat> statList=new ArrayList<>();

        List<Object[]> stat=reclamationRepository.getStatRecTreatedAgent();
        for(int j=0;j<stat.size();j++){
            Stat stat1=new Stat(stat.get(j)[0].toString(),stat.get(j)[1].toString());
            statList.add(stat1);
        }

        return statList;
    }


    public List<Stat> getStatRecTreatedByAgentToday(){
        List<Stat> statList=new ArrayList<>();

        List<Object[]> stat=reclamationRepository.getStatRecTreatedAgentToday();
        for(int j=0;j<stat.size();j++){
            Stat stat1=new Stat(stat.get(j)[0].toString(),stat.get(j)[1].toString());
            statList.add(stat1);
        }

        return statList;
    }

    public List<Stat> getStatRecTreatedByAgentWeek(){
        List<Stat> statList=new ArrayList<>();

        List<Object[]> stat=reclamationRepository.getStatRecTreatedAgentWeek();
        for(int j=0;j<stat.size();j++){
            Stat stat1=new Stat(stat.get(j)[0].toString(),stat.get(j)[1].toString());
            statList.add(stat1);
        }

        return statList;
    }

    public List<Stat> getStatRecTreatedBestAgent(){
        List<Stat> statList=new ArrayList<>();

        List<Object[]> stat=reclamationRepository.getStatRecTreatedBestAgent();
        for(int j=0;j<stat.size();j++){
            Stat stat1=new Stat(stat.get(j)[0].toString(),stat.get(j)[1].toString());
            statList.add(stat1);
        }

        return statList;
    }
}