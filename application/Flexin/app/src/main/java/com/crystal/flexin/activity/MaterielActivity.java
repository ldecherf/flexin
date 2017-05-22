package com.crystal.flexin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.crystal.flexin.R;
import com.crystal.flexin.manager.MaterielManager;
import com.crystal.flexin.resources.Materiel;

public class MaterielActivity extends HomeActivity {

    public static final String TAG = "tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String message = intent.getStringExtra(TAG);

        displayMateriel(message);


    }


    public void displayMateriel(final String id){
        final MaterielManager materielManager = new MaterielManager(id);
        materielManager.getMateriel(new MaterielManager.GetMaterielCallBack() {
            @Override
            public void onSuccess(final Materiel[] materiels) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        if(materiels.length > 0) {
                            setContentView(R.layout.activity_materiel);
                            materielManager.setMateriel(materiels[0]);
                            TextView tagId = (TextView) findViewById(R.id.reference);
                            TextView nomView = (TextView) findViewById(R.id.nom);
                            TextView descripionView = (TextView) findViewById(R.id.description);
                            TextView platformeView = (TextView) findViewById(R.id.platforme);
                            TextView etatView = (TextView) findViewById(R.id.etat);


                            tagId.setText(id);
                            nomView.setText(materielManager.getMateriel().nom);
                            descripionView.setText(materielManager.getMateriel().description);
                            platformeView.setText(materielManager.getMateriel().plateforme);
                            etatView.setText(materielManager.getMateriel().etat);


                            Button changeStateButton = (Button) findViewById(R.id.changeStateButton);
                            if(materiels[0].disponibilite.equals("disponible")){


                                changeStateButton.setText(R.string.emprunter);
                                changeStateButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        //TODO: refaire ça sans scan
                                        /*TextView id_materielView = (TextView) findViewById(R.id.id);
                                        TextView etatView = (TextView) findViewById(R.id.etatLabel);
                                        Intent intent = new Intent(v.getContext(), TagViewerActivity.class);
                                        intent.putExtra("forEmprunt", true);
                                        intent.putExtra("id_materiel",id_materielView.getText());
                                        intent.putExtra("etat_emprunt",etatView.getText());
                                        startActivity(intent);
                                        finish();*/
                                    }
                                });


                            } else{

                                changeStateButton.setText(R.string.rendre);
                                changeStateButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent intent = new Intent(v.getContext(),RendreActivity.class);
                                        intent.putExtra(EmpruntActivity.ID_MATERIEL, id);
                                        startActivity(intent);
                                        finish();
                                    }
                                });


                            }




                        }else{

                            Intent intent = new Intent(MaterielActivity.this, HomeActivity.class);
                            Toast.makeText(MaterielActivity.this, R.string.materielNotFoundError, Toast.LENGTH_SHORT);
                            startActivity(intent);
                            finish();
                        }
                    }
                });

            }

            @Override
            public void onFail() {
                Toast.makeText(getApplicationContext(), R.string.materielNotFoundError, Toast.LENGTH_SHORT);
                Intent intent = new Intent(MaterielActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, getApplicationContext());

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MaterielActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }


}
