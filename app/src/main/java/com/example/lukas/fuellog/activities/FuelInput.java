package com.example.lukas.fuellog.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lukas.fuellog.R;
import com.example.lukas.fuellog.repository.DatabaseHelper;
import com.example.lukas.fuellog.models.RefuelStop;


public class FuelInput extends Activity {
    private TextInputLayout textInputStation;
    private TextInputLayout textInputKmCount;
    private TextInputLayout textInputVolume;
    private TextInputLayout textInputPayed;
    private ListView listView;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fuelinputwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        databaseHelper = new DatabaseHelper(this);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.7), (int)(height * 0.5));

        textInputStation = findViewById(R.id.text_input_station);
        textInputKmCount = findViewById(R.id.text_input_kmcount);
        textInputPayed = findViewById(R.id.text_input_payed);
        textInputVolume = findViewById(R.id.text_input_volume);

    }

    private boolean validateKmCount(int kmCountInput){
        if(kmCountInput < 0){
            textInputKmCount.setError("km-Stand muss größer 0 sein");
            return false;
        } else {
            textInputKmCount.setError(null);
            return true;
        }

    }

    private boolean validateVolume(float volumeInput){
        if(volumeInput < 0){
            textInputVolume.setError("Spritmenge muss größer 0 sein");
            return false;
        } else {
            textInputVolume.setError(null);
            return true;
        }
    }

    private boolean validatePayed(float payedInput){
        if(payedInput < 0){
            textInputPayed.setError("Preis muss größer 0 sein");
            return false;
        } else {
            textInputPayed.setError(null);
            return true;
        }
    }

    private boolean validateStation(String inputStation){
        if(inputStation.isEmpty()){
            textInputStation.setError("Field can't be empty");
            return false;
        } else {
            textInputStation.setError(null);
            return true;
        }
    }

    public void confirmInput(View v){
        int kmCountInput = Integer.valueOf(textInputKmCount.getEditText().getText().toString().trim());
        float volumeInput = Float.valueOf(textInputVolume.getEditText().getText().toString().trim());
        float payedInput = Float.valueOf(textInputPayed.getEditText().getText().toString().trim());
        String stationInput = textInputStation.getEditText().getText().toString().trim();

        if(!validateKmCount(kmCountInput) | !validateVolume(volumeInput)| !validatePayed(payedInput) | !validateStation(stationInput)){
            return;
        }

        RefuelStop refuelStop = new RefuelStop(stationInput, kmCountInput, payedInput, volumeInput);

        String input = "km-Stand: " + kmCountInput + "km";
        input += "\n";
        input += "Sprit: " + volumeInput + "liter";
        input += "\n";
        input += "Preis: " + payedInput + "EURO";
        input += "\n";
        input += "Tankstelle: " + stationInput;
        input = "Speichern erfolgreich! \n" + input;


        if(databaseHelper.addData(refuelStop)){
            Toast.makeText(this, input, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Ein Fehler beim speichern ist aufgetreten", Toast.LENGTH_LONG).show();
        }


    }

}
