package tn.esprit.spring.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Stat;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements IUserService {

    private static final Logger l = LogManager.getLogger(UserServiceImpl.class);


    @Autowired
    UserRepository userRepository;

    public User ajouterUser(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            l.error("create user error.", e.getMessage());
        }

        return user;
    }
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }
    public User getUserById(int userId) { return userRepository.findById(new Long(userId)).get(); }

    public void deleteUser(int id) {
        try {
            Optional<User> opUsr=userRepository.findById(new Long(id));
            if (opUsr.isPresent()) {
                User user1 = opUsr.get();
                userRepository.delete(user1);
            }
        } catch (Exception e) {
            l.error("delete user error.", e.getMessage());
        }

    }


}