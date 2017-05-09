package com.crystal.flexin.activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.crystal.flexin.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class HomeActivity extends Activity {


    private ImageView searchScanButton;
    private ImageView searchNfcButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
    }

    private void init(){

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
                Intent intent = new Intent(view.getContext(), TagViewerActivity.class);
                intent.putExtra("forEmprunt", false);
                startActivity(intent);
                finish();
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

                Intent intent2 = new Intent(HomeActivity.this, MaterielActivity.class);
                Log.e("1212", scanContent);
                intent2.putExtra(MaterielActivity.TAG, scanContent);
                startActivity(intent2);
                finish();
            }
        }
    }



}
