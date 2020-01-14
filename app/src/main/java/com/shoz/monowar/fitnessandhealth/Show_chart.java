package com.shoz.monowar.fitnessandhealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class Show_chart extends AppCompatActivity {

    private List<SampleFood> sfoods = new ArrayList<>();
    private RecyclerView recyclerView;
    private sampleFoodAdap sAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_chart);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        sAdapter = new sampleFoodAdap(sfoods);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(sAdapter);
        preparesfData();
    }
    public void preparesfData() {
        SampleFood sampF = new SampleFood("Rice", "150 calories", "1 cup");
        sfoods.add(sampF);

        sampF = new SampleFood("Mutton", "100 calories", "50 gram");
        sfoods.add(sampF);

        sampF = new SampleFood("Noodles", "80 calories", "1 bowl");
        sfoods.add(sampF);

        sampF = new SampleFood("Beef", "135 calories", "50 gram");
        sfoods.add(sampF);

        sampF = new SampleFood("Vegetables", "35 calories", "20 gram");
        sfoods.add(sampF);

        sampF = new SampleFood("chicken", "135 calories", "1 piece");
        sfoods.add(sampF);
        sampF = new SampleFood("Potato", "80 calories", "1 piece");
        sfoods.add(sampF);
        sampF = new SampleFood("Dal", "80 calories", "1 bowl");
        sfoods.add(sampF);
        sampF = new SampleFood("Plain Cake", "135 calories", "50 gram");
        sfoods.add(sampF);
        sampF = new SampleFood("Fried fish", "140 calories", "1 piece");
        sfoods.add(sampF);
        sampF = new SampleFood("Biryani", "225 calories", "1 plate");
        sfoods.add(sampF);
        sampF = new SampleFood("cola", "135 calories", "200ml");
        sfoods.add(sampF);
        sampF = new SampleFood("kheer", "180 calories", "1 plate");
        sfoods.add(sampF);
        sampF = new SampleFood("chicken curry", "235 calories", "1 piece");
        sfoods.add(sampF);
        sampF = new SampleFood("chicken", "135 calories", "1 piece");
        sfoods.add(sampF);
        sampF = new SampleFood("Puri", "85 calories", "1 piece");
        sfoods.add(sampF);



        sAdapter.notifyDataSetChanged();
    }
}
