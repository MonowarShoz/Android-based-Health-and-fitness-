package com.shoz.monowar.fitnessandhealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class exerciseActivity extends AppCompatActivity {

    SharedPreferences mPref;
    SharedPreferences.Editor editor;
    EditText ExTime;
    Button go;
    int t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        mPref = getSharedPreferences("com.lighttest.sharedpreferences", MODE_PRIVATE);
        editor = mPref.edit();
        go = (Button)findViewById(R.id.startEx);
        ExTime = (EditText)findViewById(R.id.ExDur);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    saveData();
                    Intent intent = new Intent(exerciseActivity.this,
                            ExerciseCategory.class);
                    startActivity(intent);
                }


            });




    }
    public void saveData(){
       t = Integer.parseInt( ExTime.getText().toString());


        editor.putInt("TIME",t);
        setResult(RESULT_OK);
        editor.apply();

       }


    }








