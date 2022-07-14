package tn.esprit.spring;

import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.config.LoggingAspect;
import tn.esprit.spring.entities.Reclamation;
import tn.esprit.spring.entities.Stat;
import tn.esprit.spring.entities.User;
import static org.junit.Assert.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.apache.logging.log4j.Logger;
import tn.esprit.spring.services.IReclamationService;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StatTest {
    private static final Logger l = LogManager.getLogger(LoggingAspect.class);
    @Autowired
    IReclamationService iReclamationService;
    private Reclamation reclamationTest;
    private List<Stat> statWeekTest;
    private List<Stat> statTodayTest;
    String statWeekBefore;
    String statWeekAfter;


    @Before()
    public void createReclamation() {
        System.out.println("before insert");
        User user = new User(new Long("1"), "omaraloui@esprit.tn", "test", "Aloui12", "Aloui", "Omar");
        Reclamation reclamation = new Reclamation("motiff", "etat", "type", "departement", "message", new Date(), user);
        l.info("testing adding stat week reclamation");
        statWeekTest = iReclamationService.getStatRecWeek();
        statWeekBefore=statWeekTest.get(0).getValeur();
        l.info("stat reclamation week before :"+statWeekTest);
        l.info("testing adding stat today reclamation");
        statWeekTest = iReclamationService.getStatRecToday();
        l.info("stat reclamation today before :"+statWeekTest);
        l.info("testing adding reclamation");
        reclamationTest = iReclamationService.addReclamation(reclamation);
        statWeekTest = iReclamationService.getStatRecWeek();
        statWeekAfter=statWeekTest.get(0).getValeur();
        l.info("stat reclamation week after :"+statWeekTest);
        //test
    }


    @Test
    public void testAjouterReclamation() {

        System.out.println("ajout reclamation");
        assertNotNull(reclamationTest);
    }

    @Test
    public void testStatWeekReclamation() {

        System.out.println("stat week reclamation");

        String sttWeek=statWeekBefore;
        int sttsWeek=Integer.parseInt(sttWeek)+1;
        sttWeek=String.valueOf(sttsWeek);
        assertEquals(sttWeek,statWeekAfter);
    }


    @After()
    public  void deleteReclamation(){
        System.out.println("Delete after");
        iReclamationService.deleteReclamation(reclamationTest.getId());
    }

}
