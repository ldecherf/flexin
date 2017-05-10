package com.crystal.flexin.activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.crystal.flexin.R;
import com.crystal.flexin.manager.MaterielManager;
import com.crystal.flexin.manager.UserManager;
import com.crystal.flexin.resources.InfoSession;
import com.crystal.flexin.resources.Personne;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class HomeActivity extends Activity {

    //beaucoup de changement a verifier :/

   // private ImageView searchScanButton;
   // private ImageView searchNfcButton;
    private String usertag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent() ;
        this.usertag = intent.getStringExtra("usertag");

        init();
    }

    public void init(){
        final UserManager userManager= new UserManager(this.usertag);
        userManager.getPersonne(new UserManager.GetPesonneCallBack() {

            @Override
            public void onSuccess(Personne[] personne) {

                InfoSession infoSession = new InfoSession(personne[0].nom,personne[0].mail,personne[0].tel);
                //Fais ce que tu veux faire avec les infos de la personne connecte ici, pour creer un session

                ImageView searchScanButton = (ImageView) findViewById(R.id.searchScanButton);
                searchScanButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        IntentIntegrator scanIntegrator = new IntentIntegrator(HomeActivity.this);
                        scanIntegrator.initiateScan();

                    }
                });

                ImageView searchNfcButton = (ImageView) findViewById(R.id.searchNfcButton);
                searchNfcButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), TagViewerActivity.class);
                        intent.putExtra("forEmprunt", false);
                        startActivity(intent);
                        finish();
                    }
                });
            }

            @Override
            public void onFail() {
                System.err.println("get Personne failed .. Callback failure in HomeActivity");
            }
        });
    }

    /*
    private void init(){



    }*/

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve result of scanning - instantiate ZXing object
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        //check we have a valid result
        if (scanningResult != null) {
            //get content from Intent Result
            String scanContent = scanningResult.getContents();
            //get format name of data scanned
            String scanFormat = scanningResult.getFormatName();
            if (scanContent != null && scanFormat != null) {

                Intent intent2 = new Intent(HomeActivity.this, MaterielActivity.class);
                Log.e("1212", scanContent);
                intent2.putExtra(MaterielActivity.TAG, scanContent);
                startActivity(intent2);
                finish();
            }
        }
    }



}
