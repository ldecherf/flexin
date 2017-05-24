package com.crystal.flexin.activity;

import android.content.Intent;
import android.icu.util.GregorianCalendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.crystal.flexin.R;
import com.crystal.flexin.manager.EmpruntManager;
import com.crystal.flexin.manager.MaterielManager;
import com.crystal.flexin.manager.RendreManager;
import com.crystal.flexin.manager.UserManager;
import com.crystal.flexin.resources.Emprunt;
import com.crystal.flexin.resources.Materiel;
import com.crystal.flexin.resources.User;

public class MaterielActivity extends AppCompatActivity {

    public static String URL = "http://192.168.43.34:8080/";


    public static final String TAG = "tag";
    private String tag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        tag = intent.getStringExtra(TAG);

        displayMateriel(tag);


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
                            TextView platformeView = (TextView) findViewById(R.id.platforme);
                            TextView etatView = (TextView) findViewById(R.id.etat);
                            final Materiel materiel;

                            materiel = materielManager.getMateriel();
                            tagId.setText(id);
                            nomView.setText(materiel.nom);
                            platformeView.setText(materiel.plateforme);
                            etatView.setText(materiel.etat);


                            final Button changeStateButton = (Button) findViewById(R.id.changeStateButton);
                            if(materiels[0].disponibilite.equals("disponible")){

                                final UserManager userManager =  new UserManager(getApplicationContext());
                                if(userManager.existsUser()) {

                                changeStateButton.setText(R.string.emprunter);
                                    changeStateButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            User user = userManager.getUser();
                                            emprunterMateriel( id, materiel.etat , user.getId(), new GregorianCalendar());

                                        }
                                    });
                                }else
                                    changeStateButton.setVisibility(View.GONE);


                            }else{

                                final UserManager userManager =  new UserManager(getApplicationContext());
                                if(userManager.existsUser()) {

                                    RendreManager rendreManager = new RendreManager();
                                    rendreManager.getIdEmprunt(id, userManager.getUser().getId(), new RendreManager.IdEmpruntFetchCallBack() {
                                        @Override
                                        public void onSuccess(final Emprunt[] emprunts) {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    boolean isEmprunteur = false;
                                                    isEmprunteur = (emprunts.length > 0);
                                                    if(isEmprunteur) {

                                                        changeStateButton.setText(R.string.rendre);
                                                        changeStateButton.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {

                                                                Intent intent = new Intent(v.getContext(), RendreActivity.class);
                                                                intent.putExtra(RendreActivity.ID_MATERIEL, id);
                                                                startActivity(intent);
                                                                finish();
                                                            }
                                                        });
                                                    }else{

                                                        changeStateButton.setVisibility(View.GONE);

                                                    }

                                                }
                                            });
                                        }

                                        @Override
                                        public void onFail() {

                                        }
                                    });


                                }else
                                    changeStateButton.setVisibility(View.GONE);
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


    public void emprunterMateriel(String id_materiel, String etat_emprunt, String id_emprunteur, GregorianCalendar date){
        final EmpruntManager empruntManager = new EmpruntManager(id_materiel, etat_emprunt, id_emprunteur, date);
        empruntManager.emprunterMateriel(new EmpruntManager.EmprunterMaterielCallBack() {
            @Override
            public void onSuccess() {

                Intent intent = new Intent(getApplicationContext(), MaterielActivity.class);
                intent.putExtra(MaterielActivity.TAG, tag);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFail() {

                Toast.makeText(getApplicationContext(), R.string.errorConnection, Toast.LENGTH_SHORT).show();
            }
        });
    }





    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MaterielActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem userNameMenuItem = menu.findItem(R.id.username);
        MenuItem connexionStateMenuItem = menu.findItem(R.id.connexionState);
        UserManager userManager = new UserManager(getApplicationContext());

        if(userManager.existsUser()) {


            User user = userManager.getUser();
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
                UserManager userManager = new UserManager(getApplicationContext());


                if (userManager.existsUser()) {

                    userManager.unSetUser();
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
