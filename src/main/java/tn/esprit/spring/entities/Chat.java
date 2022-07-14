package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Chat implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(max = 5000)
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;


    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    private EStatus status;

    public Chat() {super(); }

    public Chat(@NotBlank @Size(max = 5000) String message, Date date, User sender, User receiver, EStatus status) {
        this.message = message;
        this.date = date;
        this.sender = sender;
        this.receiver = receiver;
        this.status = status;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
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
}
