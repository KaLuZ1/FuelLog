package com.example.lukas.fuellog.models;

import android.database.Cursor;
import android.widget.CheckBox;

import java.util.Calendar;
import java.util.Date;

public class RefuelStop {

    private String date;
    private String station;
    private int kmCounter;
    private float volume;
    private float price;
    private float pPL;
    private int id;

    public RefuelStop(String station, int kmCounter, float price, float volume) {
        this.station = station;
        this.kmCounter = kmCounter;
        this.price = price;
        this.pPL = price/volume;
        this.date = Calendar.getInstance().getTime().toString();
        this.volume = volume;
    }
    public RefuelStop(Cursor cursor){

        this.id = cursor.getInt(0);
        this.date = cursor.getString(1);
        this.station = cursor.getString(2);
        this.kmCounter = cursor.getInt(3);
        this.volume = cursor.getFloat(4);
        this.price = cursor.getFloat(5);
        this.pPL = cursor.getFloat(6);



     /*   contentValues.put(COL1, stop.getDate().toString());
        contentValues.put(COL2, stop.getStation());
        contentValues.put(COL3, stop.getKmCounter());
        contentValues.put(COL4, stop.getVolume());
        contentValues.put(COL5, stop.getPrice());
        contentValues.put(COL6, stop.getPPL());*/
    }

    public int getId(){
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getStation() {
        return station;
    }

    public int getKmCounter() {

        return kmCounter;
    }

    public float getVolume() {
        return volume;
    }

    public float getPrice() {
        return price;
    }

    public float getPPL() {
        return pPL;
    }
}
