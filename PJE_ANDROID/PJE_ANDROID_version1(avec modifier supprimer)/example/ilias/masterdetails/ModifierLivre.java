package com.example.ilias.masterdetails;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.*;
import android.view.View;
import android.widget.EditText;
import android.widget.*;
import android.database.*;
import java.io.*;
import java.util.*;
import java.lang.String;
import java.lang.Object;
import  java.lang.*;
/**
 * Created by abk on 11/12/2016.
 */

public class ModifierLivre extends AppCompatActivity {

    String res ;
    EditText titre,auteur,editeur,nature,genre,resume,commentaire,isbn;
    String Titre,Auteur,Editeur,Nature,Genre,Resume,Commentaire,Isbn;
    Button val;
    Long id= Long.valueOf(0);

    String strOrg[],strAdd[];

    GestionDB gestionDB = new GestionDB(this);
        /* on ouvre la base de données pour écrire dedans */


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifier);

        auteur = (EditText)findViewById(R.id.auteurC);
        titre = (EditText)findViewById(R.id.titreC);
        editeur = (EditText)findViewById(R.id.editeurC);
        nature = (EditText)findViewById(R.id.natureC);
        genre = (EditText)findViewById(R.id.genreC);
        resume = (EditText)findViewById(R.id.resumeC);
        commentaire = (EditText)findViewById(R.id.commenatireC);
        isbn = (EditText)findViewById(R.id.isbnC);

        gestionDB.open();

        Cursor c = gestionDB.getLivreWithId(webpageListActivity.text);

        //id = gestionDB.getLongBook(c.getString(3));
        //Toast.makeText(this,"marouane : "+c.getString(3),Toast.LENGTH_LONG).show();
        c.moveToFirst();

        Cursor idAuteur = gestionDB.getIdAuteurWithTitre(webpageListActivity.text);
        idAuteur.moveToFirst();
        Cursor k =gestionDB.getAuteurWithId(idAuteur.getLong(0));
        k.moveToFirst();
        int t= 0;
        int nbr = idAuteur.getCount();
        String nom_auteur = k.getString(1);
        while(idAuteur.moveToNext()){
            Cursor new_k = gestionDB.getAuteurWithId(idAuteur.getLong(0));
            new_k.moveToFirst();
            nom_auteur = nom_auteur + ", " + new_k.getString(1);
            //Toast.makeText(this,""+nb_l,Toast.LENGTH_LONG).show();
        }

        strOrg = nom_auteur.split(",");

        auteur.setText(nom_auteur);
        editeur.setText(c.getString(2));
        titre.setText(c.getString(3));
        nature.setText(c.getString(4));
        genre.setText(c.getString(5));
        isbn.setText(c.getString(6));
        resume.setText(c.getString(7));
        commentaire.setText(c.getString(8));


        //Toast.makeText(this,webpageListActivity.text,Toast.LENGTH_LONG).show();
        //Toast.makeText(this,"titre: "+c.getTitre()+", auteur : "+c.getId_auteur()+", editeur: "+c.getEditeur(),Toast.LENGTH_LONG).show();
    }



    public void val(View view)
    {

        Titre = titre.getText().toString();
        Auteur = auteur.getText().toString();
        Editeur = editeur.getText().toString();
        Nature = nature.getText().toString();
        Genre = genre.getText().toString();
        Resume = resume.getText().toString();
        Commentaire = commentaire.getText().toString();
        Isbn = isbn.getText().toString();

        gestionDB.open();

        Book b = new Book(Editeur,Titre,Nature,Genre,Isbn,Resume,Commentaire,"");
        strAdd = Auteur.split(",");

        gestionDB.updateLivreAuteur(strOrg,strAdd);

    //modifier livre de webpagelistactivity.tetxe
        gestionDB.updateLivre(b);
        //Toast.makeText(this,"strAdd : "+strAdd.length+", strOrg : "+strOrg.length,Toast.LENGTH_LONG).show();

        Intent intent = new Intent(ModifierLivre.this,webpageListActivity.class);
        startActivity(intent);

    }

}
