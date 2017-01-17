package com.example.ilias.masterdetails;

/**
 * Created by abk on 30/11/2016.
 */

public class Auteur {

    private long id_auteur;
    protected String nom_auteur;


    public Auteur(String nom_auteur)
    {
        this.nom_auteur = nom_auteur;
    }

    public Auteur() {
        this.nom_auteur = "";
    }

    public void setId_auteur(long id_auteur) {
        this.id_auteur = id_auteur;
    }

    public long getId_auteur() {
        return id_auteur;
    }

    public String getNom_auteur() {
        return nom_auteur;
    }

    public void setNom_auteur(String nom_auteur) {
        this.nom_auteur = nom_auteur;
    }
}
