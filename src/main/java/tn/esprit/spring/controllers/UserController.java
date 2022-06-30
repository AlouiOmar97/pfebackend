package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.ERole;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.payload.request.SignupRequest;
import tn.esprit.spring.payload.request.UpdateProfileRequest;
import tn.esprit.spring.payload.request.UpdateUserRequest;
import tn.esprit.spring.payload.response.MessageResponse;
import tn.esprit.spring.repository.RoleRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.services.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    IUserService iUserService;
    @Autowired
    RoleRepository roleRepository;


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
    public List<User> getAllUser() {


        return iUserService.getAllUsers();
    }

    @GetMapping(value = "/{iduser}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public User getUserById(@PathVariable("iduser") int userId) {

        return iUserService.getUserById(userId);
    }

    @PostMapping("/add/{idreclamaton}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public User ajouterUser(HttpServletRequest request, @PathVariable("idreclamaton") int idReclmation, @RequestBody User user) {
//        Principal principal = request.getUserPrincipal();
//        Optional<User> opUser = userRepository.findByUsername(principal.getName());
//        if(opUser.isPresent()){
//            User user=opUser.get();
//            user.setUser(user);
//        }
        iUserService.ajouterUser(user);
        return user;
    }

    @PutMapping("/update/{iduser}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<?> registerUser(@Valid @RequestBody UpdateUserRequest signUpRequest, @PathVariable("iduser") int idUser) {
        User user=userRepository.getById(new Long(idUser));
        if(!user.getUsername().equals(signUpRequest.getUsername())) {
            if (userRepository.existsByUsername(signUpRequest.getUsername())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error update: Username is already taken!"));
            }
        }
        if(!user.getEmail().equals(signUpRequest.getEmail())) {
            if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error update : Email is already in use!"));
            }
        }

        user.setNom(signUpRequest.getNom());
        user.setPrenom(signUpRequest.getPrenom());
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error update: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
    }

    @DeleteMapping("/delete/{iduser}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public String deleteReclamation(@PathVariable("iduser") int idUser) {
        iUserService.deleteUser(idUser);
        return "reclamation deleted!";
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public User GetConnectedUser(HttpServletRequest request) {
        User user=new User();
        Principal principal = request.getUserPrincipal();
        Optional<User> opUser = userRepository.findByUsername(principal.getName());
        if(opUser.isPresent()){
            User user1=opUser.get();
            user=user1;
        }
        iUserService.ajouterUser(user);
        return user;
    }

    @PutMapping("/update/profile")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('USER')")
    @ResponseBody
    public ResponseEntity<?> updateProfile(@Valid @RequestBody UpdateProfileRequest signUpRequest, HttpServletRequest request) {
        User user=new User();
        Principal principal = request.getUserPrincipal();
        Optional<User> opUser = userRepository.findByUsername(principal.getName());
        if(opUser.isPresent()){
            User user1=opUser.get();
            user=user1;
        }
       
        if(!user.getEmail().equals(signUpRequest.getEmail())) {
            if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error update : Email is already in use!"));
            }
        }

        user.setNom(signUpRequest.getNom());
        user.setPrenom(signUpRequest.getPrenom());
        user.setEmail(signUpRequest.getEmail());

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
    }

}
