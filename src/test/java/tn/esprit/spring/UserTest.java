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
import tn.esprit.spring.entities.User;
import tn.esprit.spring.services.IUserService;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    private static final Logger l = LogManager.getLogger(LoggingAspect.class);
    @Autowired
    IUserService iUserService;
    private User userTest;


    @Before()
    public void createUser() {
        System.out.println("before insert");
        User user = new User(new Long("1"), "omaraloui@esprit.tn", "test", "Aloui12", "Aloui", "Omar");
        l.info("testing adding user");
        userTest = iUserService.ajouterUser(user);
        //test
    }


    @Test
    public void testAjouterUser() {

        System.out.println("ajout user");
        assertNotNull(userTest);
    }

    @Test
    public void testModifierUser() {

        System.out.println("modifier user");
        userTest.setPrenom("test modif");
        User recMod;
        recMod=iUserService.updateUser(userTest,(userTest.getId()).intValue());
        assertEquals(recMod.getPrenom(),("test modif"));
    }


    @After()
    public  void deleteUser(){
        System.out.println("Delete after");
        iUserService.deleteUser((userTest.getId()).intValue());
    }

}
