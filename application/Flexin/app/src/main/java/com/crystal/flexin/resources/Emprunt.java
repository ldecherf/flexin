package com.crystal.flexin.resources;

import java.io.Serializable;


public class Emprunt implements Serializable{

    public String id;
    public String id_materiel;
    public String id_emprunteur;
    public String etat_emprunt;
    public String date_emprunt;
    public String date_retour;

    public Emprunt(String id, String id_materiel, String id_emprunteur, String etat_emprunt, String date_emprunt, String date_retour) {
        this.id = id;
        this.id_materiel = id_materiel;
        this.id_emprunteur = id_emprunteur;
        this.etat_emprunt = etat_emprunt;
        this.date_emprunt = date_emprunt;
        this.date_retour = date_retour;
    }
}
