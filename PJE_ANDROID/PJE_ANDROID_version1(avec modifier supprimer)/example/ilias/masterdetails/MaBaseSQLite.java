package com.example.ilias.masterdetails;

/**
 * Created by ilias on 16/11/2016.
 */


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MaBaseSQLite extends SQLiteOpenHelper
{
    public static final String TABLE_LIVRES = "table_livres";
    public static final String TABLE_AUTEUR = "table_auteur";
    public static final String TABLE_EDITEUR = "table_editeur";
    public static final String TABLE_COLLECTION = "table_collection";

    public static final String COL_ID_LIVRE = "ID_LIVRE";
    public static final String COL_ID_AUTEUR = "ID_AUTEUR";
    public static final String COL_TITRE = "Titre_t";
    //public static final String COL_ID_COLLECTION = "ID_COLLECTION";
    public static final String COL_NATURE = "NATURE";
    public static final String COL_GENRE = "GENRE";
    public static final String COL_ISBN = "ISBN";
    public static final String COL_RESUME = "RESUME";
    public static final String COL_COMMENTAIRE = "COMMENTAIRE";
    public static final String COL_IMAGE = "Image";


    public static final String COL_NOM_AUTEUR = "NOM_AUTEUR";

    public static final String COL_NOM_COLLECTION = "NOM_COLLECTION";
    public static final String COL_ID_EDITEUR = "ID_EDITEUR";
    public static final String COL_NOM_EDITEUR = "NOM_EDITEUR";



    private static final String DATABASE_NAME = "bib.db";
    private static final int DATABASE_VERSION = 1;

    // Commande sql pour la création de la base de données
    private static final String SQL_LIVRE = "CREATE TABLE " + TABLE_LIVRES + " ("
            + COL_ID_LIVRE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COL_ID_AUTEUR + " INTEGER, "
            +COL_NOM_EDITEUR+ " TEXT, "
            +COL_TITRE  +" TEXT NOT NULL, "
            //+COL_ID_COLLECTION+" INTEGER, "
            +COL_NATURE+" TEXT, "
            +COL_GENRE+" TEXT, "
            +COL_ISBN + " TEXT, "
            +COL_RESUME+" TEXT, "
            +COL_COMMENTAIRE+" TEXT, "
            +COL_IMAGE+" TEXT);";

    private static final String SQL_EDITEUR = "CREATE TABLE " + TABLE_EDITEUR + "("
            +COL_ID_EDITEUR + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COL_NOM_EDITEUR  +" TEXT);";

    private static final String SQL_AUTEUR = "CREATE TABLE " + TABLE_AUTEUR + "("
            +COL_ID_AUTEUR+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COL_NOM_AUTEUR+" TEXT);";


    /*
    private static final String SQL_COLLECTION = "CREATE TABLE " + TABLE_COLLECTION + " ("
            +COL_ID_COLLECTION+" INETEGER PRIMARY KEY AUTOINCREMENT, "
            +COL_NOM_COLLECTION+" TEXT, "
            +COL_NOM_AUTEUR+" TEXT, "
            +COL_COMMENTAIRE+" TEXT); ";
    */

    public MaBaseSQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SQL_AUTEUR);
        database.execSQL(SQL_EDITEUR);
        database.execSQL(SQL_LIVRE);

        //database.execSQL(SQL_COLLECTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MaBaseSQLite.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUTEUR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EDITEUR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIVRES);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_COLLECTION);
        onCreate(db);
    }
}