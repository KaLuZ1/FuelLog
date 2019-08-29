package com.example.lukas.fuellog.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lukas.fuellog.R;
import com.example.lukas.fuellog.models.RefuelStopAdapter;
import com.example.lukas.fuellog.repository.DatabaseHelper;
import com.example.lukas.fuellog.models.RefuelStop;

import java.util.ArrayList;

public class FuelStopsFragment extends Fragment {
    public static final String TAG = "FuelStopFragment";
    private DatabaseHelper databaseHelper;
    private ListView listView;
    private RefuelStopAdapter adapter;
    ImageButton deleteButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fuelstops, container, false);
        databaseHelper = new DatabaseHelper(getActivity());
        listView = view.findViewById(R.id.stops);

        deleteButton = view.findViewById(R.id.deleteButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter != null){
                    if(databaseHelper.deleteStops(adapter.getBlackList())){
                        Toast.makeText(getActivity(), "Stops wurdern gelöscht", Toast.LENGTH_SHORT).show();
                        onPause();
                        onResume();
                    }
                }
            }
        });


        getFuelList(listView);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getFuelList(listView);
    }

    private void getFuelList(ListView listView){
        Log.d(TAG, "query all DB elements");

        Cursor data = databaseHelper.getAllData();
        ArrayList<RefuelStop> listData = new ArrayList<>();

        while(data.moveToNext()){
            listData.add(new RefuelStop(data));
        }

        this.adapter = new RefuelStopAdapter(getActivity(), listData);
        listView.setAdapter(adapter);



       // listView.setOnItemClickListener(());






    }
}
