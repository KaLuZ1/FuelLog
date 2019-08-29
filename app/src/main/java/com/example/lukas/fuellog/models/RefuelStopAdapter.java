package com.example.lukas.fuellog.models;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


import com.example.lukas.fuellog.R;

import java.util.ArrayList;

public class RefuelStopAdapter extends BaseAdapter {
    Activity context;
    ArrayList<RefuelStop> refuels;
    ArrayList<RefuelStop> blackList;
    CheckBox checkBox;
    private static LayoutInflater inflater = null;

    public RefuelStopAdapter(Activity context, ArrayList<RefuelStop> refuels){
        this.context = context;
        this.refuels = refuels;
        this.blackList = new ArrayList<>();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return refuels.size();
    }

    @Override
    public RefuelStop getItem(int position) {
        return refuels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        itemView = (itemView == null) ? inflater.inflate(R.layout.list_item, null): itemView;

        TextView textViewIndex = (TextView) itemView.findViewById(R.id.textViewIndex);
        TextView textViewDate = (TextView) itemView.findViewById(R.id.textViewDate);
        TextView textViewStation = (TextView) itemView.findViewById(R.id.textViewStation);
        TextView textViewKmCounter = (TextView) itemView.findViewById(R.id.textViewKmCounter);
        TextView textViewVolume = (TextView) itemView.findViewById(R.id.textViewVolume);
        TextView textViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
        TextView textViewPpl = (TextView) itemView.findViewById(R.id.textViewPpl);
        final RefuelStop selectedStop = refuels.get(position);
        checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    blackList.add(selectedStop);
                } else {
                    blackList.remove(selectedStop);
                }
            }
        });

        String id = "Id: " + selectedStop.getId();
        String date = selectedStop.getDate().substring(0,16);
        String km = "Zähler: " + selectedStop.getKmCounter() + " km";
        String volume = selectedStop.getVolume() + " Liter";
        String price = selectedStop.getPrice() + " EURO";
        String ppl = (Math.round(selectedStop.getPPL() * 100.0) / 100.0) + " EURO/Liter";

        textViewIndex.setText(id);
        textViewDate.setText(date);
        textViewStation.setText(selectedStop.getStation());
        textViewKmCounter.setText(km);
        textViewVolume.setText(volume);
        textViewPrice.setText(price);
        textViewPpl.setText(ppl);


        return itemView;

    }

    public ArrayList<RefuelStop> getBlackList(){
        return blackList;
    }
}
