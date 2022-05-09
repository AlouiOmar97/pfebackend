package tn.esprit.spring.controllers;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Reclamation;
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
}
