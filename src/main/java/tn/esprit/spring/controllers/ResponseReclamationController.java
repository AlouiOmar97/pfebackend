package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.ResponseReclamation;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.services.IResponseReclamationService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/response")
public class ResponseReclamationController {

    @Autowired
    IResponseReclamationService iResponseReclamationService;
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
    public List<ResponseReclamation> getAllResponseReclamation() {


        return iResponseReclamationService.getAllResponseReclamations();
    }

    @GetMapping(value = "/{idresponse}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public ResponseReclamation getResponseReclamationById(@PathVariable("idresponse") int responseId) {

        return iResponseReclamationService.getResponseReclamationById(responseId);
    }

    @PostMapping("/add/{idreclamaton}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public ResponseReclamation ajouterResponseReclamation(HttpServletRequest request, @PathVariable("idreclamaton") int idReclmation, @RequestBody ResponseReclamation response) {
        Principal principal = request.getUserPrincipal();
        Optional<User> opUser = userRepository.findByUsername(principal.getName());
        if(opUser.isPresent()){
            User user=opUser.get();
            response.setUser(user);
        }
        iResponseReclamationService.ajouterResponseReclamation(response,idReclmation);
        return response;
    }
}
