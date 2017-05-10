package com.crystal.flexin.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.crystal.flexin.R;
import com.crystal.flexin.manager.MaterielManager;

public class RendreActivity extends AppCompatActivity {

    private String id_materiel ;
    private String etat ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendre);
        Intent intent = getIntent();
        this.id_materiel = intent.getStringExtra("id_materiel");
        this.etat = ((EditText) findViewById(R.id.rendre_etat)).getText().toString();
        rendreMateriel();
    }

    public void rendreMateriel(){
        final MaterielManager materielManager = new MaterielManager(this.id_materiel,this.etat);
        materielManager.rendreMateriel(new MaterielManager.RendreMaterielCallBack() {
            @Override
            public void onSuccess(final String msg) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ((EditText) findViewById(R.id.rendre_etat)).setVisibility(View.GONE);
                        ((Button) findViewById(R.id.rendrebtn)).setVisibility(View.GONE);
                        ((TextView) findViewById(R.id.rendremsg)).setText(msg);

                    }
                });
            }

            @Override
            public void onFail() {
                System.err.println("Emprunter meteriel failed .. Callback failure in EmpruntActivity");
            }
        });
    }
}
