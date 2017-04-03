package test;

import com.crystal.flexin.data.FlexinService;
import com.crystal.flexin.data.Materiel;
import com.crystal.flexin.resources.Equipment;

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
        FlexinService service = FlexinService.Factory.makeFlexinService(FlexinService.URL);
        Call<List<Equipment>> call = service.getEquipmentList();
        Response<List<Equipment>> response = call.execute() ;
        assertEquals(response.body().get(0).getNom(),"Enceintes");
    }

}
