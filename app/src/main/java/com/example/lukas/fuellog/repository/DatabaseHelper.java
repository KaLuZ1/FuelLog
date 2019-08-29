package com.example.lukas.fuellog.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.lukas.fuellog.models.RefuelStop;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "fuel_table";
    private static final String COL1 = "date";
    private static final String COL2 = "station";
    private static final String COL3 = "kmcounter";
    private static final String COL4 = "volume";
    private static final String COL5 = "price";
    private static final String COL6 = "ppl";



    public DatabaseHelper(Context context){
        super(context, TABLE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1 + " TEXT, " +
                COL2 + " TEXT, " +
                COL3 + " INTEGER, " +
                COL4 + " FLOAT, " +
                COL5 + " FLOAT, " +
                COL6 + " FLOAT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int a, int b){
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(RefuelStop stop){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, stop.getDate().toString());
        contentValues.put(COL2, stop.getStation());
        contentValues.put(COL3, stop.getKmCounter());
        contentValues.put(COL4, stop.getVolume());
        contentValues.put(COL5, stop.getPrice());
        contentValues.put(COL6, stop.getPPL());

        Log.d(TAG, "addData: Adding " + stop.getStation() + "to" + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public int getCurrentKmCount(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select max(" + COL3 + ") FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        if(data.getCount() == -1) {
            return 0;
        } else if(data.getCount() > 1){
            return data.getInt(1);
        } else {
            return 0;
        }
    }

    public boolean deleteStops(ArrayList<RefuelStop> blackList) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE";
        while (!blackList.isEmpty()){
            query += " ID=" + blackList.get(0).getId() + " OR";
            blackList.remove(0);
        }
        query = query.substring(0, query.length() - 2);
        db.execSQL(query);
        return true;
    }

    // public boolean deleteStops()


}
