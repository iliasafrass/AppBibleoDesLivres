package com.example.ilias.masterdetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.*;

import com.google.zxing.integrator.android.IntentIntegrator;
import com.google.zxing.integrator.android.IntentResult;
import android.database.*;
/**
 * Created by abk on 11/12/2016.
 */

public class SuprimerLivre extends AppCompatActivity implements View.OnClickListener{

    public TextView auteur,titre,editeur;
    public EditText isbn;
    public ImageView img;
    public Button scan,valid;
    public String Titre,Auteur,Isbn,Editeur;

    public String scanContent;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supprimer_livre);

        auteur = (TextView)findViewById(R.id.auteurC);
        titre =(TextView)findViewById(R.id.titreC);
        editeur = (TextView)findViewById(R.id.editeurC);
        isbn = (EditText)findViewById(R.id.isbnC);
        img = (ImageView)findViewById(R.id.img);
        scan =(Button)findViewById(R.id.scan);

        scan.setOnClickListener(this);

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
            scanContent = scanningResult.getContents();
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

    public void val(View view){

        Isbn = isbn.getText().toString();
        //Toast.makeText(this, Isbn, Toast.LENGTH_LONG).show();

        GestionDB gestionDB = new GestionDB(this);
        gestionDB.open();

        Cursor c =gestionDB.findIsbn(Isbn);
        c.moveToFirst();
        Toast.makeText(this, c.getString(2), Toast.LENGTH_LONG).show();

        if(c.getCount()==0){
            Toast.makeText(this, "le livre n'existe pas dans la biblio", Toast.LENGTH_LONG).show();
        }
        if(c.getCount() >0){
            gestionDB.suprimerLivreWithIsbn(c.getString(2));
            Toast.makeText(this, "le livre est bien supprimé", Toast.LENGTH_LONG).show();
        }

    }
}
