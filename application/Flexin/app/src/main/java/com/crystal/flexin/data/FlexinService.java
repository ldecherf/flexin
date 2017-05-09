package com.crystal.flexin.data;

import com.crystal.flexin.BuildConfig;
import com.crystal.flexin.resources.ListMateriels;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by aymane on 22/03/17.
 */
public interface FlexinService {

    String URL = "http://192.168.172.53:8080/"; //192.168.43.34


    @GET("materiel/{id}")
    Call<ListMateriels> getMateriel(@Path("id")String id);

    @GET("name/{name}")
    Call<ListMateriels> getMaterielByName(@Path("name")String name);

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
