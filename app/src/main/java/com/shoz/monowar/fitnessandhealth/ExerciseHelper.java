package com.shoz.monowar.fitnessandhealth;

import androidx.annotation.NonNull;

public class ExerciseHelper {

    public static final String API_KEY = "AIzaSyB6WHigKJ89vPFADdLZmM9JYihaNiDKos4";
    private String name;
    private String description;
    String Yad;
    public static final ExerciseHelper[] workOut = {
            new ExerciseHelper("Push up","IODxDxX7oi4"," pushups are beneficial for building upper body strength. Build a powerful upper body with a press-up routine that takes you out of the ordinary and launches you into the ranks of beast. Even the toughest athletes will struggle with some of these variations.\n" +
                    "\n" +
                    "It’s time to embrace the challenge and increase your pushing power with these eight press-up progressions."),

            new ExerciseHelper("planks","pvIjsG5Svck","perform a plank exercise for core strengthening.The plank is one of the most inclusive exercises for all the main areas of the body. Plank exercises engage the abs, back, core, glutes, and hamstrings. It puts strength and endurance to the test while improving posture and flexibility. Beginners can hold the plank position for 10-20 seconds at a time, doing three sets with up to a minute of rest in between each set. Increase the time that you hold the plank position each week."),
            new ExerciseHelper("Leg Tonning","ilF_iVyukNI","exercises to successfully target your legs and adding strength, lean muscle, and endurance to your lower body."),
            new ExerciseHelper("squat","inegQ48dV_Y","Squats help to build leg muscles, including the quads, hamstrings and calves.The Squat exercise mainly targets the thighs (quadriceps & hamstrings) and the glutes. However, core strength & stability, ankle mobility, back muscles, calves, and other factors play an important role when you are doing this exercise."),
            new ExerciseHelper("Donkey Kicks","Im5nE3J558k","Sure, donkey kicks are aptly named since they mimic the animal’s movement—but they also build a great, well, you get the picture. “When performed correctly, this exercise does an excellent job of isolating the biggest and bulkiest glutes muscle"),
            new ExerciseHelper("Burpee","IYlT3GN9JOw","exercise to strengthen your entire body.The burpee, or squat thrust, is a full body exercise used in strength training and as an aerobic exercise. The basic movement is performed in four steps and known as a four-count burpee: Begin in a standing position. Move into a squat position with your hands on the ground."),
            new ExerciseHelper("Walking Lunge","qLyj6xo3j5c","Great for developing your leg strength"),
            new ExerciseHelper("Bird Dog for Lower Back Strengthening Exercise ","hxo0lDGLNkw","Begin on all fours (downward dog), ensuring your hands are directly under your shoulders and your knees are directly under your hips. Slowly extend your right leg behind you and reach your right arm forward into a straight line. Hold your balance without arching your back. Return to the starting position and repeat on the opposite side."),
            new ExerciseHelper("Bridge for core strengthening","8bbE64NuDTU","The basic bridge isolates and strengthens your gluteus (butt) muscles and hamstrings (back of the thigh). When done correctly, the move can also enhance core stability by targeting your abdominal muscles and the muscles of lower back and hip.")
    };
    private ExerciseHelper(String name,String yad,String description){
        this.name = name;
        this.Yad = yad;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getYad() {
        return Yad;
    }

    public String getDescription() {
        return description;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }
}
