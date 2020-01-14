package com.shoz.monowar.fitnessandhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Food extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Gson gson = new Gson();
    Spinner fspinner;
    List<String>list;
    EditText foodName;
    EditText caloriesPerServing;
    EditText numServings;
    SharedPreferences mPref;
    SharedPreferences.Editor editor;
    String [] fcho =  new String[]{"Rice", "Milk", "Chocolate Cake","Apple","Banana","Grapes","Mango","Fish","Mutton","Egg","Plain Cake"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        fspinner = (Spinner)findViewById(R.id.spinnerF);
        list = new ArrayList<>(Arrays.asList(fcho));
        caloriesPerServing = findViewById(R.id.textCalories);
        numServings = findViewById(R.id.textServings);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, fcho);

        fspinner.setAdapter(adapter);
        fspinner.setOnItemSelectedListener(this);



        mPref = getSharedPreferences("com.lighttest.sharedpreferences", MODE_PRIVATE);
        editor = mPref.edit();
    }

    public void submitFood(View view){
        foodName = findViewById(R.id.textFoodName);

        FoodHelper newItem = new FoodHelper(foodName.getText().toString(), Integer.valueOf(caloriesPerServing.getText().toString()), Integer.valueOf(numServings.getText().toString()));
        String json = gson.toJson(newItem);
        editor.putString("NEW_FOOD", json);
        editor.apply();
        setResult(RESULT_OK);
        finish();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(fspinner.getSelectedItem().toString() == "Rice"){
            caloriesPerServing.setText("80");
        }
        if(fspinner.getSelectedItem().toString() =="Milk"){
            caloriesPerServing.setText("150");
        }
        if(fspinner.getSelectedItem().toString() == "Chocolate Cake"){
            caloriesPerServing.setText("100");
        }
        if(fspinner.getSelectedItem().toString() == "Apple"){
            caloriesPerServing.setText("50");
        }if(fspinner.getSelectedItem().toString() == "Banana"){
            caloriesPerServing.setText("60");
        }if(fspinner.getSelectedItem().toString() == "Grapes"){
            caloriesPerServing.setText("50");
        }if(fspinner.getSelectedItem().toString() == "Mango"){
            caloriesPerServing.setText("50");
        }if(fspinner.getSelectedItem().toString() == "Fish"){
            caloriesPerServing.setText("55");
        }if(fspinner.getSelectedItem().toString() == "Mutton"){
            caloriesPerServing.setText("75");
        }if(fspinner.getSelectedItem().toString() == "Egg"){
            caloriesPerServing.setText("75");
        }if(fspinner.getSelectedItem().toString() == "Plain Cake"){
            caloriesPerServing.setText("135");
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

