package tn.esprit.spring.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Rating;
import tn.esprit.spring.entities.Reclamation;
import tn.esprit.spring.entities.ResponseReclamation;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.services.IRatingService;
import tn.esprit.spring.services.IResponseReclamationService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/rating")
public class RatingController {

    @Autowired
    IRatingService iRatingService;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/{idreclamation}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public List<Rating> getRatingByRecId(@PathVariable("idreclamation") int idReclmation) {

        return iRatingService.getRatingByRecID(idReclmation);
    }



    @PostMapping("/add/{idreclamation}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public Rating ajouterRating(HttpServletRequest request, @PathVariable("idreclamation") int idReclmation, @RequestBody Rating rating) {
        System.out.println("Ratiiiiiiiiing");
        System.out.println(rating);
        Principal principal = request.getUserPrincipal();
        Optional<User> opUser = userRepository.findByUsername(principal.getName());
        if(opUser.isPresent()){
            User user=opUser.get();
            rating.setUser(user);
        }
        iRatingService.ajouterRating(rating,idReclmation);
        return rating;
    }


    @DeleteMapping("/delete/{idrating}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public String deleteRating(@PathVariable("idrating") int ratingId) {
        iRatingService.deleteRating(ratingId);
        return "rating deleted!";
    }
}
