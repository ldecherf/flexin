package com.crystal.flexin.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.crystal.flexin.R;
import com.crystal.flexin.data.Materiel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
        //displayMateriel();
        Button emprunterbtn = (Button) findViewById(R.id.emprunterbtn);
        emprunterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    public void displayMateriel(){
        final MaterielBis materielBis = new MaterielBis();
        materielBis.getMateriel(new MaterielBis.getMaterielCallBack() {
            @Override
            public void onSuccess(Materiel materiel) {
                materielBis.setMateriel(materiel);
                TextView etatView = (TextView) findViewById(R.id.etat);
                etatView.setText(materielBis.getMateriel().etat);
                TextView descripionView = (TextView) findViewById(R.id.description);
                descripionView.setText(materielBis.getMateriel().description);
                TextView platformeView = (TextView) findViewById(R.id.platforme);
                platformeView.setText(materielBis.getMateriel().plateforme);
                TextView referenceView = (TextView) findViewById(R.id.reference);
                referenceView.setText(materielBis.getMateriel().reference);
                TextView nomView = (TextView) findViewById(R.id.nom);
                nomView.setText(materielBis.getMateriel().nom);
            }

            @Override
            public void onFail() {
                System.err.println("Getting materiel failed .. Callback failure in GetMaterielTask");
            }
        });

    }





}
