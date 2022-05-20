
/**
 * @author Félix-Olivier Latulippe
 * @DA 2173242
 * @session HV2022
 * 
 * Ce fichier contient la classe Inventaire
 */
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashMap;

public class Inventaire implements Serializable {
    private String nom, description, categorie; // Le nom, la description et la categorie de l'objet
    private String numSerie; // Numéro de série objet
    private double prix; // Prix objet
    private LocalDate dateAchat; // Date achat objet
    private LinkedHashMap<LocalDate, String> entretien; // entretiens objet

    /**
     * Constructeur de la classe Inventaire
     * 
     * @param nom         nom objet
     * @param description description objet
     * @param categorie   categorie objet
     * @param prix        prix objet
     * @param numSerie    numéro série objet
     * @param dateAchat   date achat objet
     * @param entretien   entretiens objet
     */
    public Inventaire(String nom, String description, String categorie, double prix, String numSerie,
            LocalDate dateAchat, LinkedHashMap entretien) {
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.prix = prix;
        this.numSerie = numSerie;
        this.dateAchat = dateAchat;
        this.entretien = entretien;
    }

    /**
     * Fonction qui permet d'avoir le nom de l'objet
     * 
     * @return Retourne le nom de l'objet
     */
    public String getNom() {
        return nom;
    }

    /**
     * Fonction qui permet de modifier le nom de l'objet
     * 
     * @param nom nom à modifier
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Fonction qui permet d'avoir la description de l'objet
     * 
     * @return Retourne la description de l'objet
     */
    public String getDescription() {
        return description;
    }

    /**
     * Fonction qui permet de modifier la description de l'ojet
     * 
     * @param description description à modifier
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Fonction qui permet d'avoir la catégorie de l'objet
     * 
     * @return Retourne la catégorie de l'objet
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     * Fonction qui permet de modifier la catégorie de l'objet
     * 
     * @param categorie catégorie à modifier
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    /**
     * Fonction qui permet d'avoir le prix de l'objet
     * 
     * @return Retourne le prix de l'objet
     */
    public double getPrix() {
        return prix;
    }

    /**
     * Fonction qui permet de modifier le prix de l'objet
     * 
     * @param prix prix à modifier
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     * Fonction qui permet d'avoir le numéro de série de l'objet
     * 
     * @return Retourne le numéro de série de l'objet
     */
    public String getNumSerie() {
        return numSerie;
    }

    /**
     * Fonction qui permet de modifier le numéro de série de l'objet
     * 
     * @param numSerie num.ro de série de l'objet
     */
    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    /**
     * Fonction qui permet d'avoir la date d'achat de l'objet
     * 
     * @return Retourne la date d'achat de l'objet
     */
    public LocalDate getDateAchat() {
        return dateAchat;
    }

    /**
     * Fonction qui permet de modifier la date d'achat de l'objet
     * 
     * @param dateAchat date d'achat à modifier
     */
    public void setDateAchat(LocalDate dateAchat) {
        this.dateAchat = dateAchat;
    }

    /**
     * Fonction qui permet d'avoir les entretiens de l'objet
     * 
     * @return Retourne les entretiens de l'objet
     */
    public LinkedHashMap<LocalDate, String> getEntretien() {
        return entretien;
    }

    /**
     * Fonction qui permet d'ajouter un entretien à l'objet
     * 
     * @param date        la date de l'entretien
     * @param description la description de l'entretien
     */
    public void addEntretien(LocalDate date, String description) {
        this.entretien.put(date, description);
    }

    /**
     * Fonction qui permet de supprimer un entretien
     * 
     * @param key la clé à supprimer
     */
    public void removeEntretien(LocalDate key) {
        this.entretien.remove(key);
    }
}
