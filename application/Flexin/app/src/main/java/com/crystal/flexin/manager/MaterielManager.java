package com.crystal.flexin.manager;

import android.content.Context;
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


public class MaterielManager implements Serializable{

    String URL = "http://192.168.43.34:8080/";

    private Materiel materiel ;
    //id materiel for get materiel
    private String id ;


    public MaterielManager(String id){
        this.id = id ;
    }




    public void setMateriel(Materiel mat){
        this.materiel = mat ;
    }

    public Materiel getMateriel(){
        return this.materiel;
    }



    public final void getMateriel(final GetMaterielCallBack getMaterielCallBack, final Context context){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(URL + "materiel/"+this.id ).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.err.println("Getting materiel failed .. ");
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



}
