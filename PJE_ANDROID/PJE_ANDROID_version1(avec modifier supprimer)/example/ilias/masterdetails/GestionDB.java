package com.example.ilias.masterdetails;

/**
 * Created by ilias on 16/11/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.ilias.masterdetails.*;
import java.util.ArrayList;
import java.util.List;
import com.example.ilias.*;
import android.widget.*;

public class GestionDB
{

    //champ de la base de donnée.
    private SQLiteDatabase database;
    private MaBaseSQLite dbHelper;


    public GestionDB(Context context) {
        dbHelper = new MaBaseSQLite(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Cursor getDataBase_Livre(){
        return database.rawQuery("select * from "+MaBaseSQLite.TABLE_LIVRES , null);
    }
    public Cursor getDataBase_Editeur(){
        return database.rawQuery("select * from "+MaBaseSQLite.TABLE_EDITEUR , null);
    }
    public Cursor getDataBase_Auteur(){
        return database.rawQuery("select * from "+MaBaseSQLite.TABLE_AUTEUR , null);
    }

    public Auteur createAuteur(String au)
    {
        ContentValues values = new ContentValues();
        values.put(MaBaseSQLite.COL_NOM_AUTEUR, au);

        long insertId = database.insert(MaBaseSQLite.TABLE_AUTEUR, null,
                values);
        Cursor cursor = database.query(MaBaseSQLite.TABLE_AUTEUR,
                new String[]{MaBaseSQLite.COL_ID_AUTEUR,MaBaseSQLite.COL_NOM_AUTEUR}, MaBaseSQLite.COL_ID_AUTEUR + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Auteur new_auteur = cursorToAuteur(cursor);
        cursor.close();
        return new_auteur;

    }
   /* public Editeur createEditeur(String ed)
    {
        ContentValues values = new ContentValues();
        values.put(MaBaseSQLite.COL_NOM_EDITEUR, ed);

        long insertId = database.insert(MaBaseSQLite.TABLE_EDITEUR, null,
                values);
        Cursor cursor = database.query(MaBaseSQLite.TABLE_EDITEUR,
                new String[]{MaBaseSQLite.COL_ID_EDITEUR,MaBaseSQLite.COL_NOM_EDITEUR}, MaBaseSQLite.COL_ID_EDITEUR + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Editeur new_editeur = cursorToEditeur(cursor);
        cursor.close();

        return new_editeur;

    }*/
    /*
     * creer deux methode pou recuperer le id de auteur et editeur se ils sont deja existe
     * sinon retuen not existe et dans create livre on vas le crees */

    public Cursor findAuteur(String au)
    {// -1 if do not existe
        Cursor c =  database.query(MaBaseSQLite.TABLE_AUTEUR,
                new String[]{MaBaseSQLite.COL_ID_AUTEUR,MaBaseSQLite.COL_NOM_AUTEUR}, MaBaseSQLite.COL_NOM_AUTEUR + " LIKE \"" + au+"\"", null,
                null, null, null);
        return c;
    }
    public Cursor findIsbn(String isbn)
    {// -1 if do not existe
        Cursor c =  database.query(MaBaseSQLite.TABLE_LIVRES,
                new String[]{MaBaseSQLite.COL_ID_LIVRE,
                        MaBaseSQLite.COL_NOM_EDITEUR,
                        MaBaseSQLite.COL_ISBN}, MaBaseSQLite.COL_ISBN +
                        " like \"" + isbn+"\"", null,
                null, null, null);
        return c;
    }

    public Cursor getTableTitre()
    {// -1 if do not existe
        Cursor c =  database.query(MaBaseSQLite.TABLE_LIVRES,
                new String[]{MaBaseSQLite.COL_ID_LIVRE,MaBaseSQLite.COL_ID_AUTEUR,
                        MaBaseSQLite.COL_NOM_EDITEUR,MaBaseSQLite.COL_TITRE,
                        MaBaseSQLite.COL_NATURE,MaBaseSQLite.COL_GENRE,
                        MaBaseSQLite.COL_ISBN,MaBaseSQLite.COL_RESUME,
                        MaBaseSQLite.COL_COMMENTAIRE
                }, null, null,
                MaBaseSQLite.COL_TITRE, null, null);
        return c;
    }
    /*
    public Cursor findEditeur(String ed)
    {

        Cursor c = database.query(MaBaseSQLite.TABLE_EDITEUR,
                new String[]{MaBaseSQLite.COL_ID_EDITEUR,MaBaseSQLite.COL_NOM_EDITEUR}, MaBaseSQLite.COL_NOM_EDITEUR + " like \"" + ed+"\"", null,
                null, null, null);
        return c;
    }*/
    public Cursor getAuteurWithId(Long id)
    {
        Cursor c = database.query(MaBaseSQLite.TABLE_AUTEUR,
                new String[]{MaBaseSQLite.COL_ID_AUTEUR,MaBaseSQLite.COL_NOM_AUTEUR}, MaBaseSQLite.COL_ID_AUTEUR + " = " + id, null,
                MaBaseSQLite.COL_ID_AUTEUR, null, null);
        return c;
    }
    public Cursor getIdAuteurWithTitre(String titre)
    {
        Cursor c = database.query(MaBaseSQLite.TABLE_LIVRES,
                new String[]{MaBaseSQLite.COL_ID_LIVRE}, MaBaseSQLite.COL_TITRE + " like \"" + titre+"\"", null,
                null, null, null);
        return c;
    }
    public Cursor getIdAuteur_WithTitre(String titre)
    {
        Cursor c = database.query(MaBaseSQLite.TABLE_LIVRES,
                new String[]{MaBaseSQLite.COL_ID_AUTEUR}, MaBaseSQLite.COL_TITRE + " like \"" + titre+"\"", null,
                null, null, null);
        return c;
    }
  /*  public Cursor getEditeurWithId(Long id)
    {
        Cursor c = database.query(MaBaseSQLite.TABLE_EDITEUR,
                new String[]{MaBaseSQLite.COL_ID_EDITEUR,MaBaseSQLite.COL_NOM_EDITEUR}, MaBaseSQLite.COL_ID_EDITEUR + " = " + id, null,
                null, null, null);
        return c;
    }*/
    public Cursor getLivreWithTitr(String tit)
    {
        Cursor c = database.query(MaBaseSQLite.TABLE_AUTEUR,
                new String[]{MaBaseSQLite.COL_NOM_AUTEUR}, MaBaseSQLite.COL_TITRE + " like \"" + tit+"\"", null,
                null, null, null);
        return c;
    }

    public Cursor getTableWithTitre(String tit)
    {
        Cursor c =  database.query(MaBaseSQLite.TABLE_LIVRES,
                new String[]{MaBaseSQLite.COL_ID_AUTEUR, MaBaseSQLite.COL_NOM_EDITEUR,
                        MaBaseSQLite.COL_TITRE,MaBaseSQLite.COL_NATURE,
                        MaBaseSQLite.COL_GENRE,MaBaseSQLite.COL_ISBN,
                        MaBaseSQLite.COL_RESUME,MaBaseSQLite.COL_COMMENTAIRE
                }, MaBaseSQLite.COL_TITRE + " like \"" + tit+"\"", null,
                MaBaseSQLite.COL_TITRE, null, null);
        return c;
    }
    public Cursor getTableWithAuteur(String au)
    {
        Cursor c =  database.query(MaBaseSQLite.TABLE_LIVRES,
                new String[]{MaBaseSQLite.COL_ID_AUTEUR, MaBaseSQLite.COL_NOM_EDITEUR,
                        MaBaseSQLite.COL_TITRE,MaBaseSQLite.COL_NATURE,
                        MaBaseSQLite.COL_GENRE,MaBaseSQLite.COL_ISBN,
                        MaBaseSQLite.COL_RESUME,MaBaseSQLite.COL_COMMENTAIRE
                }, MaBaseSQLite.COL_NOM_AUTEUR + " like \"" + au+"\"", null,
                MaBaseSQLite.COL_TITRE, null, null);
        return c;
    }
    public Cursor getTableWithIsbn(String isb)
    {
        Cursor c =  database.query(MaBaseSQLite.TABLE_LIVRES,
                new String[]{MaBaseSQLite.COL_ID_AUTEUR, MaBaseSQLite.COL_NOM_EDITEUR,
                        MaBaseSQLite.COL_TITRE,MaBaseSQLite.COL_NATURE,
                        MaBaseSQLite.COL_GENRE,MaBaseSQLite.COL_ISBN,
                        MaBaseSQLite.COL_RESUME,MaBaseSQLite.COL_COMMENTAIRE
                }, MaBaseSQLite.COL_ISBN + " like \"" + isb+"\"", null,
                MaBaseSQLite.COL_TITRE, null, null);
        return c;
    }
    public Cursor getTableWithTitreIsbn(String tit,String isb)
    {
        Cursor c =  database.query(MaBaseSQLite.TABLE_LIVRES,
                new String[]{MaBaseSQLite.COL_ID_AUTEUR, MaBaseSQLite.COL_NOM_EDITEUR,
                        MaBaseSQLite.COL_TITRE,MaBaseSQLite.COL_NATURE,
                        MaBaseSQLite.COL_GENRE,MaBaseSQLite.COL_ISBN,
                        MaBaseSQLite.COL_RESUME,MaBaseSQLite.COL_COMMENTAIRE
                }, MaBaseSQLite.COL_TITRE + " like \"" + tit+"\""+"and "+MaBaseSQLite.COL_ISBN+" like \""+isb+"\"", null,
                MaBaseSQLite.COL_TITRE, null, null);
        return c;
    }
    public Cursor getTableWithAuteurIsbn(String au,String isb)
    {
        Cursor c =  database.query(MaBaseSQLite.TABLE_LIVRES,
                new String[]{MaBaseSQLite.COL_ID_AUTEUR, MaBaseSQLite.COL_NOM_EDITEUR,
                        MaBaseSQLite.COL_TITRE,MaBaseSQLite.COL_NATURE,
                        MaBaseSQLite.COL_GENRE,MaBaseSQLite.COL_ISBN,
                        MaBaseSQLite.COL_RESUME,MaBaseSQLite.COL_COMMENTAIRE
                }, MaBaseSQLite.COL_NOM_AUTEUR + " like \"" + au+"\""+"and "+MaBaseSQLite.COL_ISBN+" like \""+isb+"\"", null,
                MaBaseSQLite.COL_TITRE, null, null);
        return c;
    }
    public Cursor getTableWithAuteurTitre(String au,String tit)
    {
        Cursor c =  database.query(MaBaseSQLite.TABLE_LIVRES,
                new String[]{MaBaseSQLite.COL_ID_AUTEUR, MaBaseSQLite.COL_NOM_EDITEUR,
                        MaBaseSQLite.COL_TITRE,MaBaseSQLite.COL_NATURE,
                        MaBaseSQLite.COL_GENRE,MaBaseSQLite.COL_ISBN,
                        MaBaseSQLite.COL_RESUME,MaBaseSQLite.COL_COMMENTAIRE
                }, MaBaseSQLite.COL_NOM_AUTEUR + " like \"" + au+"\""+"and "+MaBaseSQLite.COL_TITRE+" like \""+tit+"\"", null,
                MaBaseSQLite.COL_TITRE, null, null);
        return c;
    }
    public Cursor getTableWithAuteurTitreIsbn(String au,String tit,String isb)
    {
        Cursor c1 = database.query(MaBaseSQLite.TABLE_AUTEUR,
                new String[]{MaBaseSQLite.COL_ID_AUTEUR}, MaBaseSQLite.COL_NOM_AUTEUR+ " like \"" + au+"\"", null,
                null, null, null);
        c1.moveToFirst();


        if(c1.getCount() > 0) {

           Cursor c = database.query(MaBaseSQLite.TABLE_LIVRES,
                    new String[]{MaBaseSQLite.COL_ID_AUTEUR, MaBaseSQLite.COL_NOM_EDITEUR,
                            MaBaseSQLite.COL_TITRE, MaBaseSQLite.COL_NATURE,
                            MaBaseSQLite.COL_GENRE, MaBaseSQLite.COL_ISBN,
                            MaBaseSQLite.COL_RESUME, MaBaseSQLite.COL_COMMENTAIRE
                    }, MaBaseSQLite.COL_ID_AUTEUR + " = " + c1.getLong(0) + " and " + MaBaseSQLite.COL_TITRE + " like \"" + tit + "\"" +
                            " and " + MaBaseSQLite.COL_ISBN + " like \"" + isb + "\"", null,
                    null, null, null);
            return c;
        }
        return null;
    }

    public void suprimerLivreWithIsbn(String isbn){
        database.delete(MaBaseSQLite.TABLE_LIVRES,MaBaseSQLite.COL_ISBN + " like \""+isbn+"\"",null);
    }
    public void suprimerLivreWithTitre(String titre){
        database.delete(MaBaseSQLite.TABLE_LIVRES,MaBaseSQLite.COL_TITRE+ " like \""+titre+"\"",null);
    }

    // comme netre id auteur , id editeur
    public void createLivre(String a,String ed,Book b) {

        Long aut = null;

        Cursor Cauteur = findAuteur(a);


        if(Cauteur.getCount() == 0){
            //create auteur;
           aut =createAuteur(a).getId_auteur();

       }else if(Cauteur.getCount() > 0){
            Cauteur.moveToFirst();
            aut = Cauteur.getLong(0);
       }

    //Création d'un ContentValues (fonctionne comme une HashMap)
    ContentValues values = new ContentValues();
    //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(MaBaseSQLite.COL_ID_AUTEUR, aut);
        values.put(MaBaseSQLite.COL_NOM_EDITEUR,ed);
        values.put(MaBaseSQLite.COL_TITRE,b.getTitre());
        values.put(MaBaseSQLite.COL_NATURE,b.getNature());
        values.put(MaBaseSQLite.COL_GENRE,b.getGenre());
        values.put(MaBaseSQLite.COL_ISBN,b.getIsbn());
        values.put(MaBaseSQLite.COL_RESUME,b.getResume());
        values.put(MaBaseSQLite.COL_COMMENTAIRE,b.getCommentaire());
        values.put(MaBaseSQLite.COL_IMAGE,b.getImage());

    long insertId = database.insert(MaBaseSQLite.TABLE_LIVRES, null,
            values);
/*
    Cursor cursor = database.query(MaBaseSQLite.TABLE_LIVRES,new String[]{
            MaBaseSQLite.COL_ID_AUTEUR,MaBaseSQLite.COL_ID_EDITEUR,
            MaBaseSQLite.COL_TITRE,MaBaseSQLite.COL_NATURE,
            MaBaseSQLite.COL_GENRE,MaBaseSQLite.COL_ISBN,
            MaBaseSQLite.COL_RESUME,MaBaseSQLite.COL_COMMENTAIRE
        }, MaBaseSQLite.COL_ID_LIVRE + " = " + insertId, null,
            null, null, null);
    cursor.moveToFirst();
    Book newBook = cursorToLivre(cursor);
    cursor.close();
         return newBook;*/
    }



    public void deleteLivre(Book book) {
        long id = book.getId();
        System.out.println("Book deleted with id: " + id);
        database.delete(MaBaseSQLite.TABLE_LIVRES, MaBaseSQLite.COL_ID_LIVRE
                + " = " + id, null);
    }

    public void deleteBdd(){
        database.delete(MaBaseSQLite.TABLE_AUTEUR,null,null);
        database.delete(MaBaseSQLite.TABLE_EDITEUR,null,null);
        database.delete(MaBaseSQLite.TABLE_LIVRES,null,null);
    }

    public List<Book> getAllBooks() {
        List<Book> books= new ArrayList<Book>();

        Cursor cursor = database.query(MaBaseSQLite.TABLE_LIVRES,
                new String[]{
                        MaBaseSQLite.COL_ID_LIVRE,MaBaseSQLite.COL_ID_AUTEUR,
                        MaBaseSQLite.COL_TITRE,MaBaseSQLite.COL_NATURE,
                        MaBaseSQLite.COL_GENRE,MaBaseSQLite.COL_ISBN,
                        MaBaseSQLite.COL_RESUME,MaBaseSQLite.COL_COMMENTAIRE,
                        MaBaseSQLite.COL_IMAGE}
                , null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Book book = cursorToLivre(cursor);
            books.add(book);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return books;
    }

    private Book cursorToLivre(Cursor cursor) {
        Book book= new Book();
        book.setId(cursor.getLong(0));
        book.setTitre(cursor.getString(1));
        //book.setAuteur(cursor.getString(2));
        book.setIsbn(cursor.getString(2));
        book.setImage(cursor.getString(3));
        return book;
    }


    private Auteur cursorToAuteur(Cursor cursor) {
        Auteur au= new Auteur();
        au.setId_auteur(cursor.getLong(0));
        return au;
    }

    public Book getLivreWithTitre(String titre)
    {
        Cursor c = database.query(MaBaseSQLite.TABLE_LIVRES,new String[]{
                MaBaseSQLite.COL_ID_LIVRE,MaBaseSQLite.COL_ID_AUTEUR,
                MaBaseSQLite.COL_NOM_EDITEUR,
                MaBaseSQLite.COL_TITRE,MaBaseSQLite.COL_NATURE,
                MaBaseSQLite.COL_GENRE,MaBaseSQLite.COL_ISBN,
                MaBaseSQLite.COL_RESUME,MaBaseSQLite.COL_COMMENTAIRE,
                MaBaseSQLite.COL_IMAGE},
                "titre like "+"'%"+titre+"%'",null,null,null,null);
        return cursorToLivre(c);
    }
/*
    public Book getLivreWithId(int id)
    {
        Cursor c = database.query(MaBaseSQLite.TABLE_LIVRES,new String[]{
                MaBaseSQLite.COL_ID_LIVRE,MaBaseSQLite.COL_ID_AUTEUR,
                MaBaseSQLite.COL_TITRE,MaBaseSQLite.COL_NATURE,
                MaBaseSQLite.COL_GENRE,MaBaseSQLite.COL_ISBN,
                MaBaseSQLite.COL_RESUME,MaBaseSQLite.COL_COMMENTAIRE,
                MaBaseSQLite.COL_IMAGE},
                MaBaseSQLite.COL_ID_AUTEUR +"="+id,null,null,null,null);
        return cursorToLivre(c);
    }*/
    public Cursor getLivreWithId(String titre)
    {
        Cursor c = database.query(MaBaseSQLite.TABLE_LIVRES,new String[]{
                        MaBaseSQLite.COL_ID_LIVRE,MaBaseSQLite.COL_ID_AUTEUR,
                        MaBaseSQLite.COL_NOM_EDITEUR,
                        MaBaseSQLite.COL_TITRE,MaBaseSQLite.COL_NATURE,
                        MaBaseSQLite.COL_GENRE,MaBaseSQLite.COL_ISBN,
                        MaBaseSQLite.COL_RESUME,MaBaseSQLite.COL_COMMENTAIRE,
                        MaBaseSQLite.COL_IMAGE},
                MaBaseSQLite.COL_TITRE +" like \""+titre+"\"",null,null,null,null);
        return c;
    }

    public int updateLivre(Book b){
        //La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simple préciser quelle livre on doit mettre à jour grâce à l'ID

        ContentValues values = new ContentValues();
        values.put(MaBaseSQLite.COL_NOM_EDITEUR,b.getEditeur());
        values.put(MaBaseSQLite.COL_TITRE,b.getTitre());
        values.put(MaBaseSQLite.COL_NATURE,b.getNature());
        values.put(MaBaseSQLite.COL_GENRE,b.getGenre());
        values.put(MaBaseSQLite.COL_ISBN,b.getIsbn());
        values.put(MaBaseSQLite.COL_RESUME,b.getResume());
        values.put(MaBaseSQLite.COL_COMMENTAIRE,b.getCommentaire());
        return database.update(MaBaseSQLite.TABLE_LIVRES, values, MaBaseSQLite.COL_TITRE + " like \""+webpageListActivity.text+"\"", null);
    }
    public Long getLongBook(String titre)
    {
        Cursor c = database.query(MaBaseSQLite.TABLE_LIVRES,new String[]{MaBaseSQLite.COL_ID_LIVRE},
                MaBaseSQLite.COL_TITRE+" like \""+titre+"\"",null,null,null,null);
        return c.getLong(0);
    }
    public void updateLivreAuteur(String[] strOrg,String[] strAdd) {
        //La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simple préciser quelle livre on doit mettre à jour grâce à l'ID
        if(strOrg.length == strAdd.length)
        {
            for (int i = 0; i < strAdd.length; i++)
            {
                ContentValues values = new ContentValues();
                values.put(MaBaseSQLite.COL_NOM_AUTEUR, strAdd[i]);
                database.update(MaBaseSQLite.TABLE_AUTEUR, values, MaBaseSQLite.COL_NOM_AUTEUR +
                        " like \"" + strOrg[i] + "\"", null);
            }
        }
    }



}
