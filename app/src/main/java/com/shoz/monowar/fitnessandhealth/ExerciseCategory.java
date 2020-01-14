package com.shoz.monowar.fitnessandhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ExerciseCategory extends ListActivity {

    ListView ExView;
    TextView ExName;
    String[] ExItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_category);



        ExName = (TextView) findViewById(R.id.textView);

        ListView listExercise = getListView();
        ArrayAdapter<ExerciseHelper> listAdapter = new ArrayAdapter<ExerciseHelper>(
                this, android.R.layout.simple_list_item_1, ExerciseHelper.workOut
        );
        listExercise.setAdapter(listAdapter);
    }

        public void onListItemClick(ListView listView, View itemView,int postion,long id) {
            Intent intent= new Intent(ExerciseCategory.this, ShowExerciseDetails.class);
            intent.putExtra(ShowExerciseDetails.EXTRA_EXno, (int) id);
            startActivity(intent);

        }



    }

