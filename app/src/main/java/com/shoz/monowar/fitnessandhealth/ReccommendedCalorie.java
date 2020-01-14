package com.shoz.monowar.fitnessandhealth;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import static android.hardware.camera2.params.RggbChannelVector.BLUE;



public class ReccommendedCalorie extends AppCompatActivity {

    int gender;
    float height;
    float weight;
    float suggestedIntake;
    float activityFactor;
    int MET = 8;
    double bmi;
    int age;
    TextView toText;
    FirebaseAuth firebaseAuth;
    double kgTolbs = 2.20;
    SharedPreferences mPref;
    SharedPreferences.Editor editor;
    String tmpStr;
    TextView greetingText;
    TextView calorieText;
    TextView todayCalorieText;
    TextView progressMessage;
    TextView bmiText;
    TextView burnedCalories;

    int caloriesToday;
    int calBurn;
    //Get the current day below
    int day = ((int) System.currentTimeMillis() / 86400000);
    static final int NEW_FOOD_REQUEST = 1;
    static final int EXERCISE_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.calorie_recom);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        mPref = getSharedPreferences("com.lighttest.sharedpreferences", MODE_PRIVATE);
        editor = mPref.edit();

        greetingText = findViewById(R.id.greetingText);
        todayCalorieText = findViewById(R.id.currentCalories);
        progressMessage = findViewById(R.id.progressMessage);
        bmiText = findViewById(R.id.bmiText);

        burnedCalories = findViewById(R.id.burnedCalories);
        tmpStr = "Hello! " + mPref.getString("FIRST_NAME", "noData") + "!";
        greetingText.setText(tmpStr);

        if (day != mPref.getInt("DAY_OF_PREVIOUS_MEAL", 0)) {
            caloriesToday = 0;
            calBurn = 0;
        } else {
            caloriesToday = mPref.getInt("DAILY_CALORIES", 0);
            calBurn = mPref.getInt("CALORIES_BURNED_TODAY", 0);
        }

        tmpStr = "Calories consumed today:\n" + caloriesToday;
        todayCalorieText.setText(tmpStr);
        tmpStr = "Calories actively burned today:\n" + calBurn;
        burnedCalories.setText(tmpStr);

        if (mPref.getString("GENDER", "noData") == "Male") {
            gender = 0;
        } else {
            gender = 1;
        }

        if (mPref.getBoolean("METRIC", true)) {
            height = (float) (mPref.getInt("HEIGHT", 0));
            weight = (float) (mPref.getInt("WEIGHT", 0));
            bmi = Math.round((weight / Math.pow(height / 100, 2)) * 10) / 10;
            bmiText.setText(tmpStr);

        } else {
            height = (float) ((float) mPref.getInt("HEIGHT", 0) * 0.393701);
            weight = (float) ((float) mPref.getInt("WEIGHT", 0) * kgTolbs);
            bmi = Math.round((703 * weight / Math.pow(height, 2)) * 10) / 10;
            bmiText.setText(tmpStr);

        }
        if (bmi < 18.5) {
            tmpStr = "Your BMI: " + bmi + "\nYou're considered underweight.";

        } else if (bmi < 25 ) {
            tmpStr = "Your BMI: " + bmi + "\nYou're considered normal weight.";

        } else if (bmi < 30) {
            tmpStr = "Your BMI: " + bmi + "\nYou're considered overweight.";

        } else {
            tmpStr = "Your BMI: " + bmi + "\nYou're considered obese.";
        }
        bmiText.setText(tmpStr);


        activityFactor = mPref.getFloat("ACTIVITY_FACTOR", (float) 1.3);
        age = mPref.getInt("AGE", 0);
        if (gender == 0) {
            suggestedIntake = (float) ((10 * weight + 6.25 * height - 5 * age + 5) * activityFactor);
        } else if (gender == 1) {
            suggestedIntake = (float) ((10 * weight + 6.25 * height - 5 * age - 161) * activityFactor);
        }

        int goal = mPref.getInt("GOAL", 1);
        calorieText =(TextView) findViewById(R.id.recCalories);
        if (goal == 1) {
            tmpStr = "Your Recommended Daily Intake:\n" + Math.round(suggestedIntake) + " Calories";
            calorieText.setText(tmpStr);

        } else if (goal == 2 ) {
            tmpStr = "Your Recommended calorie :\n" + Math.round(suggestedIntake * 0.8) + " Calories for weight loss";
            calorieText.setText(tmpStr);
        } else if (goal == 3) {
            tmpStr = "Your Recommended Daily Intake for Weight Gain:\n" + Math.round(suggestedIntake * 1.2) + " Calories";
            calorieText.setText(tmpStr);
        }

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_Exercise:
                        Intent a = new Intent(ReccommendedCalorie.this, exerciseActivity.class);
                        startActivityForResult(a, EXERCISE_REQUEST);
                        break;
                    case R.id.navigation_dashboard:
                        Intent b = new Intent(ReccommendedCalorie.this,food_main_chart.class);
                        startActivity(b);
                        break;
                    case R.id.navigation_FOOD:
                        Intent c = new Intent(ReccommendedCalorie.this, Food.class);
                        startActivityForResult(c, NEW_FOOD_REQUEST);
                        break;
                    case R.id.ref:
                        Intent d = new Intent(ReccommendedCalorie.this,web_activity.class);
                        startActivity(d);
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.signout_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.signO:
                logOut();
                return true;

        }
        return false;
    }
    private void logOut(){
        editor.clear().apply();
        FirebaseAuth.getInstance().signOut();

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        // Check which request we're responding to

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_FOOD_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Gson gson = new Gson();
                String json = mPref.getString("NEW_FOOD", "noData");
                FoodHelper foodItem1 = gson.fromJson(json, FoodHelper.class);

                caloriesToday += (foodItem1.getCaloriesPerServing() * foodItem1.getNumServings());






                tmpStr = "Calories consumed today:\n" + caloriesToday;
                todayCalorieText.setText(tmpStr);


                editor.putInt("DAY_OF_PREVIOUS_MEAL", day);
                editor.putInt("DAILY_CALORIES", caloriesToday);
                editor.apply();
                if (caloriesToday <= suggestedIntake - 100 && caloriesToday >= suggestedIntake - 500) {
                    tmpStr = "You're almost there!";

                    progressMessage.setText(tmpStr);
                    progressMessage.setVisibility(View.VISIBLE);
                } else if (caloriesToday > suggestedIntake - 100 && caloriesToday < suggestedIntake + 100) {
                    tmpStr = "You've hit your goal!";

                    progressMessage.setText(tmpStr);
                    progressMessage.setVisibility(View.VISIBLE);
                } else if (caloriesToday > suggestedIntake + 100) {
                    tmpStr = "Whoa! You've eaten too much today!";

                    progressMessage.setText(tmpStr);
                    progressMessage.setVisibility(View.VISIBLE);
                } else {

                    progressMessage.setVisibility(View.INVISIBLE);
                }
                }
            }
            if (requestCode == EXERCISE_REQUEST) {
                if (resultCode == RESULT_OK) {
                    // calBurn += (int) Math.round((mPref.getInt("STEP_NUMBER", 0) * 0.05));
                    calBurn  += (int) Math.round(mPref.getInt("TIME", 0) * .0175 * weight * MET);
                tmpStr = "Calories actively burned today:\n" + calBurn;
                    burnedCalories.setText(tmpStr);

                    editor.putInt("CALORIES_BURNED_TODAY", calBurn);
                    editor.apply();

                }
            }
        }


    }


