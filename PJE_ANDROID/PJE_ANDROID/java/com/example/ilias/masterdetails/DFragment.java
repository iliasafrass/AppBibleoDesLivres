package com.example.ilias.masterdetails;

/**
 * Created by abk on 11/12/2016.
 */

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.DialogFragment;
import android.app.*;
import android.content.*;
import android.widget.*;



public class DFragment extends DialogFragment implements View.OnClickListener{

    public Context ct =getContext();
    public Button add,del;
    public GestionDB gestionDB;

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.dialogfragment,null);
        gestionDB = new GestionDB(getActivity());
        add   = (Button)view.findViewById(R.id.add);
        del = (Button)view.findViewById(R.id.delet);
        add.setOnClickListener(this);
        del.setOnClickListener(this);
        setCancelable(true  );
        return view;
    }


    @Override
    public void onClick(View view) {
        gestionDB.open();
        if(view.getId() == R.id.add){
            Intent in = new Intent(getActivity(),ModifierLivre.class);
            startActivity(in);
            Toast.makeText(getActivity(),"button add clicker",Toast.LENGTH_LONG).show();
        }else if(view.getId() == R.id.delet){

            String res =  webpageListActivity.text;
           gestionDB.suprimerLivreWithTitre(res);

            Toast.makeText(getActivity(),res,Toast.LENGTH_LONG).show();
            Intent in = new Intent(getActivity(),webpageListActivity.class);
            startActivity(in);
            Intent intent = new Intent(getActivity(),webpageListActivity.class);
            startActivity(intent);
        }
    }


}
