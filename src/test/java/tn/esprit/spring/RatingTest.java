package tn.esprit.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.config.LoggingAspect;
import tn.esprit.spring.entities.Rating;
import tn.esprit.spring.entities.Reclamation;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.services.IRatingService;
import tn.esprit.spring.services.IReclamationService;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingTest {
    private static final Logger l = LogManager.getLogger(LoggingAspect.class);
    @Autowired
    IRatingService iRatingService;
    private Rating ratingTest;
    @Autowired
    IReclamationService iReclamationService;
    private Reclamation reclamationTest;

    @Before()
    public void createRating() {
        System.out.println("before insert");
        User user = new User(new Long("1"), "omaraloui@esprit.tn", "test", "Aloui12", "Aloui", "Omar");
        Reclamation reclamation = new Reclamation("motif", "etat", "type", "departement", "message", new Date(), user);
        l.info("testing adding reclamation");
        reclamationTest = iReclamationService.addReclamation(reclamation);
        Rating rating=new Rating("Message","1",new Date(),user);
        l.info("testing adding rating");
        ratingTest = iRatingService.ajouterRating(rating,reclamationTest.getId());
        //test
    }


    @Test
    public void testAjouterRating() {

        System.out.println("ajout rating");
        assertNotNull(ratingTest);
    }

    @Test
    public void testGetRating() {

        System.out.println("get rating");
        List<Rating> ratingMod;
        ratingMod=iRatingService.getRatingByRecID(reclamationTest.getId());
        assertEquals(ratingMod.get(0).getMessage(),("Message"));
    }


    @After()
    public  void deleteRating(){
        System.out.println("Delete after");
        iRatingService.deleteRating(ratingTest.getId());
        iReclamationService.deleteReclamation(reclamationTest.getId());

    }

}
