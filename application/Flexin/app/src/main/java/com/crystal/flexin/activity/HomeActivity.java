package com.crystal.flexin.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.crystal.flexin.R;
import com.crystal.flexin.manager.UserManager;
import com.crystal.flexin.resources.User;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class HomeActivity extends Activity {

    //beaucoup de changement a verifier :/
    private String usertag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageButton searchScanButton = (ImageButton) findViewById(R.id.searchScanButton);
        ImageButton searchNfcButton = (ImageButton) findViewById(R.id.searchNfcButton);

        searchScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator scanIntegrator = new IntentIntegrator(HomeActivity.this);
                scanIntegrator.initiateScan();
            }
        });

        searchNfcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TagViewerActivity.class);
                intent.putExtra(TagViewerActivity.FOREMPRUNT, false);
                startActivity(intent);
                finish();
            }
        });


        //Intent intent = getIntent() ;
        //this.usertag = intent.getStringExtra("usertag");
        /*
        UserManager userManager = new UserManager(getApplicationContext());

        userManager.setUser(usertag);

        User user;
        if(userManager.existsUser()){

            user = userManager.getUser();
            TextView text = (TextView) findViewById(R.id.welcomeMessageTextView);
            text.setText("Welcome " + user.getFirstName() + " " + user.getName());
        }
        */


    }

    public void init(){
     /*   final UserManager userManager= new UserManager(getApplicationContext());
        userManager.getUser(new UserManager.FetchUserCallBack() {

            @Override
            public void onSuccess(User[] personne) {

            }

            @Override
            public void onFail() {
                System.err.println("get User failed .. Callback failure in HomeActivity");
            }
        });*/
    }


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
                intent2.putExtra(MaterielActivity.TAG, scanContent);
                startActivity(intent2);
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}




