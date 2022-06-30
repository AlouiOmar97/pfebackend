package tn.esprit.spring.entities;

public class Stat {
    private String critere;
    private String valeur;
    private  User user;

    public Stat() {
    }

    public Stat(String critere, String valeur) {
        this.critere = critere;
        this.valeur = valeur;
    }

    public Stat(User user, String valeur) {
        this.valeur = valeur;
        this.user = user;
    }

    public String getCritere() {
        return critere;
    }

    public void setCritere(String critere) {
        this.critere = critere;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Stat{" +
                "critere='" + critere + '\'' +
                ", valeur='" + valeur + '\'' +
                '}';
    }
}
