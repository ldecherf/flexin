package com.crystal.flexin.manager;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.crystal.flexin.R;
import com.crystal.flexin.adapter.EquipmentListAdapter;
import com.crystal.flexin.data.FlexinService;
import com.crystal.flexin.resources.Equipment;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by basaile92 on 01/04/2017.
 */

public class EquipmentManager extends AsyncTask<Void , Void , List<Equipment>> {

    private ProgressDialog progressDialog;
    private View view;
    private RecyclerView homeActivityEquipmentRecyclerView;

    public EquipmentManager(View view, RecyclerView homeActivityEquipmentRecyclerView){

        this.view = view;
        this.homeActivityEquipmentRecyclerView = homeActivityEquipmentRecyclerView;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        this.progressDialog = new ProgressDialog(this.view.getContext());
        this.progressDialog.setMessage(this.view.getContext().getString(R.string.loading));
        this.progressDialog.show();
    }

    @Override
    protected List<Equipment> doInBackground(Void... voids) {
        try {
            FlexinService service = FlexinService.Factory.makeFlexinService(FlexinService.ENDPOINT);
            Call<List<Equipment>> call = service.getUrlDetails();
            Response<List<Equipment>> response = call.execute();
            return response.body();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Equipment> equipments) {
        super.onPostExecute(equipments);
        this.homeActivityEquipmentRecyclerView = (RecyclerView) this.view.findViewById(R.id.homeActivityEquipmentRecyclerView);
        this.homeActivityEquipmentRecyclerView.setAdapter(new EquipmentListAdapter(equipments));
        this.progressDialog.dismiss();
    }

    public static void setEtatColor(LinearLayout equipmentEtatLayout, String etat) {

        if(etat.equals("neuf"))
            equipmentEtatLayout.setBackgroundColor(Color.GREEN);
        else if(etat.equals("moyen"))
            equipmentEtatLayout.setBackgroundColor(Color.YELLOW);
        else if(etat.equals("reparation"))
            equipmentEtatLayout.setBackgroundColor(Color.RED);
        else if(etat.equals("detruit") || etat.equals("obsolete"))
            equipmentEtatLayout.setBackgroundColor(Color.GRAY);
    }

}
