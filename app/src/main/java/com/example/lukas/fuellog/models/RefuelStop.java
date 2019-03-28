package com.example.lukas.fuellog.models;

import java.util.Calendar;
import java.util.Date;

public class RefuelStop {

    private Date date;
    private String station;
    private int kmCounter;
    private float volume;
    private float price;
    private float pPL;

    public Date getDate() {
        return date;
    }

    public String getStation() {
        return station;
    }

    public RefuelStop(String station, int kmCounter, float price, float pPL) {
        this.station = station;
        this.kmCounter = kmCounter;
        this.price = price;
        this.pPL = pPL;
        this.date = Calendar.getInstance().getTime();
        this.volume = price / pPL;
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
