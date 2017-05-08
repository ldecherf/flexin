package com.crystal.flexin.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.crystal.flexin.R;
import com.crystal.flexin.fragment.DialogTextSearchFragment;
import com.crystal.flexin.manager.MaterielManager;

public class TextSearchActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView homeActivityEquipmentRecyclerView;
    private MaterielManager materielManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_search);

        Intent intent = getIntent();
        String searchName = intent.getStringExtra(DialogTextSearchFragment.DIALOGINTENT);


        this.homeActivityEquipmentRecyclerView = (RecyclerView) findViewById(R.id.homeActivityMaterielRecyclerView);
        this.homeActivityEquipmentRecyclerView.setHasFixedSize(true);
        this.mLayoutManager = new LinearLayoutManager(this);
        this.homeActivityEquipmentRecyclerView.setLayoutManager(mLayoutManager);
        this.materielManager = new MaterielManager(findViewById(R.id.homeActivityMainLayout), this.homeActivityEquipmentRecyclerView);
        this.materielManager.execute();*/

    }
}
