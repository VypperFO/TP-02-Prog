import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashMap;

public class Inventaire implements Serializable {
    private String nom, description, categorie;
    private int prix, numSerie;
    private LocalDate dateAchat;
    private LinkedHashMap<LocalDate, String> entretien;

    public Inventaire(String nom, String description, String categorie, int prix, int numSerie, LocalDate dateAchat) {
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.prix = prix;
        this.numSerie = numSerie;
        this.dateAchat = dateAchat;
    }

    public Inventaire(String nom, int prix) {
        this.nom = nom;
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(int numSerie) {
        this.numSerie = numSerie;
    }

    public LocalDate getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(LocalDate dateAchat) {
        this.dateAchat = dateAchat;
    }

    public LinkedHashMap<LocalDate, String> getEntretien() {
        return entretien;
    }

    public void setEntretien(LinkedHashMap<LocalDate, String> entretien) {
        this.entretien = entretien;
    }
}
