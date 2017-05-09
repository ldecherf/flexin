package com.crystal.flexin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.crystal.flexin.R;
import com.crystal.flexin.TagViewer;
import com.crystal.flexin.resources.Materiel;

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

        //displayMateriel();
        Button emprunterbtn = (Button) findViewById(R.id.emprunterbtn);
        emprunterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView id_materielView = (TextView) findViewById(R.id.id);
                TextView etatView = (TextView) findViewById(R.id.etat);
                Intent intent = new Intent(v.getContext(), TagViewer.class);
                intent.putExtra("forEmprunt", true);
                intent.putExtra("id_materiel",id_materielView.getText());
                intent.putExtra("etat_emprunt",etatView.getText());
                startActivity(intent);
            }
        });

    }


    public void displayMateriel(){
        final MaterielBis materielBis = new MaterielBis();
        materielBis.getMateriel(new MaterielBis.GetMaterielCallBack() {
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
