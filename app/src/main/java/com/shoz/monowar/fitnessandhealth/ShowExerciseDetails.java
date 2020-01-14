package com.shoz.monowar.fitnessandhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import static java.nio.file.Paths.get;

public class ShowExerciseDetails extends YouTubeBaseActivity {

    public static final String EXTRA_EXno = "exNo";
    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_exercise_details);

        b1 = (Button)findViewById(R.id.showVid);

        int exNo = (Integer)getIntent().getExtras().get(EXTRA_EXno);
        final ExerciseHelper exercise = ExerciseHelper.workOut[exNo];
        youTubePlayerView = (YouTubePlayerView)findViewById(R.id.youView);

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(exercise.getYad());

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        TextView name = (TextView) findViewById(R.id.ExNAME);
        name.setText(exercise.getName());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.initialize(ExerciseHelper.API_KEY,onInitializedListener);

            }
        });

        TextView description= (TextView) findViewById(R.id.WodescEx);
        description.setText(exercise.getDescription());
    }
}
