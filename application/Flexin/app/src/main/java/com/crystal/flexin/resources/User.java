package com.crystal.flexin.resources;
import java.io.Serializable;


public class User implements Serializable{

    private String id;    
    private String name ;
    private String firstname;
    private String password;
    private String mail ;
    private String tel ;


    public void setId(String id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isNull() {
        return (this.name == null);
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public String getTel() {
        return tel;
    }
}
