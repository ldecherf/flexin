package com.crystal.flexin.manager;

import android.icu.util.GregorianCalendar;

import com.crystal.flexin.activity.MaterielActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EmpruntManager {

    private String id_materiel ;
    private String etat_emprunt ;
    private String id_emprunteur ;
    private String date;

    public EmpruntManager(String id_materiel, String etat_emprunt, String id_emprunteur, GregorianCalendar date){
        this.id_materiel = id_materiel ;
        this.etat_emprunt = etat_emprunt ;
        this.id_emprunteur = id_emprunteur ;
        this.date = date.getTimeInMillis()+"";
    }

    public final void emprunterMateriel(final EmprunterMaterielCallBack emprunterMaterielCallBack){
        OkHttpClient client = new OkHttpClient();
        Request requestCreerEmprunt = new Request.Builder().url(MaterielActivity.URL + "empruntcreer/"+this.id_materiel+"/"+this.etat_emprunt+"/"+this.id_emprunteur+"/"+this.date).build();
        Request requestMajDisp = new Request.Builder().url(MaterielActivity.URL + "empruntdisp/"+this.id_materiel).build();

        client.newCall(requestCreerEmprunt).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.err.println("Emprunter materiel failed ..");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                emprunterMaterielCallBack.onSuccess();
            }
        });

        client.newCall(requestMajDisp).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.err.println("Mise a jour disponibilite emprunt failed ");

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                emprunterMaterielCallBack.onSuccess();

            }
        });


    }


    public interface EmprunterMaterielCallBack {
        public void onSuccess();
        public void onFail();
    }

}
