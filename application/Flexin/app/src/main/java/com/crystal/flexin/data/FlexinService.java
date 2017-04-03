package com.crystal.flexin.data;

import com.crystal.flexin.BuildConfig;
import com.crystal.flexin.resources.Equipment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
/**
 * Created by aymane on 22/03/17.
 */
public interface FlexinService {

    String URL = "http://localhost:8080";


    @GET("materiel")
    Call<List<Equipment>> getEquipmentList();

    /******** Factory class that sets up a new Proximity services *******/
    class Factory {
        public static FlexinService makeFlexinService(String url) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

            logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();

            return retrofit.create(FlexinService.class);

        }

    }

}
