package com.crystal.flexin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.crystal.flexin.R;

public class NFCSearchActivity extends HomeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_search);

        Intent intent = getIntent();
        //ce message est recu a partir de l'activite TagViewer method resolveIntent()
        String message = intent.getStringExtra("tag");
        TextView tagId = (TextView) findViewById(R.id.id);
        tagId.setText(message);
        //jusqu'ici j'ai juste pris le contenu du tag et l'affiche
        //ce qu'il reste a faire c'est de l'envoyer dans une requete
        //pour recuperer toutes les infos qui lui correspondent
        //et afficher le tout sur cette activite

    }


}
