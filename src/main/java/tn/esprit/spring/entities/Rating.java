package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Rating implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(max = 5000)
    private String message;

    @NotBlank
    @Size(max = 5000)
    private String value;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    private User user;

    @ManyToOne
    @JsonIgnoreProperties("rating")
    private Reclamation reclamation;

    public Rating() {super(); }

    public Rating(@NotBlank @Size(max = 5000) String message, @NotBlank @Size(max = 5000) String value, Date date, User user) {
        this.message = message;
        this.value = value;
        this.date = date;
        this.user = user;
    }

    public Reclamation getReclamation() {
        return reclamation;
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", value='" + value + '\'' +
                ", date=" + date +
                ", user=" + user +
                ", reclamation=" + reclamation +
                '}';
    }
}
