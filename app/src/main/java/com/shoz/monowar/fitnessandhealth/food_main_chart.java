package com.shoz.monowar.fitnessandhealth;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import java.util.ArrayList;

public class food_main_chart extends AppCompatActivity {
    SearchView sv;

    EditText fName,fCal,fSER;
    RecyclerView recyclerView;
    MyFoodAdapter adapter;
    ArrayList<foodAdapter> foods=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_main_chart);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        recyclerView = (RecyclerView)findViewById(R.id.mRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new MyFoodAdapter(this,foods);


        retrieve();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.add_item:
                showDialog();
                return true;
            case R.id.show_sf:
                Intent intsamp = new Intent(food_main_chart.this,Show_chart.class);
                startActivity(intsamp);

        }
        return false;
    }








    public void retrieve() {
        foods.clear();
        DbAdapter db = new DbAdapter(this);
        db.openDB();
        Cursor c = db.getAllFoods();
        //LOOP AND ADD TO ARRAYLIST
        while (c.moveToNext()){
            int id=c.getInt(0);
            String name=c.getString(1);
            String cal=c.getString(2);
            String serv = c.getString(3);
            foodAdapter f =new foodAdapter(id,name,cal,serv);



            //ADD TO ARRAYLIST
            foods.add(f);
        }
        //CHECK IF ARRAYLIST ISNT EMPTY
        if(!(foods.size()<1))
        {
            recyclerView.setAdapter(adapter);
        }

        db.closeDB();;

    }

    private void showDialog(){
        Dialog d = new Dialog(this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.add_food);
        fName = (EditText)d.findViewById(R.id.FnameEditTxt);
        fCal = (EditText) d.findViewById(R.id.FcalEditTxt);
        fSER = (EditText) d.findViewById(R.id.FserEditTxt);

        Button saveBtn= (Button) d.findViewById(R.id.saveBtn);
        final Button retrievebtn= (Button) d.findViewById(R.id.retrieveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(fName.getText().toString(),fCal.getText().toString(),fSER.getText().toString());
            }
        });
        retrievebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieve();
            }
        });

        d.show();


    }

    private void save(String name, String cal, String serv) {
        DbAdapter db = new DbAdapter(this);
        db.openDB();
        //COMMIT
        long res=db.add(name,cal,serv);
        if(res>0)
        {
            fName.setText("");
            fCal.setText("");
            fSER.setText("");
        }else
        {
            Snackbar.make(fName, "Unable To Save",Snackbar.LENGTH_SHORT).show();

        }

        db.closeDB();

        //REFRESH
        retrieve();

    }
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle()=="New")
        {
            showDialog();
        }else if(item.getTitle()=="Delete")
        {
            adapter.deleteFood();
        }
        return super.onContextItemSelected(item);
    }





}
