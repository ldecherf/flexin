package com.crystal.flexin.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.crystal.flexin.R;
import com.crystal.flexin.manager.UserManager;
import com.crystal.flexin.resources.User;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class HomeActivity extends AppCompatActivity {

    private String usertag;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.userManager = new UserManager(getApplicationContext());
        Intent intent = getIntent() ;
        this.usertag = intent.getStringExtra("usertag");
        this.userManager.setUser(usertag, this);
        setContentView(R.layout.activity_home);

        updateView();


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


    public void updateView() {

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





        User user;
        if(this.userManager.existsUser()){

            user = this.userManager.getUser();
            TextView text = (TextView) findViewById(R.id.welcomeMessageTextView);
            text.setText("Welcome " + user.getFirstName() + " " + user.getName());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem userNameMenuItem = menu.findItem(R.id.username);
        MenuItem connexionStateMenuItem = menu.findItem(R.id.connexionState);


        if(this.userManager.existsUser()) {


            User user = this.userManager.getUser();
            userNameMenuItem.setTitle(user.getFirstName() + " " + user.getName());
            connexionStateMenuItem.setTitle(R.string.changeconnect);


        }else {

            userNameMenuItem.setTitle(R.string.guest);
            connexionStateMenuItem.setTitle(R.string.connect);

        }


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.connexionState:{

                if (this.userManager.existsUser()) {

                    this.userManager.unSetUser();
                }
                Intent intent = new Intent(getApplicationContext(), ConnectionActivity.class);
                startActivity(intent);
                finish();

                return true;
            }
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}




