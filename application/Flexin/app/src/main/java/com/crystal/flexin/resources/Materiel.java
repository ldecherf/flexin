package com.crystal.flexin.resources;


import com.google.gson.annotations.Expose;

/**
 * Created by aymane on 22/03/17.
 */
public class Materiel {

    @Expose
    public String etat ;
    @Expose
    public String description ;
    @Expose
    public String plateforme ;
    @Expose
    public String reference ;
    @Expose
    public String photo ;
    @Expose
    public String nom ;


    public Materiel() { }
}
