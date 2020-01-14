package com.shoz.monowar.fitnessandhealth;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class sampleFoodAdap extends RecyclerView.Adapter<sampleFoodAdap.MyViewHolder> {

     List<SampleFood> sFoods;

    public class MyViewHolder extends RecyclerView.ViewHolder {
         TextView sftitle, sfcal, sfserving;

        public MyViewHolder(View view) {
            super(view);
            sftitle = (TextView) view.findViewById(R.id.sftitle);
            sfcal = (TextView) view.findViewById(R.id.sfccal);
            sfserving = (TextView) view.findViewById(R.id.sfcgenre);
        }
    }
    public sampleFoodAdap(List<SampleFood> sFoods) {
        this.sFoods = sFoods;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.show_f_row, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        SampleFood sampleFood = sFoods.get(position);
        holder.sftitle.setText(sampleFood.getTitle());
        holder.sfcal.setText(sampleFood.getGenre());
        holder.sfserving.setText(sampleFood.getYear());

    }
    public int getItemViewType(int position)
    {
        return position;
    }




    @Override
    public int getItemCount() {
        return sFoods.size();
    }
}
