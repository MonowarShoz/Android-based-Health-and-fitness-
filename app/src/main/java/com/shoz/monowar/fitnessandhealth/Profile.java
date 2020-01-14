package com.shoz.monowar.fitnessandhealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Profile extends  AppCompatActivity implements AdapterView.OnItemSelectedListener {

    SharedPreferences mPrefs;

    SharedPreferences.Editor editor;
    EditText FirstName;

    EditText Weight;
    EditText Height;
    EditText Age;
    String Gender;
    int iGoal;
    boolean metric;
    Switch metricSwitch;
    Spinner goal;
    Spinner dropdown;


    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //create a list of items for the spinner.
        String[] items1 = new String[]{"Male", "Female"};
        String[] items2 = new String[]{"Maintain Weight", "Lose Weight", "Gain Weight"};

        FirstName = (EditText) findViewById(R.id.textFirstName);

        Weight = (EditText) findViewById(R.id.textWeight);
        Height = (EditText) findViewById(R.id.textHeight);
        Age = (EditText) findViewById(R.id.textAge);
        submitBtn = (Button) findViewById(R.id.submitBtn);

        dropdown = (Spinner) findViewById(R.id.ChooseGender);
        goal = (Spinner) findViewById(R.id.weightPlanner);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);


        dropdown.setAdapter(adapter1);
        dropdown.setOnItemSelectedListener(this);
        goal.setAdapter(adapter2);
        goal.setOnItemSelectedListener(this);

        //add the user information into SharedPreferences for use throughout the app




//        mPrefs = this.getSharedPreferences("com.lighttest.sharedpreferences", MODE_PRIVATE);
//        if (mPrefs.getString("FIRST_NAME", null) != null) {
//            if (mPrefs.getInt("WEIGHT", 0) != 0) {
//                if (mPrefs.getInt("HEIGHT", 0) != 0) {
//                    if (mPrefs.getInt("AGE", 0) != 0) {
//                        if (mPrefs.getString("GENDER", null) != null) {
//                            if (mPrefs.getFloat("ACTIVITY_FACTOR", -1) == -1) {
//
//                                Intent intent1 = new Intent(getApplicationContext(), RecordActivity.class);
//                                startActivity(intent1);
//                            }
//
////                                else {
////                                    Intent intent2 = new Intent(getApplicationContext(), ReccommendedCalorie.class);
////                                    startActivity(intent2);
////
////                                }
//
//
//                        }
//                    }
//                }
//            }
//        }


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fe = FirstName.getText().toString();
                try{
                int we = Integer.parseInt(Weight.getText().toString());
                int he = Integer.parseInt(Height.getText().toString());
                int Ae = Integer.parseInt(Age.getText().toString());
                }catch (NumberFormatException e){e.printStackTrace();}
                if(fe.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter your Data",Toast.LENGTH_SHORT).show();
                }else{
                    toSecondActivity();
                    disp();
                }


            }


        });


        metric = false;
        metricSwitch = (Switch) findViewById(R.id.metricSwitch);

        metricSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if (isChecked) {
                    metric = true;
                    Weight.setHint("Weight (KG)");
                    Height.setHint("Height (CM)");
                } else {
                    metric = false;
                    Weight.setHint("Weight (lbs)");
                    Height.setHint("Height (inches)");
                }
            }
        });

    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        if (dropdown.getSelectedItem() != null) {
            if (dropdown.getSelectedItem().toString() == "Male") {
                Gender = "Male";
            } else {
                Gender = "Female";
            }
        }
        if (goal.getSelectedItem() != null) {
            if (goal.getSelectedItem().toString() == "Maintain Weight") {
                iGoal = 1;
            } else if (goal.getSelectedItem().toString() == "Gain Weight") {
                iGoal = 3;
            } else {
                iGoal = 2;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Gender = "Male";

    }


    public void toSecondActivity() {


        String FirName = FirstName.getText().toString();
        if (FirName.isEmpty()) {
            FirstName.setError("Enter Name");
        }
        int weight = Integer.parseInt(Weight.getText().toString());



            Intent intent = new Intent(getApplicationContext(), RecordActivity.class);


            //sharedPreferences.edit().putString("firstName", String.valueOf(txtFirstName)).apply();
            //Expanded out the above to make it more readable in code
            mPrefs = this.getSharedPreferences("com.lighttest.sharedpreferences", MODE_PRIVATE);
            //initialized the editor using the above sharedPreferences variable
            editor = mPrefs.edit();
            //add the user information into SharedPreferences for use throughout the app

            editor.putString("FIRST_NAME", FirName);
            editor.putInt("WEIGHT", weight);
            editor.putInt("HEIGHT", Integer.parseInt(Height.getText().toString()));
            editor.putInt("AGE", Integer.parseInt(Age.getText().toString()));
            editor.putString("GENDER", Gender);
            editor.putBoolean("METRIC", metric);
            editor.putInt("GOAL", iGoal);

            //Apply the changes made to SharedPreferences
            editor.apply();


            //Intent intent = new Intent(getApplicationContext(), RecordActivity.class);
            startActivity(intent);
        }
        public void disp() {
            mPrefs = this.getSharedPreferences("com.lighttest.sharedpreferences", MODE_PRIVATE);
            if (mPrefs.getString("FIRST_NAME", null) == null) {
                if (mPrefs.getInt("WEIGHT", 0) == 0) {
                    if (mPrefs.getInt("HEIGHT", 0) == 0) {
                        if (mPrefs.getInt("AGE", 0) == 0) {
                            if (mPrefs.getString("GENDER", null) == null) {
                                if (mPrefs.getFloat("ACTIVITY_FACTOR", -1) != -1) {
                                    Intent intent1 = new Intent(getApplicationContext(), RecordActivity.class);
                                    startActivity(intent1);


                                } else {
                                    Intent intent2 = new Intent(getApplicationContext(), ReccommendedCalorie.class);
                                    startActivity(intent2);
                                }

//


                            }
                        }
                    }
                }
            }

        }
    }








