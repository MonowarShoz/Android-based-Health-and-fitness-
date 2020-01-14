package com.shoz.monowar.fitnessandhealth;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecordActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    List<String> list;
    LayoutInflater inflater;
    String tmp;
    SharedPreferences mPrefs;
    SharedPreferences.Editor editor;
    Intent intent;
    float actFac;

    TextView ActiveTxt;
    String[] choices = new String[]{"Sedentary", "Lightly Active", "Active"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        ActiveTxt = (TextView)findViewById(R.id.ActiveText);
        spinner = (Spinner)findViewById(R.id.HowactiveSpinner);
        list = new ArrayList<>(Arrays.asList(choices));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, choices);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



        mPrefs = getSharedPreferences("com.lighttest.sharedpreferences", MODE_PRIVATE);
        String fName = mPrefs.getString("FIRST_NAME", "noData");
        Integer weight = mPrefs.getInt("WEIGHT", 0);
        Integer height = mPrefs.getInt("HEIGHT", 0);
        Integer age = mPrefs.getInt("AGE", 0);
        String gender = mPrefs.getString("GENDER", "noData");

    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {





        if (spinner.getSelectedItem().toString() == "Sedentary"){



            tmp = "The average person is sedentary. Anyone that spends less than 30 minutes per day intentionally exercising, is considered sedentary. \n"+
                    "Some of your activities may include working at your desk, walking your dog, shopping at Publix.";
            ActiveTxt.setText(tmp);




        }

        if (spinner.getSelectedItem().toString() == "Lightly Active"){



            tmp ="A user that spends at least 30 minutes or more intentionally exercising.\n" +
                    "Your activities may include walking the dog, mowing the lawn, gardening, being a car sales man, or even just jogging around the block.";
            ActiveTxt.setText(tmp);

        }

        if (spinner.getSelectedItem().toString() == "Active"){

            tmp ="A user that spends 1.5-2 hours out of their day intentionally exercising. \n" +
                    "Their normal daily activities may include waiting tables, mowing the lawn, or walking their dog. However most Active users set aside an extra 1-2 hours a day just for exercise.";
            ActiveTxt.setText(tmp);
        }


    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
    public void toNextActivity(View view){
        intent = new Intent(this, ReccommendedCalorie.class);
        if (spinner.getSelectedItem().toString() == "Sedentary"){
            actFac = (float) 1.2;
        }
        else if (spinner.getSelectedItem().toString() == "Lightly Active"){
            actFac = (float) 1.3;
        }
        else{
            actFac = (float) 1.4;
        }

        editor = mPrefs.edit();
        editor.putFloat("ACTIVITY_FACTOR",actFac);
        editor.apply();

        startActivity(intent);
    }
}
