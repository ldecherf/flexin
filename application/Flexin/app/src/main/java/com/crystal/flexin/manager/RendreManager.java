package com.crystal.flexin.manager;

import android.icu.util.GregorianCalendar;

import com.crystal.flexin.resources.Emprunt;
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
 * Created by basaile92 on 22/05/2017.
 */

public class RendreManager {

    String URL = "http://192.168.43.34:8080/";


    public final void rendreMateriel(String id_materiel,String etat_emprunt,String id_emprunt,String date_retour, final RendreMaterielCallBack rendreMaterielCallBack){
        OkHttpClient client = new OkHttpClient();


        Request requestRendreDisp = new Request.Builder().url(URL + "rendre_disp/"+id_materiel+"/"+etat_emprunt).build();
        Request requestRendreUpdate = new Request.Builder().url(URL + "rendre_update/"+id_emprunt+"/"+date_retour).build();



        client.newCall(requestRendreDisp).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.err.println("Rendre_disp materiel failed .. Callback failure in RendreManager class");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                rendreMaterielCallBack.onSuccess();
            }
        });

        client.newCall(requestRendreUpdate).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.err.println("Rendre_update materiel failed .. Callback failure in RendreManager class");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                rendreMaterielCallBack.onSuccess();
            }
        });
    }

    public interface RendreMaterielCallBack {
        public void onSuccess();
        public void onFail();
    }

    public final void getIdEmprunt(String id_materiel,String id_emprunteur, final IdEmpruntFetchCallBack idEmpruntFetchCallBack ){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(URL + "get_id_emprunt/"+id_materiel+"/"+id_emprunteur).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.err.println("getting Id Emprunt failed for rendre materiel request .. Callback failure IdEmpruntFetch/RendreManager class");

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                Type collectionType = new TypeToken<Collection<Emprunt>>(){}.getType();
                Collection<Emprunt> enums = gson.fromJson(result, collectionType);
                Emprunt[] emprunts = enums.toArray(new Emprunt[enums.size()]);
                idEmpruntFetchCallBack.onSuccess(emprunts);
            }
        });

    }

    public interface IdEmpruntFetchCallBack {
        public void onSuccess(Emprunt[] emprunts);
        public void onFail();
    }






}
