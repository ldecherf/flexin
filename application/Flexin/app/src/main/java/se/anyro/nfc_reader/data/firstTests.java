package se.anyro.nfc_reader.data;

import
        org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by aymane on 22/03/17.
 */
public class firstTests {

    @Test
    public void listMaterielTest() throws IOException {
        ProxService service = ProxService.Factory.makeProxService(ProxService.ENDPOINT);
        Call<Materiel> call = service.getUrlDetails();
        Response<Materiel> response = call.execute() ;
        System.out.println(response.body().materiels);
        //assertEquals(response.body().materiels.get(0),"");
    }
}
