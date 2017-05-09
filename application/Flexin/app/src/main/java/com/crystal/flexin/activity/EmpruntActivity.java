package com.crystal.flexin.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.crystal.flexin.R;
import com.crystal.flexin.manager.MaterielManager;

public class EmpruntActivity extends AppCompatActivity {

    private String id ;
    private String id_materiel ;
    private String etat_emprunt ;
    private String id_emprunteur ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emprunt);

        Intent intent = getIntent() ;
        this.id = intent.getStringExtra("id_emprunt");
        this.id_materiel = intent.getStringExtra("id_materiel");
        this.etat_emprunt = intent.getStringExtra("etat_emprunt");
        this.id_emprunteur = intent.getStringExtra("id_emprunteur");

        emprunterMateriel();

    }

    public void emprunterMateriel(){
        final MaterielManager materielManager = new MaterielManager(this.id,this.id_materiel,this.etat_emprunt,this.id_emprunteur);
        materielManager.emprunterMateriel(new MaterielManager.EmprunterMaterielCallBack() {
            @Override
            public void onSuccess(final String msg) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        TextView msg_empruntView = (TextView) findViewById(R.id.msg_emprunt);
                        msg_empruntView.setText(msg);
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
