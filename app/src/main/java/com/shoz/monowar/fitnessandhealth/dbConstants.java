package com.shoz.monowar.fitnessandhealth;

public class dbConstants {
    static final String ROW_ID="id";
    static final String NAME = "Foodname";
    static final String CALORIES = "FoodCal";
    static final String serv = "FoodServ";

    //DB PROPERTIES
    static final String DB_NAME="d_DB";
    static final String TB_NAME="d_TB";
    static final int DB_VERSION='1';

    static final String CREATE_TB="CREATE TABLE d_TB(id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "Foodname TEXT NOT NULL,FoodCal TEXT NOT NULL,FoodServ TEXT NOT NULL);";
}
