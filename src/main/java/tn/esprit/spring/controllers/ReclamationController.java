package tn.esprit.spring.controllers;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Reclamation;
import tn.esprit.spring.entities.Stat;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.security.jwt.AuthTokenFilter;
import tn.esprit.spring.security.jwt.JwtUtils;
import tn.esprit.spring.services.IReclamationService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reclamation")
public class ReclamationController {

    @Autowired
    IReclamationService iReclamationService;
    @Autowired
    UserRepository userRepository;



//    @GetMapping("/all")
//    public String allAccess() {
//        return "Public Content.";
//    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public List<Reclamation> getAllReclamation() {


        return iReclamationService.getAllReclamations();
    }

    @GetMapping(value = "/{idreclamation}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public Reclamation getReclamationById(@PathVariable("idreclamation") int reclamationId) {

        return iReclamationService.getReclamationById(reclamationId);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public Reclamation ajouterReclamation(HttpServletRequest request, @RequestBody Reclamation reclamation) {
//        public Reclamation ajouterReclamation(HttpServletRequest request, @RequestBody Reclamation reclamation) {
//        String tok="";
//        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
//            tok= token.substring(7, token.length());
//        }
//        System.out.println("token"+tok);
//        System.out.println("#####username####: "+ Jwts.parser().setSigningKey("bezKoderSecretKey").parseClaimsJws(tok).getBody().getSubject());
//
//        System.out.println("principal");
//        System.out.println(principal.getName());
//        System.out.println(principal);
        Principal principal = request.getUserPrincipal();
        Optional<User> opUser = userRepository.findByUsername(principal.getName());
        if(opUser.isPresent()){
            User user=opUser.get();
        reclamation.setUser(user);
        }
        iReclamationService.ajouterReclamation(reclamation);
        return reclamation;
    }

    @GetMapping(value = "/stat/etat")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public List<Stat> getStatEtat() {

        System.out.println("stat etat");
        return iReclamationService.getStatByEtat();
    }

    @GetMapping(value = "/stat/departement")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public List<Stat> getStatDepartement() {

        System.out.println("stat departemet");
        return iReclamationService.getStatByDepartement();
    }

    @GetMapping(value = "/stat/type")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public List<Stat> getStatType() {

        System.out.println("stat type");
        return iReclamationService.getStatByType();
    }

    @GetMapping(value = "/stat/rec/today")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public List<Stat> getStatRecToday() {

        System.out.println("stat rec today");
        return iReclamationService.getStatRecToday();
    }

    @GetMapping(value = "/stat/rec/week")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public List<Stat> getStatRecWeek() {

        System.out.println("stat rec week");
        return iReclamationService.getStatRecWeek();
    }

    @GetMapping(value = "/stat/rec/prevweek")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public List<Stat> getStatRecPreviousWeek() {

        System.out.println("stat rec previous week");
        return iReclamationService.getStatRecPreviousWeek();
    }

    @GetMapping(value = "/stat/rec/month")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public List<Stat> getStatRecMonth() {

        System.out.println("stat rec month");
        return iReclamationService.getStatRecMonthGroupedByDate();
    }



    @GetMapping(value = "/stat/rec/treated/today")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public List<Stat> getStatRecTreatedToday() {

        System.out.println("stat rec treated today");
        return iReclamationService.getStatRecTreatedToday();
    }

    @GetMapping(value = "/stat/rec/treated/week")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public List<Stat> getStatRecTreatedWeek() {

        System.out.println("stat rec treated week");
        return iReclamationService.getStatRecTreatedWeek();
    }

    @GetMapping(value = "/stat/rec/avgres")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public List<Stat> getStatAvgResTime() {

        System.out.println("stat rec avg response time");
        return iReclamationService.getStatRecAvgResTime();
    }

    @GetMapping(value = "/stat/rec/treated/agent")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public List<Stat> getStatRecTreatedByAgent() {

        System.out.println("stat rec treated by agent");
        return iReclamationService.getStatRecTreatedByAgent();
    }

    @GetMapping(value = "/stat/rec/treated/agent/today")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public List<Stat> getStatRecTreatedByAgentToday() {

        System.out.println("stat rec treated by agent today");
        return iReclamationService.getStatRecTreatedByAgentToday();
    }

    @GetMapping(value = "/stat/rec/treated/agent/week")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public List<Stat> getStatRecTreatedByAgentWeek() {

        System.out.println("stat rec treated by agent week");
        return iReclamationService.getStatRecTreatedByAgentWeek();
    }

    @GetMapping(value = "/stat/rec/treated/agent/best")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public List<Stat> getStatRecTreatedBestAgent() {

        System.out.println("stat rec treated best agent");
        return iReclamationService.getStatRecTreatedBestAgent();
    }
}
