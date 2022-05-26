package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Chat;
import tn.esprit.spring.entities.User;

import java.util.List;


@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {

    @Query("select c from Chat c where (sender=:sender and receiver=:receiver) or (receiver=:sender and sender=:receiver) order by date asc")
    public List<Chat> getListMessage(@Param("sender") User sender, @Param("receiver") User receiver);

    @Query(value="SELECT * FROM `users` WHERE id in (SELECT DISTINCT `sender_id` as contact FROM `chat` WHERE `receiver_id`=?1 UNION SELECT DISTINCT `receiver_id` as contact FROM `chat` WHERE `sender_id`=?1)",
            nativeQuery = true)
    public List<Object[]> getUserContactList(@Param("user") Long user);

    public List<Chat> findByReceiver(User receiver);



}
