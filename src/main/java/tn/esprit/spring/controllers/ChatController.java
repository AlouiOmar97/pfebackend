package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Chat;
import tn.esprit.spring.entities.ResponseReclamation;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.payload.request.Message;
import tn.esprit.spring.repository.ChatRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.services.IChatService;
import tn.esprit.spring.services.IResponseReclamationService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
//@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    IResponseReclamationService iResponseReclamationService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    IChatService iChatService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receiveMessage(@Payload Message message){
        System.out.println("recieve mesg");
        System.out.println(message.toString());
        iChatService.ajouterChat(iChatService.messageToChat(message));
        return message;
    }

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message){
        System.out.println("private msg");
        System.out.println(message.toString());
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
        iChatService.ajouterChat(iChatService.messageToChat(message));
        System.out.println(message.toString());
        return message;
    }

    @GetMapping("api/chat/all")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public List<ResponseReclamation> getAllResponseReclamation() {


        return iResponseReclamationService.getAllResponseReclamations();
    }

    @GetMapping(value = "api/chat/{sender}/{receiver}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public List<Chat> getListMessage(@PathVariable("sender") String sender,@PathVariable("receiver") String receiver) {

        User userSender=new User();
        User userReceiver=new User();
        Optional<User> opSenderUser = userRepository.findByUsername(sender);
        Optional<User> opReceiverUser = userRepository.findByUsername(receiver);
        if(opSenderUser.isPresent() && opReceiverUser.isPresent()){
             userSender=opSenderUser.get();
             userReceiver=opReceiverUser.get();

        }
        return iChatService.getMessages(userReceiver,userSender);
    }

    @GetMapping(value = "api/chat/chatroom")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public List<Chat> getChatRoomListMessage() {


        return iChatService.getChatroomMessages();
    }

    @GetMapping(value = "api/chat/contact/{user}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public List<User> getUserContact(@PathVariable("user") String user) {

        User userC=new User();
        Optional<User> opCUser = userRepository.findByUsername(user);
        if(opCUser.isPresent()){
            userC=opCUser.get();

        }
        return iChatService.getUserContactList(userC);
    }

}
