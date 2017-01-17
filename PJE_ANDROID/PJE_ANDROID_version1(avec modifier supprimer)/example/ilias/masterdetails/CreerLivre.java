package com.example.ilias.masterdetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.zxing.integrator.android.IntentResult;
import com.google.zxing.integrator.android.IntentIntegrator;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.EditText;
import android.view.View;

import org.json.*;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;


import android.support.v7.app.*;
import android.support.v7.app.*;



import android.os.Bundle;
import android.app.*;
import android.content.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.os.*;
import java.io.*;
import java.*;
import java.io.*;
import java.net.*;

import org.json.*;


import android.net.*;
import android.graphics.*;
import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.R.attr.*;
import android.R.*;




public class CreerLivre extends AppCompatActivity implements OnClickListener  {

    public static int id;
    public static String Auteur;
    public static String Titre;
    public static String Isbn;
    public String editeur,nature ,genre ,resume,commentaire;
    public EditText editText1,editText2,editText3,editText4,editText5,editText6,editText7,editText8;
    public Button Scan,web ;
    private ImageView Image;
    public byte[] image;
    private Bitmap bit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creer_livre);

         editText1 = (EditText)findViewById(R.id.auteurC);
         editText2 = (EditText)findViewById(R.id.titreC);
         editText3 = (EditText)findViewById(R.id.editeurC);
         editText4 = (EditText)findViewById(R.id.natureC);
         editText5 = (EditText)findViewById(R.id.genreC);
         editText6 = (EditText)findViewById(R.id.resumeC);
         editText7 = (EditText)findViewById(R.id.commenatireC);
         editText8 = (EditText)findViewById(R.id.isbnC);
         Scan = (Button)findViewById(R.id.scan);
        Image = (ImageView)findViewById(R.id.thumb);
        web = (Button)findViewById(R.id.web);
        web.setOnClickListener(this);
        Scan.setOnClickListener(this);
    }


    public void onClick(View v){
        if(v.getId()==R.id.scan){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }else if(v.getId()==R.id.web){
            //get the url tag
            String tag = (String)v.getTag();
            //launch the url
            Intent webIntent = new Intent(Intent.ACTION_VIEW);
            webIntent.setData(Uri.parse(tag));
            startActivity(webIntent);
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            if(scanContent!=null && scanFormat!=null && scanFormat.equalsIgnoreCase("EAN_13")){
                //book search
                editText8.setText(scanContent);
                String bookSearchString = "https://www.googleapis.com/books/v1/volumes?"+
                        "q=isbn:"+scanContent;
                new GetBookInfo().execute(bookSearchString);

            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Not a valid scan!", Toast.LENGTH_SHORT);
                toast.show();
            }
                /* return le format de code bare*/
            //formatTxt.setText("FORMAT: " + scanFormat);
                /* return le numero de code bar*/
            //contentTxt.setText("CONTENT: " + scanContent);
        }else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private class GetBookInfo extends AsyncTask<String, Void, String> {
        //fetch book info

        protected String doInBackground(String... bookURLs) {
            //request book info
            StringBuilder bookBuilder = new StringBuilder();
            for (String bookSearchURL : bookURLs) {
                //search urls (client HTTP pour demander les données)
                HttpClient bookClient = new DefaultHttpClient();
                try {
                    //get the data
                    HttpGet bookGet = new HttpGet(bookSearchURL);
                    HttpResponse bookResponse = bookClient.execute(bookGet);
                    StatusLine bookSearchStatus = bookResponse.getStatusLine();
                    if (bookSearchStatus.getStatusCode()==200) {
                        //we have a result
                        HttpEntity bookEntity = bookResponse.getEntity();
                        InputStream bookContent = bookEntity.getContent();
                        InputStreamReader bookInput = new InputStreamReader(bookContent);
                        BufferedReader bookReader = new BufferedReader(bookInput);
                        String lineIn;
                        while ((lineIn=bookReader.readLine())!=null) {
                            bookBuilder.append(lineIn);
                        }

                    }

                }
                catch(Exception e){ e.printStackTrace(); }

            }
            //chaine returner de type json
            return bookBuilder.toString();
        }
        protected void onPostExecute(String result) {
            //parse search results
            try{
                //parse results

                JSONObject resultObject = new JSONObject(result);
                JSONArray bookArray = resultObject.getJSONArray("items");
                JSONObject bookObject = bookArray.getJSONObject(0);
                JSONObject volumeObject = bookObject.getJSONObject("volumeInfo");

                //titre
                try{
                    String res = volumeObject.getString("title").substring(1).toLowerCase();
                    editText2.setText(res.replaceAll("\""," ")); }
                catch(JSONException jse){
                    editText2.setText("");
                    jse.printStackTrace();
                }

                // description
                try{ editText6.setText(volumeObject.getString("description")); }
                catch(JSONException jse){
                    editText6.setText("");
                    jse.printStackTrace();
                }

                // auteur
                StringBuilder authorBuild = new StringBuilder("");
                try{
                    JSONArray authorArray = volumeObject.getJSONArray("authors");
                    for(int a=0; a<authorArray.length(); a++){
                        if(a>0) authorBuild.append(", ");
                        authorBuild.append(authorArray.getString(a));
                    }
                    editText1.setText(authorBuild.toString());
                }
                catch(JSONException jse){
                    editText1.setText("");
                    jse.printStackTrace();
                }



                //editor
                try{ editText3.setText(volumeObject.getString("publisher")); }
                catch(JSONException jse){
                    editText3.setText("");
                    jse.printStackTrace();
                }


                try{
                    web.setTag(volumeObject.getString("infoLink"));
                    web.setVisibility(View.VISIBLE);
                }
                catch(JSONException jse){
                    web.setVisibility(View.GONE);
                    jse.printStackTrace();
                }

                try{
                    JSONObject imageInfo = volumeObject.getJSONObject("imageLinks");
                    new GetBookThumb().execute(imageInfo.getString("smallThumbnail"));
                }
                catch(JSONException jse){
                    Image.setImageBitmap(null);
                    jse.printStackTrace();
                }

            }catch (Exception e) {
                //no result
                e.printStackTrace();
                editText2.setText("NOT FOUND");
                editText1.setText("");
                editText6.setText("");
                Image.setImageBitmap(null);
                web.setVisibility(View.GONE);
            }



        }

    }

    private class GetBookThumb extends AsyncTask<String, Void, String> {

        private Bitmap thumbImg;

        //get thumbnail
        @Override
        protected String doInBackground(String... thumbURLs) {
            //attempt to download image
            try{
                //try to download
                URL thumbURL = new URL(thumbURLs[0]);
                URLConnection thumbConn = thumbURL.openConnection();
                thumbConn.connect();
                InputStream thumbIn = thumbConn.getInputStream();
                BufferedInputStream thumbBuff = new BufferedInputStream(thumbIn);
                thumbImg = BitmapFactory.decodeStream(thumbBuff);
                bit = thumbImg;
                thumbBuff.close();
                thumbIn.close();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            return "";
        }
        //pour afficher image telecharger
        protected void onPostExecute(String result) {
            Image.setImageBitmap(thumbImg);
        }
    }

    /* créeer livre  */
    public void valider(View view){

        Auteur = editText1.getText().toString();
        Titre = editText2.getText().toString();
        editeur = editText3.getText().toString();
        nature = editText4.getText().toString();
        genre = editText5.getText().toString();
        resume = editText6.getText().toString();
        commentaire = editText7.getText().toString();
        Isbn = editText8.getText().toString();
        //image = DbBitmapUtility.getBytes(bit);

       String img = "img";

         /* création d'une instance de la classe GestionDB */
        GestionDB gestionDB = new GestionDB(this);
        /* on ouvre la base de données pour écrire dedans */
        gestionDB.open();

        // Book livre = new Book(Isbn,Titre,Auteur);
        String[] texte = Auteur.split(",");
        //.compreTo("")
        if(Titre.compareTo("") != 0){
            for(int i=0;i<texte.length;i++) {
                Book test = new Book(editeur, Titre, nature, genre, Isbn, resume, commentaire, "");


                    if (test != null )
                    {
                        //Book.id++;
                        gestionDB.createLivre(texte[i],editeur, test);
                    }
                }
            Intent intent = new Intent(CreerLivre.this,webpageListActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"le titre vide !!!", Toast.LENGTH_SHORT).show();
        }
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");


    }

    public void clear(View view){
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        editText4.setText("");
        editText5.setText("");
        editText6.setText("");
        editText7.setText("");
        editText8.setText("");
        Image.setImageResource(0);
    }
}

