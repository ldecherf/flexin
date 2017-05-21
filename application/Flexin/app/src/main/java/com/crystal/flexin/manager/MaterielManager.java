package com.crystal.flexin.manager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.crystal.flexin.R;
import com.crystal.flexin.resources.Materiel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Collection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by aymane on 01/05/17.
 */

public class MaterielManager implements Serializable{

    String URL = "http://192.168.43.34:8080/";

    private Materiel materiel ;
    //id materiel for get materiel et rendre materiel et id emprunt for emprunter
    private String id ;
    private String id_materiel ;
    private String etat_emprunt ;
    private String id_emprunteur ;

    public MaterielManager(String id){
        this.id = id ;
    }

    public MaterielManager(String id, String id_materiel, String etat_emprunt, String id_emprunteur){
        this.id = id ;
        this.id_materiel = id_materiel ;
        this.etat_emprunt = etat_emprunt ;
        this.id_emprunteur = id_emprunteur ;
    }

    public MaterielManager(String id, String etat_emprunt){
        //id materiel
        this.id = id ;
        this.etat_emprunt = etat_emprunt ;

    }
    public void setMateriel(Materiel mat){
        this.materiel = mat ;
    }

    public Materiel getMateriel(){
        return this.materiel;
    }

    //connecter le telephone en localhost ;)

    public final void getMateriel(final GetMaterielCallBack getMaterielCallBack, final Context context){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(URL + "materiel/"+this.id ).build();
        Log.e("1992","blablablalbalalbzlbal : "+this.id);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.err.println("Getting materiel failed .. Callback failure in MatarielBis class");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                Type collectionType = new TypeToken<Collection<Materiel>>(){}.getType();
                Collection<Materiel> enums = gson.fromJson(result, collectionType);
                Materiel[] materiels = enums.toArray(new Materiel[enums.size()]);
                getMaterielCallBack.onSuccess(materiels);

            }
        });
    }

    public interface GetMaterielCallBack {
        public void onSuccess(Materiel[] materiel);
        public void onFail();
    }


    public final void emprunterMateriel(final EmprunterMaterielCallBack emprunterMaterielCallBack){
        OkHttpClient client = new OkHttpClient();
        //System.out.println("ciciciciciciciciicicicicici " + this.id+"----"+this.id_materiel+"---"+this.etat_emprunt+"---"+this.id_emprunteur);
        Request request = new Request.Builder().url(URL + "emprunt/"+this.id_materiel+"/"+this.etat_emprunt+"/"+this.id_emprunteur).build();

        // POUR METTRE A JOUR DISPONIBILITE
        Request request2 = new Request.Builder().url(URL + "emprunt/"+this.id_materiel).build();

        client.newCall(request2).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.err.println("mise a jour disponibilite emprunt failed .. Callback failure in MatarielBis class");

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.err.println("Emprunter materiel failed .. Callback failure in MatarielBis class");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                emprunterMaterielCallBack.onSuccess("veuillez récuperer le materiel, prenez en soin !");
            }
        });
    }


    public interface EmprunterMaterielCallBack {
        public void onSuccess(String msg);
        public void onFail();
    }




    public final void rendreMateriel(final RendreMaterielCallBack rendreMaterielCallBack){
        OkHttpClient client = new OkHttpClient();
        System.out.println("RENDRE MATERIEL HAHAAHHAHA " +this.id+"---"+this.etat_emprunt);

        Request request = new Request.Builder().url(URL + "rendre/"+this.id+"/"+this.etat_emprunt).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.err.println("Rendre materiel failed .. Callback failure in MatarielBis class");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                rendreMaterielCallBack.onSuccess("Merci d'avoir rendu le materiel !");
            }
        });
    }

    public interface RendreMaterielCallBack {
        public void onSuccess(String msg);
        public void onFail();
    }

}
