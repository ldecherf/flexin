package com.crystal.flexin.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.crystal.flexin.R;

public class ConnectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        Button connexion = (Button) findViewById(R.id.connexion);
        Button anonymous = (Button) findViewById(R.id.anonymous);
        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),TagViewerActivity.class);
                intent.putExtra("forConnexion",true);
                startActivity(intent);
            }
        });
        anonymous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),HomeActivity.class);
                startActivity(intent);
            }
        });
    }

}
