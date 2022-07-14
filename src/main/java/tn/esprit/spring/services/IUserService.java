package tn.esprit.spring.services;

import tn.esprit.spring.entities.Chat;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.payload.request.Message;

import java.util.List;

public interface IUserService {

    public User ajouterUser(User user);
    public List<User> getAllUsers();
    public User getUserById(int userId);
    public User updateUser(User user,int id);
    public void deleteUser(int id);


}
