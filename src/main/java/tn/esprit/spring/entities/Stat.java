package tn.esprit.spring.entities;

public class Stat {
    private String critere;
    private String valeur;

    public Stat() {
    }

    public Stat(String critere, String valeur) {
        this.critere = critere;
        this.valeur = valeur;
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

    @Override
    public String toString() {
        return "Stat{" +
                "critere='" + critere + '\'' +
                ", valeur='" + valeur + '\'' +
                '}';
    }
}
