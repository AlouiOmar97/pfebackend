package tn.esprit.spring.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class UpdateProfileRequest {


  @NotBlank
  @Size(min = 3, max = 20)
  private String nom;

  @NotBlank
  @Size(min = 3, max = 20)
  private String prenom;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;




  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
