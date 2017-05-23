package com.crystal.flexin.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.crystal.flexin.R;
import com.crystal.flexin.manager.UserManager;

public class ConnectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUser();
        setContentView(R.layout.activity_connection);
        Button connexion = (Button) findViewById(R.id.connexion);
        Button anonymous = (Button) findViewById(R.id.anonymous);

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),TagViewerActivity.class);
                intent.putExtra(TagViewerActivity.FORCONNEXION,true);
                startActivity(intent);
                finish();
            }
        });

        anonymous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initUser(){

        UserManager userManager = new UserManager(getApplicationContext());
        if(userManager.existsUser()){

            Intent intent = new Intent(ConnectionActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
