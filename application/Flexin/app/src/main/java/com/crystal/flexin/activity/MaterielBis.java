package com.crystal.flexin.activity;

import com.crystal.flexin.resources.Materiel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
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

public class MaterielBis {

    private Materiel materiel ;
    private String id ;
    private String id_materiel ;
    private String etat_emprunt ;
    private String id_emprunteur ;

    public MaterielBis(){}

    public MaterielBis(String id,String id_materiel,String etat_emprunt,String id_emprunteur){
        this.id = id ;
        this.id_materiel = id_materiel ;
        this.etat_emprunt = etat_emprunt ;
        this.id_emprunteur = id_emprunteur ;
    }

    public void setMateriel(Materiel mat){
        this.materiel = mat ;
    }

    public Materiel getMateriel(){
        return this.materiel;
    }

    //connecter le telephone en localhost ;)

    public final void getMateriel(final GetMaterielCallBack getMaterielCallBack){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://localhost:8080/materiel/1").build();
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
                getMaterielCallBack.onSuccess(materiels[0]);
            }
        });
    }

    public interface GetMaterielCallBack {
        public void onSuccess(Materiel materiel);
        public void onFail();
    }


    public final void emprunterMateriel(final EmprunterMaterielCallBack emprunterMaterielCallBack){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://192.168.172.52:8080/emprunt/"+this.id+this.id_materiel+this.etat_emprunt+this.id_emprunteur).build();
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
}
