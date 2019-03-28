package com.example.lukas.fuellog.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lukas.fuellog.models.RefuelStop;

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
                COL1 + " DATE, " +
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


        long result = db.insert(TABLE_NAME, null, contentValues);

        return result < 0 ? false : true;

    }

}
