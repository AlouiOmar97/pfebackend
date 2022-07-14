package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;


import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Reclamation implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3152690779535828408L;


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(max = 1500)
    private String motif;

    @NotBlank
    @Size(max = 50)
    private String etat;

    @NotBlank
    @Size(max = 50)
    private String type;

    @NotBlank
    @Size(max = 50)
    private String departement;

    @NotBlank
    @Size(max = 5000)
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy="reclamation",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch=FetchType.EAGER)
    @JsonIgnoreProperties("reclamation")
    private List<ResponseReclamation> reponsesReclamation = new ArrayList<>();

    public Reclamation() {
        super();
    }

    public Reclamation(String motif, String type) {
        this.motif = motif;
        this.type = type;
    }

    public Reclamation(@NotBlank @Size(max = 1500) String motif, @NotBlank @Size(max = 50) String etat, @NotBlank @Size(max = 50) String type, @NotBlank @Size(max = 50) String departement, @NotBlank @Size(max = 5000) String message, Date date, User user) {
        this.motif = motif;
        this.etat = etat;
        this.type = type;
        this.departement = departement;
        this.message = message;
        this.date = date;
        this.user = user;
    }

    public Reclamation(int id,@NotBlank @Size(max = 1500) String motif, @NotBlank @Size(max = 50) String etat, @NotBlank @Size(max = 50) String type, @NotBlank @Size(max = 50) String departement, @NotBlank @Size(max = 5000) String message, Date date, User user) {
        this.id=id;
        this.motif = motif;
        this.etat = etat;
        this.type = type;
        this.departement = departement;
        this.message = message;
        this.date = date;
        this.user = user;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDepartements(List<ResponseReclamation> departements) {
        this.reponsesReclamation = departements;
    }


    public void addResponseReclamation(ResponseReclamation responseReclamation){
        responseReclamation.setReclamation(this);
        this.reponsesReclamation.add(responseReclamation);
    }

    public List<ResponseReclamation> getReponsesReclamation() {
        return reponsesReclamation;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "id=" + id +
                ", motif='" + motif + '\'' +
                ", etat='" + etat + '\'' +
                ", type='" + type + '\'' +
                ", departement='" + departement + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                ", user=" + user +
                ", reponsesReclamation=" + reponsesReclamation +
                '}';
    }
}
