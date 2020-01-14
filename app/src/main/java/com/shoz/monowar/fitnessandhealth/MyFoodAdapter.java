package com.shoz.monowar.fitnessandhealth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static java.security.AccessController.getContext;

public class MyFoodAdapter extends RecyclerView.Adapter<MyFoodHolder> {

    Context c;
    ArrayList<foodAdapter> foods;


    int selpos;

    public MyFoodAdapter(Context c,ArrayList<foodAdapter> foods){
        this.c=c;
        this.foods = foods;

    }
    @NonNull
    @Override
    public MyFoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.vmodel,null);
        MyFoodHolder holder = new MyFoodHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyFoodHolder holder, int position) {
        foodAdapter f = foods.get(position);

        holder.foodNameTxt.setText(f.getFoodTitle());
        holder.calTxt.setText(f.getcal());
        holder.servTxt.setText(f.getSer());


//        holder.calTxt.setText(f.getcal());
//        holder.servTxt.setText(f.getSer());


        //clicked
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                selpos=pos;
                Snackbar.make(v,foods.get(pos).getFoodTitle(),Snackbar.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public void deleteFood()
    {
        //GET ID
        foodAdapter p=foods.get(selpos);
        int id=p.getId();

        //DELETE IT FROM DB
        DbAdapter db=new DbAdapter(c);
        db.openDB();
        if(db.delete(id))
        {
            //DELETE ALSO FROM ARRAYLIST
            foods.remove(selpos);
        }else {
            Toast.makeText(c,"Unable To Delete",Toast.LENGTH_SHORT).show();
        }

        db.closeDB();

        this.notifyItemRemoved(selpos);
    }
}
