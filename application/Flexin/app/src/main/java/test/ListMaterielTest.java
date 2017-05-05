package test;

import com.crystal.flexin.data.Emprunt;
import com.crystal.flexin.data.FlexinService;
import com.crystal.flexin.data.ListMateriels;
import com.crystal.flexin.data.Materiel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import
        org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.Assert.assertEquals;

/**
 * Created by aymane on 22/03/17.
 */
public class ListMaterielTest {

    @Test
    public void getMeterielTest() throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://192.168.172.53:8080/materiel/1").build();
        okhttp3.Response response = client.newCall(request).execute();
        String result = response.body().string();
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<Materiel>>(){}.getType();
        Collection<Materiel> enums = gson.fromJson(result, collectionType);
        Materiel[] materiels = enums.toArray(new Materiel[enums.size()]);
        assertEquals(materiels[0].nom,"Souris");
    }

    @Test
    public void emprunterTest() throws IOException {

        String id = "24/";
        String id_materiel = "1/";
        String etat_emprunt = "moyen/";
        String id_emprunteur = "1992";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://192.168.172.53:8080/emprunt/"+id+id_materiel+etat_emprunt+id_emprunteur).build();
        okhttp3.Response response = client.newCall(request).execute();
        Request request2 = new Request.Builder().url("http://192.168.172.53:8080/emprunt/"+id).build();
        okhttp3.Response response2 = client.newCall(request2).execute();
        String result = response2.body().string();
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<Emprunt>>(){}.getType();
        Collection<Emprunt> enums = gson.fromJson(result, collectionType);
        Emprunt[] emprunts = enums.toArray(new Emprunt[enums.size()]);
        assertEquals(emprunts[0].id,24);

    }

}
