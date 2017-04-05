package com.crystal.flexin.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.crystal.flexin.R;
import com.crystal.flexin.manager.MaterielManager;


public class HomeActivity extends Activity {


    private ImageView searchTextButton;
    private ImageView searchScanButton;
    private ImageView searchNfcButton;
    private ImageView searchLocationButton;
    private ImageView sortByButton;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView homeActivityEquipmentRecyclerView;
    private MaterielManager materielManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    private void init(){

        this.homeActivityEquipmentRecyclerView = (RecyclerView) findViewById(R.id.homeActivityMaterielRecyclerView);
        this.homeActivityEquipmentRecyclerView.setHasFixedSize(true);
        this.mLayoutManager = new LinearLayoutManager(this);
        this.homeActivityEquipmentRecyclerView.setLayoutManager(mLayoutManager);
        this.materielManager = new MaterielManager(findViewById(R.id.homeActivityMainLayout), this.homeActivityEquipmentRecyclerView);
        this.materielManager.execute();

        this.searchTextButton = (ImageView) findViewById(R.id.searchTextButton);
        this.searchTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        this.searchScanButton = (ImageView) findViewById(R.id.searchScanButton);
        this.searchScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        this.searchNfcButton = (ImageView) findViewById(R.id.searchNfcButton);
        this.searchNfcButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        this.searchLocationButton = (ImageView) findViewById(R.id.searchLocationButton);
        this.searchLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        this.sortByButton = (ImageView) findViewById(R.id.sortByButton);
        this.sortByButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

}
