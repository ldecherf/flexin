package com.crystal.flexin.resources;


import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by aymane on 22/03/17.
 */
public class Materiel implements Serializable{

    public String id;
    public String etat ;
    public String plateforme ;
    public String nom ;
    public String disponibilite ;


    public Materiel() { }
}
