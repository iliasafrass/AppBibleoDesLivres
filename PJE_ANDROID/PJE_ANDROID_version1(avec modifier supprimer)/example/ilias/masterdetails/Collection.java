package com.example.ilias.masterdetails;

/**
 * Created by abk on 30/11/2016.
 */

public class Collection {


    private long id_collection;
    protected String nom_collection,auteur,commentaire;


    public Collection(String nom_collection,String auteur,String commentaire)
    {
        this.nom_collection = nom_collection;
        this.auteur = auteur;
        this.commentaire = commentaire;
    }

    public void setId_collection(long id_collection) {
        this.id_collection = id_collection;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setNom_collection(String nom_collection) {
        this.nom_collection = nom_collection;
    }

    public long getId_collection() {
        return id_collection;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getNom_collection() {
        return nom_collection;
    }
}
