package com.example.ilias.masterdetails;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.zxing.integrator.android.*;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Rechercher_livre extends AppCompatActivity implements View.OnClickListener {



    private Button scanBtn;
    private String Auteur,Titre,Isbn;
    EditText auteur,titre,isbn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_isbn);

        scanBtn = (Button)findViewById(R.id.scan_button);
        isbn = (EditText) findViewById(R.id.isbnC);
         auteur= (EditText)findViewById(R.id.auteurC);
         titre= (EditText)findViewById(R.id.titreC);
        scanBtn.setOnClickListener(this);
    }

    public void onClick(View v){
        if(v.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
                /* return le format de code bare (EAN_13) */
            //formatTxt.setText("FORMAT: " + scanFormat);
                /* return le numero de code bar*/
            isbn.setText(scanContent);

        }else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    // chercher un livre
    public void val(View view){

        Auteur = auteur.getText().toString();
        Titre = titre.getText().toString();
        Isbn = isbn.getText().toString();

        String img = "img";

         /* création d'une instance de la classe GestionDB */
        GestionDB gestionDB = new GestionDB(this);
        /* on ouvre la base de données pour écrire dedans */
        gestionDB.open();

        //Cursor c =null;

        if(Auteur.length() != 0 && Titre.length() != 0 && Isbn.length() != 0) {
            Toast.makeText(this, "auteur  titre  isbn", Toast.LENGTH_LONG).show();
            // return table livre contient les ligne titre et auteur et isbn
            Cursor c = gestionDB.getTableWithAuteurTitreIsbn(Auteur, Titre, Isbn);

            int t = 0;
            if (c == null){
                Toast.makeText(this, "le livre n'existe pas !!", Toast.LENGTH_LONG).show();

            }else {
                Toast.makeText(this, "le livre existe !!", Toast.LENGTH_LONG).show();
                c.moveToFirst();
                int nbligne = c.getCount();
        /*    while(t<nbligne)
            {
                String nom_auteur  ="";



                // table livre contient les id autreur de titre auteur isbn rechercher
                Cursor idAuteur= gestionDB.getIdAuteur_WithTitre(c.getString(3));
                idAuteur.moveToFirst();
                // table auteur contient les id et nom des auteur de titre auteur isbn rechercher
                Cursor k =gestionDB.getAuteurWithId(idAuteur.getLong(0));
                k.moveToFirst();

                int nb_l = idAuteur.getCount();
                nom_auteur = k.getString(1);
                int i=0;

                while(idAuteur.moveToNext()){
                    Cursor new_k = gestionDB.getAuteurWithId(idAuteur.getLong(0));
                    new_k.moveToFirst();
                    nom_auteur = nom_auteur + ", " + new_k.getString(1);

                    //Toast.makeText(this,""+nb_l,Toast.LENGTH_LONG).show();
                }
                t++;
                c.moveToNext();
            }*/
            }
            //webpageListActivity.afficher_liste("1","2","3","4","5","6","7","8","9");
        }
        if(Auteur.length() != 0 && Titre.length() != 0 && Isbn.length() == 0 ){
            Toast.makeText(this,"auteur  titre ",Toast.LENGTH_LONG).show();
            // return table livre contient les ligne titre et auteur et isbn
           // c = gestionDB.getTableWithAuteurTitre(Auteur,Titre);

        }
        if(Auteur.length() != 0 && Isbn.length() != 0 && titre.length() == 0 ) {
            Toast.makeText(this, "auteur  isbn", Toast.LENGTH_LONG).show();
            // c = gestionDB.getTableWithAuteurIsbn(Auteur,Isbn);

        }
        if(Titre.length() != 0 && Isbn.length() != 0 && Auteur.length() == 0 ){
            Toast.makeText(this,"titre  isbn",Toast.LENGTH_LONG).show();
            // return table livre contient les ligne titre et auteur et isbn
            //c = gestionDB.getTableWithTitreIsbn(Titre,Isbn);

        }

        if(Auteur.length() != 0 && Titre.length() == 0 && Isbn.length() == 0){
            //c = gestionDB.getTableWithAuteur(Auteur);
        }
        if(Titre.length() != 0 && Auteur.length() == 0 && Isbn.length() == 0){
            //c = gestionDB.getTableWithTitre(Titre);
        }
        if(Isbn.length() != 0 && Auteur.length() == 0 && Titre.length() == 0){
            //c = gestionDB.getTableWithIsbn(Isbn);
        }
        if (Auteur.length() ==0 && Titre.length() == 0 && Isbn.length() == 0){
            Toast.makeText(this,"Veuillez remplir pour la recherche",Toast.LENGTH_LONG).show();
        }
        Toast.makeText(this,""+Auteur.length(),Toast.LENGTH_LONG).show();
        //webpageListActivity.afficher_liste("1","2","3","4","5","6","7","8","9");
        //Intent intent = new Intent(Rechercher_livre.this,webpageListActivity.class);
       // startActivity(intent);
    }
}


