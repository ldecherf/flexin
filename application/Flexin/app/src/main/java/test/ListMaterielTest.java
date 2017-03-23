package test;

import com.crystal.flexin.data.FlexinService;

import
        org.junit.Test;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.Assert.assertEquals;

/**
 * Created by aymane on 22/03/17.
 */
public class ListMaterielTest {

    @Test
    public void listMaterielTest() throws IOException {
        FlexinService service = FlexinService.Factory.makeFlexinService(FlexinService.ENDPOINT);
        Call<List<List<String>>> call = service.getUrlDetails();
        Response<List<List<String>>> response = call.execute() ;
        System.out.println(response.body().get(0).get(1));
        //assertEquals(response.body().materiels.get(0),"");
    }
}
