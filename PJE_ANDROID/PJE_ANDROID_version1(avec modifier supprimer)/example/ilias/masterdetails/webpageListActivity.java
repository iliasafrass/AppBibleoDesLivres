package com.example.ilias.masterdetails;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ilias.masterdetails.dummy.DummyContent;

import java.util.List;

import static com.example.ilias.masterdetails.dummy.DummyContent.ITEMS;
import static com.example.ilias.masterdetails.dummy.DummyContent.addItem;
import android.support.v4.app.DialogFragment;
import android.R.attr.*;
import android.R.attr;

/**
 * An activity representing a list of webpages. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link webpageDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
import android.widget.*;
import android.support.v4.app.*;
public class webpageListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    public static String text;
    private boolean mTwoPane;

    /* création d'une instance de la classe GestionDB */
    private GestionDB gestionDB;
    Cursor cursor_livre,cursor_livre1,cursor_auteur,cursor_editeur;
    int nblignes_livre = 0,nblignes_auteur = 0,nblignes_editeur = 0;
    FragmentManager fm = getSupportFragmentManager();
    Button dimis;

    @Override
    protected void onResume() {
        gestionDB.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        gestionDB.close();
        ITEMS.clear();
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void restoreMe(Bundle state){
        if(state != null){
            onRestoreInstanceState(state);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webpage_list);
        restoreMe(savedInstanceState);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Choisie un livre", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        View recyclerView = findViewById(R.id.webpage_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.webpage_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }



        gestionDB = new GestionDB(this);
        gestionDB.open();

        cursor_livre = gestionDB.getDataBase_Livre();
        cursor_livre1 = gestionDB.getTableTitre();

        cursor_auteur = gestionDB.getDataBase_Auteur();
        cursor_editeur = gestionDB.getDataBase_Editeur();


        nblignes_livre = cursor_auteur.getCount();
        nblignes_auteur = cursor_auteur.getCount();
        nblignes_editeur = cursor_editeur.getCount();
int t =0;
        int nb = cursor_livre1.getCount();
        cursor_livre1.moveToFirst();
            while (t<nb) {

                String nom_editeur = cursor_livre1.getString(2);


                //Cursor c1 = gestionDB.getAuteurWithId(cursor_livre1.getLong(1));
                //c1.moveToFirst();
                String nom_auteur  ="";// c1.getString(1);

                Cursor idAuteur= gestionDB.getIdAuteurWithTitre(cursor_livre1.getString(3));
                idAuteur.moveToFirst();
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


               // Toast.makeText(this,""+nom_auteur,Toast.LENGTH_LONG).show();

                //Toast.makeText(this,"auteur : "+nom_auteur+" editeur : "+nom_editeur,Toast.LENGTH_LONG).show();

                addItem(new DummyContent.DummyItem(
                        ""+(t+1),cursor_livre1.getString(3),
                        nom_auteur,
                        cursor_livre1.getString(6),
                        nom_editeur,cursor_livre1.getString(4),
                        cursor_livre1.getString(5),cursor_livre1.getString(7),cursor_livre1.getString(8)
                ));
                t++;
                cursor_livre1.moveToNext();
            }
        if(nblignes_livre == 0 || nblignes_auteur == 0 || nblignes_editeur == 0)
            Toast.makeText(this,"aucun livre n'existe  dans la base de données",Toast.LENGTH_LONG).show();

    }




    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.ITEMS));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<DummyContent.DummyItem> mValues;

        public SimpleItemRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.webpage_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).titre);


            //holder.img.setImageBitmap(DbBitmapUtility.getImage());
            holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    DFragment dfragment= new DFragment();

                    //on vas visualiser notre dialog
                    text = holder.afficher();
                    dfragment.show(fm,"gerer livre");


                    return true;
                }
            });
           holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(webpageDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        webpageDetailFragment fragment = new webpageDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.webpage_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, webpageDetailActivity.class);
                        intent.putExtra(webpageDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public final ImageView img;
            public DummyContent.DummyItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
                img = (ImageView)view.findViewById(R.id.thumb);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
            public String afficher(){
                return mItem.titre;
            }
        }
    }
}
