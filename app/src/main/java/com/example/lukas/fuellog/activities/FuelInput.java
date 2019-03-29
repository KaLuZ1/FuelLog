package com.example.lukas.fuellog.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.lukas.fuellog.R;


public class FuelInput extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fuelinputwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.7), (int)(height * 0.7));
    }

}
