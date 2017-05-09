package com.crystal.flexin.activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.crystal.flexin.R;
import com.crystal.flexin.TagViewer;
import com.crystal.flexin.fragment.DialogTextSearchFragment;
import com.crystal.flexin.manager.MaterielManager;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class HomeActivity extends Activity {


    private ImageView searchTextButton;
    private ImageView searchScanButton;
    private ImageView searchNfcButton;
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
        /*/
        this.homeActivityEquipmentRecyclerView = (RecyclerView) findViewById(R.id.homeActivityMaterielRecyclerView);
        this.homeActivityEquipmentRecyclerView.setHasFixedSize(true);
        this.mLayoutManager = new LinearLayoutManager(this);
        this.homeActivityEquipmentRecyclerView.setLayoutManager(mLayoutManager);
        this.materielManager = new MaterielManager(findViewById(R.id.homeActivityMainLayout), this.homeActivityEquipmentRecyclerView);
        this.materielManager.execute();*/

        this.searchTextButton = (ImageView) findViewById(R.id.searchTextButton);
        this.searchTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment dialogTextSearchFragment = new DialogTextSearchFragment();
                dialogTextSearchFragment.show(getFragmentManager(), "DialogTextSearchFragment");
            }
        });

        this.searchScanButton = (ImageView) findViewById(R.id.searchScanButton);
        this.searchScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator scanIntegrator = new IntentIntegrator(HomeActivity.this);
                scanIntegrator.initiateScan();

            }
        });

        this.searchNfcButton = (ImageView) findViewById(R.id.searchNfcButton);
        this.searchNfcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TagViewer.class);
                intent.putExtra("forEmprunt", false);
                startActivity(intent);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve result of scanning - instantiate ZXing object
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        //check we have a valid result
        if (scanningResult != null) {
            //get content from Intent Result
            String scanContent = scanningResult.getContents();
            //get format name of data scanned
            String scanFormat = scanningResult.getFormatName();
            if (scanContent != null && scanFormat != null) {

                //TODO ouvre l'activité avec le bon élément.

            }
        }
    }



}
