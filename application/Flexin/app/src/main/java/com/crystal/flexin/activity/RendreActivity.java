package com.crystal.flexin.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crystal.flexin.R;
import com.crystal.flexin.manager.MaterielManager;

import org.w3c.dom.Text;

public class RendreActivity extends AppCompatActivity {

    private String id_materiel ;
    private TextView rendreEtatEditText;
    private Button rendreEtatButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendre);

        Intent intent = getIntent();
        this.id_materiel = intent.getStringExtra(EmpruntActivity.ID_MATERIEL);
        rendreEtatEditText = (TextView) findViewById(R.id.rendreEtatEditText);
        rendreEtatButton = (Button) findViewById(R.id.rendreEtatButton);

        rendreEtatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String etat = rendreEtatEditText.getText().toString();

                if(!etat.equals("")){
                    Log.e("id_materiel", "RENDRE MATERIEL ACTION LISTENER"+id_materiel);

                    rendreMateriel(id_materiel ,etat);

                    Intent intent = new Intent(RendreActivity.this, HomeActivity.class);
                    startActivity(intent);


                }else{

                    Toast.makeText(v.getContext(), R.string.etatNotFilled, Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public void rendreMateriel(String id,String etat){
        Log.e("id_materiel", "RENDRE MATERIEL RENDRE ACTIVITY "+this.id_materiel);
        final MaterielManager materielManager = new MaterielManager(id, etat);
        materielManager.rendreMateriel(new MaterielManager.RendreMaterielCallBack() {
            @Override
            public void onSuccess(final String msg) {



            }

            @Override
            public void onFail() {
                System.err.println("Emprunter materiel failed .. Callback failure in EmpruntActivity");
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

}
