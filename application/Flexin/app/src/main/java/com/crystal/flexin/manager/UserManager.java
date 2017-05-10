package com.crystal.flexin.manager;

import android.content.Context;
import android.util.Log;

import com.crystal.flexin.resources.Materiel;
import com.crystal.flexin.resources.Personne;
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
 * Created by aymane on 10/05/17.
 */

public class UserManager {


    String URL = "http://192.168.172.61:8080/";
    private String id ;

    public UserManager(String id){
        this.id = id;
    }

    public final void getPersonne(final GetPesonneCallBack getPesonneCallBack){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(URL + "personne/"+this.id ).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.err.println("Getting user Infos failed .. Callback failure in UserManager class");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                Type collectionType = new TypeToken<Collection<Personne>>(){}.getType();
                Collection<Personne> enums = gson.fromJson(result, collectionType);
                Personne[] personnes = enums.toArray(new Personne[enums.size()]);
                getPesonneCallBack.onSuccess(personnes);

            }
        });
    }

    public interface GetPesonneCallBack {
        public void onSuccess(Personne[] personne);
        public void onFail();
    }

}
