package com.crystal.flexin.resources;

/**
 * Created by basaile92 on 01/04/2017.
 */

public class Equipment {

    private int id;
    private String nom;
    private String reference;
    private int id_rangement;
    private String photo;
    private String etat;
    private String description;
    private int id_categorie;
    private int id_plateforme;
    private int id_position;

    public Equipment(int id, String nom, String reference, int id_rangement, String photo, String etat, String description, int id_categorie, int id_plateforme, int id_position) {
        this.id = id;
        this.nom = nom;
        this.reference = reference;
        this.id_rangement = id_rangement;
        this.photo = photo;
        this.etat = etat;
        this.description = description;
        this.id_categorie = id_categorie;
        this.id_plateforme = id_plateforme;
        this.id_position = id_position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getId_rangement() {
        return id_rangement;
    }

    public void setId_rangement(int id_rangement) {
        this.id_rangement = id_rangement;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public int getId_plateforme() {
        return id_plateforme;
    }

    public void setId_plateforme(int id_plateforme) {
        this.id_plateforme = id_plateforme;
    }

    public int getId_position() {
        return id_position;
    }

    public void setId_position(int id_position) {
        this.id_position = id_position;
    }

    @Override
    public String toString() {
        return "Equipment" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", reference='" + reference + '\'' +
                ", id_rangement=" + id_rangement +
                ", photo='" + photo + '\'' +
                ", etat='" + etat + '\'' +
                ", description='" + description + '\'' +
                ", id_categorie=" + id_categorie +
                ", id_plateforme=" + id_plateforme +
                ", id_position=" + id_position;
    }
}
