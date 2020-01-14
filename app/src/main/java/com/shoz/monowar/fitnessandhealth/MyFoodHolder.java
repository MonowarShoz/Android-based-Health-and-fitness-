package com.shoz.monowar.fitnessandhealth;

import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyFoodHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener {

    TextView foodNameTxt,calTxt,servTxt;
    ItemClickListener itemClickListener;
    public MyFoodHolder(View itemView) {
        super(itemView);

        foodNameTxt = (TextView)itemView.findViewById(R.id.nameFoodTxt);
        calTxt = (TextView)itemView.findViewById(R.id.calTxt);
        servTxt = (TextView)itemView.findViewById(R.id.serv);
        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);
    }


    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("ACTION : ");
        menu.add(0,0,0,"New");
        menu.add(0,1,0,"Delete");

    }


}
