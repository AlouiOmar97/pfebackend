package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Chat;
import tn.esprit.spring.entities.Reclamation;
import tn.esprit.spring.entities.Stat;
import tn.esprit.spring.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Integer> {
    Optional<Reclamation> findByDepartement(String department);

    Boolean existsByDepartement(String department);


    @Query("SELECT r.etat as critere,COUNT(r) as valeur FROM Reclamation r GROUP BY r.etat")
    public List<Object[]> getStatEtat();

    @Query("SELECT r.departement as critere,COUNT(r) as valeur FROM Reclamation r GROUP BY r.departement")
    public List<Object[]> getStatDepartement();

    @Query("SELECT r.type as critere,COUNT(r) as valeur FROM Reclamation r GROUP BY r.type")
    public List<Object[]> getStatType();

    @Query(value="SELECT 'critere',COUNT(*) as valeur FROM `reclamation` as r where DATE(r.date)=DATE(now())",nativeQuery = true)
    public List<Object[]> getStatRecToday();

    @Query(value="SELECT 'critere',COUNT(*) as valeur FROM `reclamation` as r where DATE(r.date)>DATE(now() - INTERVAL 7 day)",nativeQuery = true)
    public List<Object[]> getStatRecWeek();

    @Query(value="SELECT 'critere',COUNT(*) as valeur FROM `reclamation` as r where DATE(r.date) BETWEEN DATE(now() - INTERVAL 14 day) and DATE(now() - INTERVAL 7 day)",nativeQuery = true)
    public List<Object[]> getStatRecPreviousWeek();

    @Query(value="SELECT DATE(r.date) as critere,COUNT(*) as valeur FROM `reclamation` as r where DATE(r.date)>DATE(now() - INTERVAL 30 day) GROUP BY DATE(r.date)",nativeQuery = true)
    public List<Object[]> getStatRecMonthGroupedDate();

    @Query(value="SELECT 'critere',COUNT(*) FROM `response_reclamation` rr join `reclamation` r on rr.`reclamation_id`=r.id WHERE r.`etat`= \"traitée\" and DATE(rr.date)=DATE(now())",nativeQuery = true)
    public List<Object[]> getStatRecTreatedToday();

    @Query(value="SELECT 'critere',COUNT(*) FROM `response_reclamation` rr join `reclamation` r on rr.`reclamation_id`=r.id WHERE r.`etat`= \"traitée\" and DATE(rr.date)>DATE(now() - INTERVAL 7 day)",nativeQuery = true)
    public List<Object[]> getStatRecTreatedWeek();

    @Query(value="SELECT 'critere',AVG(avgres) FROM (SELECT dateres,r.`date` as daterec,TIMESTAMPDIFF(MINUTE,r.`date`,dateres ) as avgres FROM `reclamation` r JOIN ( SELECT rs.*,rs.`date` as dateres from `response_reclamation` rs JOIN `reclamation` re ON re.`id`=rs.`reclamation_id` where rs.`user_id`<>re.`user_id` and rs.`date`=(SELECT MIN(rrr.`date`) FROM `response_reclamation` rrr WHERE rrr.`reclamation_id`=rs.`reclamation_id` AND rrr.`user_id`<>re.`user_id`) ) rr on r.`id`=rr.`reclamation_id` GROUP BY r.`id` ) as tab",nativeQuery = true)
    public List<Object[]> getStatAvgResTime();

    @Query(value="SELECT tab.userr, COUNT(tab.userr) FROM (SELECT rr.`reclamation_id`, rr.`user_id` as userr FROM `response_reclamation` rr join `reclamation` r on rr.`reclamation_id`=r.id join `user_roles` ur on rr.`user_id`=ur.`user_id` WHERE r.`etat`= \"traitée\" and ur.`role_id`=3 GROUP BY rr.`user_id`,rr.`reclamation_id`) as tab GROUP BY tab.userr",nativeQuery = true)
    public List<Object[]> getStatRecTreatedAgent();

    @Query(value="SELECT tab.userr, COUNT(tab.userr) FROM (SELECT rr.`reclamation_id`, rr.`user_id` as userr FROM `response_reclamation` rr join `reclamation` r on rr.`reclamation_id`=r.id join `user_roles` ur on rr.`user_id`=ur.`user_id` WHERE r.`etat`= \"traitée\" and ur.`role_id`=3 and DATE(rr.date)=DATE(now()) GROUP BY rr.`user_id`,rr.`reclamation_id`) as tab GROUP BY tab.userr",nativeQuery = true)
    public List<Object[]> getStatRecTreatedAgentToday();

    @Query(value="SELECT tab.userr, COUNT(tab.userr) FROM (SELECT rr.`reclamation_id`, rr.`user_id` as userr FROM `response_reclamation` rr join `reclamation` r on rr.`reclamation_id`=r.id join `user_roles` ur on rr.`user_id`=ur.`user_id` WHERE r.`etat`= \"traitée\" and ur.`role_id`=3 and DATE(rr.date)>DATE(now() - INTERVAL 7 day) GROUP BY rr.`user_id`,rr.`reclamation_id`) as tab GROUP BY tab.userr",nativeQuery = true)
    public List<Object[]> getStatRecTreatedAgentWeek();

    @Query(value="SELECT tab.userr, COUNT(tab.userr) as valeur FROM (SELECT rr.`reclamation_id`, rr.`user_id` as userr FROM `response_reclamation` rr join `reclamation` r on rr.`reclamation_id`=r.id join `user_roles` ur on rr.`user_id`=ur.`user_id` WHERE r.`etat`= \"traitée\" and ur.`role_id`=3 GROUP BY rr.`user_id`,rr.`reclamation_id`) as tab GROUP BY tab.userr ORDER BY valeur DESC LIMIT 1",nativeQuery = true)
    public List<Object[]> getStatRecTreatedBestAgent();


}
