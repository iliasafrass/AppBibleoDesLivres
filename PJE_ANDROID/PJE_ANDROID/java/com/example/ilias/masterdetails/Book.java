package com.example.ilias.masterdetails;

/**
 * Created by ilias on 16/11/2016.
 */

public class Book {

    private long id_livre,id_auteur,id_collection;

    protected String editeur,titre,nature,genre,isbn,resume,commentaire;

    private String image;

    public String getNature() {
        return nature;
    }

    public String getGenre() {
        return genre;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getResume() {
        return resume;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Book()
    {}

    public Book(String editeur,String titre,String nature, String genre,String isbn,String resume,String commentaire,String image)
    {
        this.titre = titre;
        this.isbn = isbn;
        this.image = image;
        this.genre = genre;
        this.resume = resume;
        this.commentaire = commentaire;
        this.nature = nature;
        this.editeur = editeur;
    }
    public void setEditeur(String editeur){
        this.editeur = editeur;
    }
    public String getEditeur()
    {
        return  editeur;
    }
    public long getId_collection() {
        return id_collection;
    }

    public void setId_livre(long id_livre) {
        this.id_livre = id_livre;
    }

    public void setId_collection(long id_collection) {
        this.id_collection = id_collection;
    }

    public void setId_auteur(long id_auteur) {
        this.id_auteur = id_auteur;
    }

    public long getId_auteur() {
        return id_auteur;

    }

    public long getId_livre() {
        return id_livre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitre()
    {
        return titre;
    }

    public void setTitre(String titre)
    {
        this.titre = titre;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public long getId()
    {
        return id_livre;
    }

    public void setId(long id_livre)
    {
        this.id_livre = id_livre;
    }

    public String toString()
    {
        return "ISBN : "+this.isbn+"\nTitre : "+this.titre+"\nNature : "+this.nature+"\nGenre : "+this.genre
                +"\nCommentaire : "+this.commentaire;
    }

}
