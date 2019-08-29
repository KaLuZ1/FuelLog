package com.example.lukas.fuellog.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lukas.fuellog.R;
import com.example.lukas.fuellog.models.RefuelStop;
import com.example.lukas.fuellog.repository.DatabaseHelper;

import java.util.ArrayList;

public class SummaryFragment extends Fragment {
    public static final String TAG = "SummaryFragment";

    private DatabaseHelper databaseHelper;

    TextView textViewStartKm;
    TextView textViewCurrentKm;
    TextView textViewSumVol;
    TextView textViewSumPrice;
    TextView textViewSumMileage;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary, container, false);

        databaseHelper = new DatabaseHelper(getActivity());

        textViewStartKm = (TextView) view.findViewById(R.id.textViewStartKm);
        textViewCurrentKm = (TextView) view.findViewById(R.id.textViewCurrentKm);
        textViewSumVol = (TextView) view.findViewById(R.id.textViewSumVol);
        textViewSumPrice = (TextView) view.findViewById(R.id.textViewSumPrice);
        textViewSumMileage = (TextView) view.findViewById(R.id.textViewSumMileage);

        showHome();

        return view;
    }

    private void showHome(){
        Cursor data = databaseHelper.getAllData();
        ArrayList<RefuelStop> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(new RefuelStop(data));
        }
        if(listData.isEmpty()){
            return;
        }
        float sumVol = 0;
        float sumPrice = 0;
        int start = listData.get(0).getKmCounter();
        int end = listData.get(listData.size() - 1).getKmCounter();
        for (RefuelStop refuelStop: listData
                ) {
            sumPrice += refuelStop.getPrice();
            sumVol += refuelStop.getVolume();
        }
        float mileage = ((sumVol - listData.get(listData.size() - 1).getVolume()) * 100)/ ((float)0.00001 + end - start);

        String startKm = "Zähler (Beginn: " + listData.get(0).getDate().toString().substring(4, 16) + "): " + start + " km";
        String currentKm = "Zähler (Jetzt: " + listData.get(listData.size() - 1).getDate().toString().substring(4, 16) + "): " + end + " km";
        String strSumVol = "Gesamtverbrauch: " + sumVol + " Liter";
        String strSumPrice = "Gesamtpreis: " + sumPrice + " EURO";
        String sumMileage = "Verbauch pro 100Km: " + mileage + " Liter";


        textViewStartKm.setText(startKm);
        textViewCurrentKm.setText(currentKm);
        textViewSumVol.setText(strSumVol);
        textViewSumPrice.setText(strSumPrice);
        textViewSumMileage.setText(sumMileage);
    }

    @Override
    public void onResume() {
        super.onResume();
        showHome();
    }
}
