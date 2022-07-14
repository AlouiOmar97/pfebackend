package tn.esprit.spring.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Chat;
import tn.esprit.spring.entities.EStatus;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.payload.request.Message;
import tn.esprit.spring.repository.ChatRepository;
import tn.esprit.spring.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ChatServiceImpl implements IChatService {

    private static final Logger l = LogManager.getLogger(ChatServiceImpl.class);

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @Autowired
    ChatRepository chatRepository;
    @Autowired
    UserRepository userRepository;

    public Chat messageToChat(Message message) {
        Chat chat=new Chat();
        try {

            chat.setMessage(message.getMessage());
            chat.setDate(new Date());
            Optional<User> opSenderUser = userRepository.findByUsername(message.getSenderName());
            Optional<User> opReceiverUser = userRepository.findByUsername(message.getReceiverName());
            if(opSenderUser.isPresent() && opReceiverUser.isPresent()){
                User userSender=opSenderUser.get();
                User userReceiver=opReceiverUser.get();
                chat.setSender(userSender);
                chat.setReceiver(userReceiver);
            }
            chat.setStatus(EStatus.MESSAGE);
            chatRepository.save(chat);
            //simpMessagingTemplate.convertAndSend("/topic/messages/" + chat.getReceiver().getUsername(), chat);
        } catch (Exception e) {
            l.error("create chat error.", e.getMessage());
        }

        return chat;
    }

    public Chat ajouterChat(Chat chat) {
        try {
            chat.setDate(new Date());
            chatRepository.save(chat);
            //simpMessagingTemplate.convertAndSend("/topic/messages/" + chat.getReceiver().getUsername(), chat);
        } catch (Exception e) {
            l.error("create chat error.", e.getMessage());
        }

        return chat;
    }

    public Chat ajouterChatPrive(Chat chat) {
        try {
            chat.setDate(new Date());
            chatRepository.save(chat);
            //simpMessagingTemplate.convertAndSendToUser(chat.getReceiver().getUsername(),"/private",message);
        } catch (Exception e) {
            l.error("create chat error.", e.getMessage());
        }

        return chat;
    }

    public List<Chat> getAllChats() {
        return (List<Chat>) chatRepository.findAll();
    }

    public List<Chat> getMessages(User sender,User receiver) {
        return (List<Chat>) chatRepository.getListMessage(receiver,sender);
    }

    public List<Chat> getChatroomMessages() {


            User chatRoom=new User();
            Optional<User> opReceiverUser = userRepository.findByUsername("CHATROOM");
            if (opReceiverUser.isPresent()) {
                chatRoom = opReceiverUser.get();

            }
            return (List<Chat>) chatRepository.findByReceiver(chatRoom);


    }

    public List<User> getUserContactList(User user){
       // chatRepository.findAllByIdIn();
        List<User> userList=new ArrayList<User>();
        //userList.add(user);
//        System.out.println("#########");
//        System.out.println( chatRepository.getUserContactList(user.getId()));
        List<Object[]> us=   chatRepository.getUserContactList(user.getId());
//        System.out.println("#####get#####");
//        System.out.println(( us.size()));
        for(int j=0;j<us.size();j++){
//            System.out.println("user "+j+" :");
            User user1=new User(new Long(us.get(j)[0].toString()),us.get(j)[1].toString(),us.get(j)[2].toString(),us.get(j)[3].toString(),us.get(j)[4].toString(),us.get(j)[5].toString());
            userList.add(user1);
//            User user1=new User();
//            user1.setId(new Long(us.get(j)[0].toString()));
//            user1.setEmail(us.get(j)[1].toString());
//            System.out.println("######## debut user to sting ####################");
//            System.out.println(user1.toString());
//            System.out.println("########## fin user to string ###################");
//        for (int i=0;i<us.get(j).length;i++){
//            System.out.print(us.get(j)[i]+" ");
//        }
//            System.out.println(" ");
        }
//        for(Object o: us){
//            userList.add((User)o);
//        }
        //System.out.println(userList);
//        chatRepository.getUserContactList(user.getId());
       return  userList;
    }

    public Chat getChatById(int chatId) { return chatRepository.findById(chatId).get(); }

    public void deleteChat(int id) {
        try {
            Optional<Chat> opChat=chatRepository.findById(id);
            if (opChat.isPresent()) {
                Chat chat1 = opChat.get();
                chatRepository.delete(chat1);
            }
        } catch (Exception e) {
            l.error("delete chat error.", e.getMessage());
        }

    }
}