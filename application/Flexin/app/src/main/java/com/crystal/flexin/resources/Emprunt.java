package com.crystal.flexin.resources;

import com.google.gson.annotations.Expose;

/**
 * Created by aymane on 05/05/17.
 */

public class Emprunt {

    @Expose
    public int id;
    @Expose
    public int id_materiel;
    @Expose
    public int id_emprunteur;
    @Expose
    public String emprunt;
}
