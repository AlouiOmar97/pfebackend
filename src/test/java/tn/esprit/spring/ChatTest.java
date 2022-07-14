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
import tn.esprit.spring.entities.Chat;
import tn.esprit.spring.entities.EStatus;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.services.IChatService;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ChatTest {
    private static final Logger l = LogManager.getLogger(LoggingAspect.class);
    @Autowired
    IChatService iChatService;
    private Chat chatTest;


    @Before()
    public void createChat() {
        System.out.println("before insert");
        User user = new User(new Long("1"), "omaraloui@esprit.tn", "test", "Aloui12", "Aloui", "Omar");
        Chat chat=new Chat("Message",new Date(),user,user, EStatus.MESSAGE);
        l.info("testing adding chat");
        chatTest = iChatService.ajouterChat(chat);
        //test
    }


    @Test
    public void testAjouterChat() {

        System.out.println("ajout chat");
        assertNotNull(chatTest);
    }

    @Test
    public void testGetChat() {

        System.out.println("get chat");
        Chat chatMod;
        chatMod=iChatService.getChatById(chatTest.getId());
        assertEquals(chatMod.getMessage(),("Message"));
    }


    @After()
    public  void deleteChat(){
        System.out.println("Delete after");
        iChatService.deleteChat(chatTest.getId());
    }

}
