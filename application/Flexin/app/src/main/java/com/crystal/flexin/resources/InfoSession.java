package com.crystal.flexin.resources;

/**
 * Created by aymane on 10/05/17.
 */

public class InfoSession {

    private String nom ;
    private String email ;
    private String tel ;

    public InfoSession(String nom, String email, String tel){
        this.nom = nom ;
        this.email = email ;
        this.tel = tel ;
    }

    public String getNom(){return  this.nom;}
    public String getEmail(){return this.email;}
    public String getTel(){return this.tel;}

}
