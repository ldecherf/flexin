package com.crystal.flexin.manager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.crystal.flexin.R;
import com.crystal.flexin.activity.HomeActivity;
import com.crystal.flexin.activity.MaterielActivity;
import com.crystal.flexin.resources.User;
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

public class UserManager {


    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public UserManager(Context context){

        this.preferences = context.getSharedPreferences("user", 0);
        this.editor = this.preferences.edit();

    }

    public void setUser(final String id, final HomeActivity activity){

        final FetchingClass fetchingClass = new FetchingClass();
        fetchingClass.fetchUser(id, new FetchingClass.FetchUserCallBack() {
            @Override
            public void onSuccess(final User[] users) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(users.length>0) {
                            User user = users[0];
                            editor.putString("id", id);
                            editor.putString("name", user.getName());
                            editor.putString("firstname", user.getFirstName());
                            editor.putString("password", user.getPassword());
                            editor.putString("mail", user.getMail());
                            editor.putString("tel", user.getTel());

                            editor.commit();

                            Intent intent = new Intent(activity.getApplicationContext(), activity.getClass());
                            activity.startActivity(intent);
                            activity.finish();
                        }

                    }
                });

            }

            @Override
            public void onFail() {
                Toast.makeText(activity, R.string.errorConnection, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void unSetUser(){

        this.editor.clear();
        this.editor.commit();
    }

    public User getUser(){
        User user = new User();
        user.setId(this.preferences.getString("id", null));
        user.setName(this.preferences.getString("name", null));
        user.setFirstName(this.preferences.getString("firstname", null));
        user.setPassword(this.preferences.getString("password", null));
        user.setMail(this.preferences.getString("mail", null));
        user.setTel(this.preferences.getString("tel", null));

        return user;
    }

    public boolean existsUser(){

        User user = getUser();
        return !(user.isNull());

    }


    public static class FetchingClass {

        public final void fetchUser(String id, final FetchUserCallBack fetchUserCallBack ){

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(MaterielActivity.URL + "personne/"+id ).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.err.println("Getting User failed .. Callback failure in UserManagerBis class");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String result = response.body().string();
                    Gson gson = new Gson();
                    Type collectionType = new TypeToken<Collection<User>>(){}.getType();
                    Collection<User> enums = gson.fromJson(result, collectionType);
                    User[] users = enums.toArray(new User[enums.size()]);
                    fetchUserCallBack.onSuccess(users);

                }
            });
        }

        public interface FetchUserCallBack {
            public void onSuccess(User[] users);
            public void onFail();
        }

    }



}
