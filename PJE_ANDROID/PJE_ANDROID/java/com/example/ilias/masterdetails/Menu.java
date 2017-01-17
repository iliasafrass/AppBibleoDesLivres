
package com.example.ilias.masterdetails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by ilias on 16/11/2016.
 */

public class Menu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        Button btn1 = (Button)findViewById(R.id.button_creerLivre);
        Button btn2 = (Button)findViewById(R.id.button_visualiserLivre);
        Button btn3 = (Button)findViewById(R.id.button_rechercher);
        Button btn4 = (Button)findViewById(R.id.button_SupprimerLivre);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, CreerLivre.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this,webpageListActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this,Rechercher_livre.class);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this,SuprimerLivre.class);
                startActivity(intent);
            }
        });

    }
}
