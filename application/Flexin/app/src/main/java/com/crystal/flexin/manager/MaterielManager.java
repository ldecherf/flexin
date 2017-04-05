package com.crystal.flexin.manager;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.crystal.flexin.R;
import com.crystal.flexin.adapter.MaterielListAdapter;
import com.crystal.flexin.data.FlexinService;
import com.crystal.flexin.resources.Materiel;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by basaile92 on 01/04/2017.
 */

public class MaterielManager extends AsyncTask<Void , Void , Response<List<Materiel>>> {

    private ProgressDialog progressDialog;
    private View view;
    private RecyclerView homeActivityEquipmentRecyclerView;

    public MaterielManager(View view, RecyclerView homeActivityEquipmentRecyclerView){

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
    protected Response<List<Materiel>> doInBackground(Void... voids) {
        Response<List<Materiel>> response = null;
        try {
            FlexinService service = FlexinService.Factory.makeFlexinService(FlexinService.URL);
            Call<List<Materiel>> call = service.getEquipmentList();
            response = call.execute();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    protected void onPostExecute(Response<List<Materiel>> equipmentsResponse) {
        super.onPostExecute(equipmentsResponse);
        this.homeActivityEquipmentRecyclerView = (RecyclerView) this.view.findViewById(R.id.homeActivityMaterielRecyclerView);
        this.homeActivityEquipmentRecyclerView.setAdapter(new MaterielListAdapter(equipmentsResponse.body()));
        this.progressDialog.dismiss();
    }

    public static void setEtatColor(LinearLayout equipmentEtatLayout, String etat) {

        if(etat.equals("bon"))
            equipmentEtatLayout.setBackgroundColor(Color.GREEN);
        else if(etat.equals("moyen"))
            equipmentEtatLayout.setBackgroundColor(Color.YELLOW);
        else if(etat.equals("reparation"))
            equipmentEtatLayout.setBackgroundColor(Color.RED);
        else if(etat.equals("detruit") || etat.equals("obsolete"))
            equipmentEtatLayout.setBackgroundColor(Color.GRAY);
    }

}
