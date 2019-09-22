package com.example.lukas.fuellog.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.lukas.fuellog.R;
import com.example.lukas.fuellog.models.RefuelStop;
import com.example.lukas.fuellog.repository.DatabaseHelper;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;


public class DiagramFragment extends Fragment {
    public static final String TAG = "DiagramFragment";
    LineGraphSeries<DataPoint> series;
    private DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diagram, container, false);


        databaseHelper = new DatabaseHelper(getActivity());
        double y, x;
        x = 0;
        y = 0;

        GraphView graph = view.findViewById(R.id.graph);

        Cursor data = databaseHelper.getAllData();
        ArrayList<RefuelStop> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(new RefuelStop(data));
        }

        series = new LineGraphSeries<DataPoint>();

        for (RefuelStop refuelStop: listData
                ) {
            x += 1;
            y = refuelStop.getPPL();
            series.appendData(new DataPoint(x, y), true, 40);
        }
      /*  for(int i = 0; i < 500; i++){
            x = x +0.1;
            y = Math.cos(x);
            series.appendData(new DataPoint(x, y), true, 500);
        } */
        graph.addSeries(series);

        return view;
    }

    private void getpplDiagramm(){

    }
}
