package com.shoz.monowar.fitnessandhealth;

public class FoodHelper {
    private String foodName;
    private int caloriesPerServing;
    private int numServings;

    public FoodHelper(String name, int calories, int servings){
        foodName = name;
        caloriesPerServing = calories;
        numServings = servings;
    }

    public String getFoodName(){
        return foodName;
    }

    public int getCaloriesPerServing() {
        return caloriesPerServing;
    }

    public int getNumServings() {
        return numServings;
    }
}


