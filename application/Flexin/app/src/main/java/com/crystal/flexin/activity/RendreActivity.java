package com.crystal.flexin.activity;

import android.app.Activity;
import android.content.Intent;
import android.icu.util.GregorianCalendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crystal.flexin.R;
import com.crystal.flexin.manager.MaterielManager;
import com.crystal.flexin.manager.RendreManager;
import com.crystal.flexin.manager.UserManager;
import com.crystal.flexin.resources.Emprunt;
import com.crystal.flexin.resources.User;

import org.w3c.dom.Text;

public class RendreActivity extends AppCompatActivity {

    public static final String ID_MATERIEL = "id_materiel";
    private String id_materiel ;
    private TextView rendreEtatEditText;
    private Button rendreEtatButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendre);

        Intent intent = getIntent();
        this.id_materiel = intent.getStringExtra(RendreActivity.ID_MATERIEL);
        rendreEtatEditText = (TextView) findViewById(R.id.rendreEtatEditText);
        rendreEtatButton = (Button) findViewById(R.id.rendreEtatButton);

        rendreEtatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String etat = rendreEtatEditText.getText().toString();

                if(!etat.equals("")){

                    UserManager userManager = new UserManager(getApplicationContext());
                    User user = userManager.getUser();
                    rendreMateriel(etat, new GregorianCalendar(), user.getId());
                    Intent intent = new Intent(RendreActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();


                }else{

                    Toast.makeText(v.getContext(), R.string.etatNotFilled, Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public void rendreMateriel(final String etat_emprunt, final GregorianCalendar date_retour, String id_emprunteur){
        final RendreManager rendreManager = new RendreManager();
        rendreManager.getIdEmprunt(id_materiel, id_emprunteur, new RendreManager.IdEmpruntFetchCallBack() {
            @Override
            public void onSuccess(Emprunt[] emprunts) {
                Emprunt emprunt = emprunts[0];
                rendreManager.rendreMateriel(id_materiel, etat_emprunt, emprunt.id, date_retour.getTimeInMillis() + "", new RendreManager.RendreMaterielCallBack() {
                    @Override
                    public void onSuccess() {

                        Intent intent = new Intent(getApplicationContext(), MaterielActivity.class);
                        intent.putExtra(MaterielActivity.TAG, id_materiel);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFail() {

                    }
                });

            }

            @Override
            public void onFail() {

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RendreActivity.this, MaterielActivity.class);
        intent.putExtra(MaterielActivity.TAG, this.id_materiel);
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
