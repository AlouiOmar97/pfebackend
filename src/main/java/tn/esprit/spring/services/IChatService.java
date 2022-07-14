package tn.esprit.spring.services;

import tn.esprit.spring.entities.Chat;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.payload.request.Message;

import java.util.List;

public interface IChatService {

    public Chat messageToChat(Message message);
    public Chat ajouterChat(Chat chat);
    public List<Chat> getMessages(User sender, User receiver);
    public List<Chat> getChatroomMessages();
    public List<User> getUserContactList(User user);
    public Chat ajouterChatPrive(Chat chat);
    public List<Chat> getAllChats();
    public Chat getChatById(int chatId);
    public void deleteChat(int id);


}
