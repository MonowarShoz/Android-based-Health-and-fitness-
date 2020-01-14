package com.shoz.monowar.fitnessandhealth;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, dbConstants.DB_NAME,null,dbConstants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(dbConstants.CREATE_TB);

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ dbConstants.TB_NAME);
        onCreate(db);

    }
}
