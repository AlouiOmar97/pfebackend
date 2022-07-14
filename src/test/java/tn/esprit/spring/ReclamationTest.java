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
import tn.esprit.spring.entities.User;
import static org.junit.Assert.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.apache.logging.log4j.Logger;
import tn.esprit.spring.services.IReclamationService;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ReclamationTest {
    private static final Logger l = LogManager.getLogger(LoggingAspect.class);
    @Autowired
    IReclamationService iReclamationService;
    private Reclamation reclamationTest;


    @Before()
    public void createReclamation() {
        System.out.println("before insert");
        User user = new User(new Long("1"), "omaraloui@esprit.tn", "test", "Aloui12", "Aloui", "Omar");
        Reclamation reclamation = new Reclamation("motif", "etat", "type", "departement", "message", new Date(), user);
        l.info("testing adding reclamation");
        reclamationTest = iReclamationService.addReclamation(reclamation);
        //test
    }


    @Test
    public void testAjouterReclamation() {

        System.out.println("ajout reclamation");
        assertNotNull(reclamationTest);
    }

    @Test
    public void testModifierReclamation() {

        System.out.println("modifier reclamation");
        reclamationTest.setType("test modif");
        Reclamation recMod;
        recMod=iReclamationService.updateReclamation(reclamationTest,reclamationTest.getId());
        assertEquals(recMod.getType(),("test modif"));
    }


    @After()
    public  void deleteReclamation(){
        System.out.println("Delete after");
        iReclamationService.deleteReclamation(reclamationTest.getId());
    }

}
